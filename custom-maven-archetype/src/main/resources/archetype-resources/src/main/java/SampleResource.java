package ${package};

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("sample")
public class SampleResource {

	@GET
	public Response message() {
		String message = "Hello World from ${groupId}/${artifactId}";
		return Response.ok(message).build();
	}

}
