package de.rieckpil.blog;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Liveness
public class DatabaseLivenessCheck implements HealthCheck {

    @Resource(lookup = "jdbc/h2")
    private DataSource dataSource;

    private final String livenessCheckName = "database";

    @Override
    public HealthCheckResponse call() {

        try (var connection = dataSource.getConnection()) {
            var metaData = connection.getMetaData();

            return HealthCheckResponse.builder()
                    .name(livenessCheckName)
                    .withData("version", String.format("%s - %s.%s",
                            metaData.getDatabaseProductName(),
                            metaData.getDatabaseMajorVersion(),
                            metaData.getDatabaseMinorVersion()))
                    .withData("schema", connection.getSchema())
                    .withData("databaseName", connection.getCatalog())
                    .up()
                    .build();
        } catch (SQLException e) {
            return HealthCheckResponse.down(livenessCheckName);
        }
    }
}
