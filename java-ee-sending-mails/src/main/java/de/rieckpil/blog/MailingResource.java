package de.rieckpil.blog;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("mails")
public class MailingResource {

	@Inject
	private MailingService mailingService;

	@GET
	public Response sendSimpleMessage() {
		mailingService.sendSimpleMail();
		return Response.ok("Mail was successfully delivered").build();
	}

}
