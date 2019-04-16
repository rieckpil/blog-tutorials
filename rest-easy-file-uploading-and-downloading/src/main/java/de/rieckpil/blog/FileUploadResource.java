package de.rieckpil.blog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("files")
@Stateless
public class FileUploadResource {

	@PersistenceContext
	private EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getRandomFile() {

		Long amountOfFiles = em.createQuery("SELECT COUNT(f) FROM FileUpload f", Long.class).getSingleResult();
		Long randomPrimaryKey;

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

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadFile(MultipartFormDataInput incomingFile) throws IOException {

		InputPart inputPart = incomingFile.getFormDataMap().get("file").get(0);
		InputStream uploadedInputStream = inputPart.getBody(InputStream.class, null);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;

		while ((len = uploadedInputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}

		FileUpload upload = new FileUpload(
				getFileNameOfUploadedFile(inputPart.getHeaders().getFirst("Content-Disposition")),
				getContentTypeOfUploadedFile(inputPart.getHeaders().getFirst("Content-Type")),
				byteArrayOutputStream.toByteArray());

		em.persist(upload);
	}

	/**
	 * Parses the HTTP Content-Disposition header to extract the filename. If the
	 * header is missing or not containing a filename attribute, 'unknown' is
	 * returned.
	 * 
	 * Sample HTTP Content-Disposition header:
	 * 
	 * Content-Disposition=[form/data;filename="foo.txt"]
	 * 
	 * @param contentDispositionHeader
	 * @return the name of the uploaded file
	 */
	private String getFileNameOfUploadedFile(String contentDispositionHeader) {

		if (contentDispositionHeader == null || contentDispositionHeader.isEmpty()) {
			return "unkown";
		} else {
			String[] contentDispositionHeaderTokens = contentDispositionHeader.split(";");

			for (String contentDispositionHeaderToken : contentDispositionHeaderTokens) {
				if ((contentDispositionHeaderToken.trim().startsWith("filename"))) {
					return contentDispositionHeaderToken.split("=")[1].trim().replaceAll("\"", "");
				}
			}

			return "unkown";
		}
	}

	/**
	 * Parses the HTTP Content-Type header to extract the type (e.g. image/jpeg) of
	 * the file. If the header is missing or empty, 'unknown' is returned.
	 * 
	 * Sample HTTP Content-Type header:
	 * 
	 * Content-Type=[image/jpeg]
	 * 
	 * @param contentTypeHeader
	 * @return the type of the uploaded file
	 */
	private String getContentTypeOfUploadedFile(String contentTypeHeader) {
		if (contentTypeHeader == null || contentTypeHeader.isEmpty()) {
			return "unkown";
		} else {
			return contentTypeHeader.replace("[", "").replace("]", "");
		}
	}

}
