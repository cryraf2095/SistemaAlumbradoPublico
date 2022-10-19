package com.alumbradopublico.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class SQLServerConnection {

	static {
		new SQLServerDriver();
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SistemaAlumbradoPublico","SA","SistemaAdministracionAlumbradoPublico1");
	}
}
