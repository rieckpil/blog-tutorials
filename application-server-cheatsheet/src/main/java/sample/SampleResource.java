package sample;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("sample")
public class SampleResource {

	@Inject
	@ConfigProperty(name = "message")
	private String message;

	@PersistenceContext
	private EntityManager em;

	@Resource(lookup = "jdbc/postgres")
	private DataSource dataSource;

	@GET
	public Response message() throws SQLException {
		String databaseName = "";

		try (Connection con = dataSource.getConnection()) {
			databaseName = con.getMetaData().getDatabaseProductName();
		}

		return Response.ok(message + " with database: " + databaseName).build();
	}
}
