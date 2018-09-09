package datos;

import java.sql.*;
import java.util.*;

public class Conexion {
	
	//DRIVER ORACLE = http://icursos.net/cursos/JavaJDBC/drivers/ojdbc6.jar
	
	//GESTOR ORACLE XE (WIN)= http://icursos.net/software/SqlDeveloper/sqldeveloper-4.1.3.20.78-no-jre.zip
	//GESTOR ORACLE XE OTROS= http://www.oracle.com/technetwork/developer-tools/sql-developer/downloads/index.html
	
	//ORACLE XE (WIN)=http://icursos.net/software/oracleXE/OracleXE112_Win64.zip
	//ORACLE XE OTROS=http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
	
	private static String JDBC_DRIVER;
	private static String JDBC_URL;
	private static String JDBC_USER;
	private static String JDBC_PASS;
	private static Driver driver = null;
	private static String JDBC_FILE_NAME="jdbc";
	
	public static Properties loadProperties(String file) {
		Properties prop = new Properties();
		ResourceBundle bundle = ResourceBundle.getBundle(file);
		Enumeration e = bundle.getKeys();
		String key = null;
		while(e.hasMoreElements()) {
			key=(String) e.nextElement();
				System.out.println("KEY:"+key);
			prop.put(key, bundle.getObject(key));
		}
		JDBC_DRIVER=prop.getProperty("driver");
		JDBC_URL=prop.getProperty("url");
		JDBC_USER=prop.getProperty("user");
		JDBC_PASS=prop.getProperty("pass");
		return prop;
	}
	
	public static synchronized Connection getConnection() throws SQLException {
		
		if(driver == null) {
			try {
				loadProperties(JDBC_FILE_NAME);
				Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
			}catch(Exception e) {
				System.out.println("Fallo al cargar el Driver");
				e.printStackTrace();
			}
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			if(ps!=null)
				ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null)
				conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
