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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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

}
