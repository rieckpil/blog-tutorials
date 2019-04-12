# HOWTO: Up- and downloading files with Java EE and Web Components 

In one of my last blog post I showed you how to upload and download files with React and Spring Boot. Today I want to give you a quickstart example on how to achieve the same with Java EE and Web Components (standards ftw!). In this blog post I'll be using Java 8, Java EE 8, Payara 5.191 as the application server and no framework for the frontend (except Bootstrap for some styling)

## Setting up the backend

As the JAX-RS specification currently doesn't provide a convenient way to a work with multipart data (except working with the HttpRequest directly), I'm using some propretiary Jersey code. Similar annotations/methods are availabe for ApacheCXF and RestEasy as well. 


The pom.xml for the backend looks like the following:

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>de.rieckpil.blog</groupId>
	<artifactId>backend</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>war</packaging>
	<dependencies>
		<dependency>
			<groupId>javax</groupId>
			<artifactId>javaee-api</artifactId>
			<version>8.0</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.core</groupId>
			<artifactId>jersey-server</artifactId>
			<version>2.27</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.media</groupId>
			<artifactId>jersey-media-multipart</artifactId>
			<version>2.27</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
	<build>
		<finalName>backend</finalName>
	</build>
	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
		<failOnMissingWebXml>false</failOnMissingWebXml>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	</properties>
</project>
```

The two Jersey dependencies are required for the propreitary annotations, which you'll see in the next step. They are marked as scope `provided` as they are alreay packaged within Payara and therefor don't need to pollute the .war file and keep its size thin.

To register the MultipartFeature of Jersey for our JAX-RS endpoints, I'm using the programmatic way like the following:

```
@ApplicationPath("resources")
public class JAXRSConfiguration extends ResourceConfig {

	public JAXRSConfiguration() {
		packages("de.rieckpil.blog").register(MultiPartFeature.class);
	}

}
```

The ResourceConfig class is part of the jersey-server dependency. You can achieve the same with registering the feature in the `web.xml`.

With this feature enabled we can now make use of the `@FormDataParam` annotation to parse and access the incoming `FormData`. In addition Jesery provides meta data of the uploaded file with the class `FormDataContentDisposition`. As the content type of the incoming request won't be classic `application/json`, we have to add the correct `MediaType` to the method:

```
@Path("files")
@Stateless
public class FileUploadResource {

	@PersistenceContext
	private EntityManager em;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = uploadedInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        FileUpload upload = new FileUpload(fileDetail.getFileName(), fileDetail.getType(),
                byteArrayOutputStream.toByteArray());

        em.persist(upload);
    }

    // ...
}
```

Within the method I'm converting the incoming InputStream to a byte[] and store it in the embedded H2 database of Payara using this JPA entity:

```
@Entity
public class FileUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;

	private String contentType;

	@Lob
	private byte[] data;

    // constructors, getters and setters ...
}
```

Besides uploading I'm also providing a endpoint to download a file. In this example a random file is retrieved from the database and returned as `MediaType.APPLICATION_OCTET_STREAM`. In addition the `Content-Disposition` header containts the name of the file. 

@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getRandomFile() {

		Long amountOfFiles = em.createQuery("SELECT COUNT(f) FROM FileUpload f", Long.class).getSingleResult();
		Long randomPrimaryKey;

		System.out.println(amountOfFiles);

		if (amountOfFiles == null || amountOfFiles == 0) {
			return Response.ok().build();
		} else if (amountOfFiles == 1) {
			randomPrimaryKey = 1L;
		} else {
			randomPrimaryKey = ThreadLocalRandom.current().nextLong(1, amountOfFiles + 1);
		}

		FileUpload randomFile = em.find(FileUpload.class, randomPrimaryKey);

		return Response.ok(randomFile.getData(), MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=" + randomFile.getFileName()).build();
	}


To make the interaction with the frontend work, we have to enable CORS as the browser will otherwise block the request. This is acheived with a JAX-RS @Provider which intercepts the HTTP response and adds custom HTTP headers. Extracting the filename of the downloaded file in the frontend later on, the header `Access-Control-Expose-Headers` is important, as we otherwise won't have access to the HTTP header Content-Disposition:

@Provider
public class CorsFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

		// Required to be able to access the Content-Disposition header with Fetch API
		responseContext.getHeaders().add("Access-Control-Expose-Headers", "content-disposition");
	}
}

That's everything for the backend

## Setting up the frontend

As I'm using no framework for the frontend, the setup is pretty straighforward. In addition to the Bootstrap CSS and JS libraries, I'm adding a custom `app.js` to the `index.html`. Within the <body> of the HTML file, you can find a basic page layout and two unkown HTML tags: <upload-component> and <download-component> which are both custom Web Components:
```
<!DOCTYPE html>
<html>
<head>
    <title>File Upload with Web Components</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-9  offset-md-1">
                <h3 style="text-align:center">Up- and downloading files with Java EE and Web Components</h3>
                <div id="message" role="alert"></div>
                <upload-component caption="Upload"></upload-component>
                <br/>
                <download-component caption="Download random file"></download-component>
            </div>
        </div>
    </div>

    <script src="app.js" type="module"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
    </script>
</body>
</html>
```

Each component is defined in its own file (DownloadComponent.js and UploadComponent.js) and is really simple for this quickstart (no real configuration from outside to make it reusable), but you should get a good insight into Web Components with them. To show you how you could configure the component from outside, I'm passing the text of the button as a component attribute (`caption`) from outside:

export default class UploadComponent extends HTMLElement {

    constructor() {
        super();
        this.message = document.querySelector("#message");
        this.innerHTML = `<form>
            <input required type="file"></input>
            <button type="submit">${this.getAttribute('caption')}</button>
        </form>`;
        this.form = this.querySelector('form');
        this.form.onsubmit = e => this.uploadFile(e);
        this.file = this.querySelector('input');
    }

    uploadFile(e) {
        e.preventDefault();
        const formData = new FormData();
        formData.append('file', this.file.files[0]);

        fetch('http://localhost:8080/resources/files', {
            method: 'POST',
            body: formData
        }).then(response => {
            this.message.innerHTML = 'File upload was succesfull!';
            this.message.className = 'alert alert-success';
            this.form.reset();
        }).catch(error => {
            this.message.innerHTML = 'Something went wrong while uploading a file :(';
            this.message.className = 'alert alert-danger';
        });
    }
}

customElements.define('upload-component', UploadComponent);

For creating a Web Component, you have to extend the HTMLElement class and define it later on as a custom element with a own HTML tag. Within the constructor of the component, I'm creating the HTML layout of the component with a simple JavaScript string literal (have a look at lit-html for a more advanced way of doing this). In additon, the references to some of the HTML elements is stored as attributes and a `onsubmit` function is defined for the the `form`. 

The file for the upload is wrapped in a FormData object and the Fetch API is used to make the POST HTTP call and upload the file. 

The <download-component> is even simpler then the previous. The component contains just a button and access the JAX-RS endpoint for downloading a random file on every click on the button: 

export default class DownloadComponent extends HTMLElement {

    constructor() {
        super();
        this.message = document.querySelector("#message");
        this.innerHTML = `<button>${this.getAttribute('caption')}</button>`;
        this.button = this.querySelector('button');
        this.button.onclick = e => this.downloadRandomFile(e);
    }

    downloadRandomFile(e) {
        e.preventDefault();
        fetch('http://localhost:8080/resources/files')
            .then(response => {
                console.log(response.headers)
                const filename = response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            }).catch(error => {
                this.message.innerHTML = 'Something went wrong while downloading a random file :(';
                this.message.className = 'alert alert-danger';
            });
    }

}

customElements.define('download-component', DownloadComponent);



You can find the example in my GitHub repository with a `docker-compose.yml` file for local testing time on your machine.

Have fun up- and downloading files with Web/Java EE standards only, 
Phil