package cs.funciones;

import java.sql.*;
import datos.Conexion;
import oracle.jdbc.*;

public class TestCursores {

	public static void main(String[] args) {
		
		OracleCallableStatement oraCallStmt = null;
		OracleResultSet ors = null;
		
		try {
				Connection con = Conexion.getConnection();
			 oraCallStmt=(OracleCallableStatement) con.prepareCall("{?= call REF_CURSOR_PACKAGE.get_dept_ref_cursor(?)}");
			 oraCallStmt.registerOutParameter(1, OracleTypes.CURSOR);
			 oraCallStmt.setInt(2, 200);
			 oraCallStmt.execute();
			 
			 ors = (OracleResultSet) oraCallStmt.getCursor(1);
			 while(ors.next()) {
				 System.out.println("ID departamento:"+ors.getInt(1));
				 System.out.println("Nombre del departamento:"+ors.getString(2));
				 System.out.println("Ubicación_id:"+ors.getString(3));
				 System.out.println();
			 }
			 ors.close();
			 con.close();
			 oraCallStmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
