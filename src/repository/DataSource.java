package repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/chat";
	private static final String USER = "root";
	private static final String PASS = "123";
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	static {
		config.setJdbcUrl(DB_URL);
		config.setUsername(USER);
		config.setPassword(PASS);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("maximumPoolSize", "40");
		ds = new HikariDataSource(config);
	}

	public DataSource() {
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
