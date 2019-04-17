package de.rieckpil.blog;

import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class MultipartFileUpload {

	@FormParam("file")
	@PartType("application/octet-stream")
	private byte[] data;

	public MultipartFileUpload() {

	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
