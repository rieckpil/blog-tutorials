package sample;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("sample")
public class SampleResource {

	private String message = "Hello World!";

	@PersistenceContext
	private EntityManager em;

	@Resource(lookup = "jdbc/postgres")
	// @Resource(name = "jdbc/postgres") for Tomee
	// @Resource(lookup = "java:jboss/datasources/postgres") for WildFly
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
