package db.migration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V004__UPDATE_REVIEWS extends BaseJavaMigration {

	@Override
	public Integer getChecksum() {
		// implement this if needed to detect changes
		return super.getChecksum();
	}

	public void migrate(Context context) throws Exception {

		// either create a Spring JdbcTemplate or use native JDBC
		// new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(),
		// true));

		try (Statement select = context.getConnection().createStatement()) {
			try (ResultSet rows = select.executeQuery("SELECT id, name, publisher FROM book")) {
				while (rows.next()) {
					Long id = rows.getLong(1);
					String bookName = rows.getString(2);
					String publisher = rows.getString(3);
					try (PreparedStatement preparedUpdate = context.getConnection()
							.prepareStatement("UPDATE book SET name = ?, publisher = ? WHERE id = ?")) {
						preparedUpdate.setString(1, bookName.toUpperCase());
						preparedUpdate.setString(2, publisher.toUpperCase());
						preparedUpdate.setLong(3, id);
						preparedUpdate.executeUpdate();
					}
				}
			}
		}
	}
}
