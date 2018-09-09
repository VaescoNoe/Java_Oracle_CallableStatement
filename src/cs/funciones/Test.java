package cs.funciones;

import java.sql.*;

import datos.Conexion;

public class Test {

	public static void main(String[] args) {
		int empleadoId=100;
		
		try {
			procedure(empleadoId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static double function(int empleadoId) throws SQLException {
		Connection con = Conexion.getConnection();
		CallableStatement cs = null;
		double salarioMensual;
		
		cs = con.prepareCall("{ ? = call get_employees_salary(?)}");
		
		cs.registerOutParameter(1,java.sql.Types.INTEGER);
		cs.setInt(2, empleadoId);
		cs.execute();
		salarioMensual = cs.getDouble(1);
		cs.close();
		return salarioMensual;
	}
	
	public static void procedure(int empleadoId) throws SQLException {
		double incrementoSalario = 1.1;
		Connection con;
		con=Conexion.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		
		stmt = con.createStatement();
			System.out.println("Aumento del 10% al empleado:"+empleadoId);
		cs = con.prepareCall("{call SET_EMPLOYEE_SALARY(?,?)}");
		cs.setInt(1, empleadoId);
		cs.setDouble(2,	incrementoSalario);
		cs.execute();
		cs.close();
		
		System.out.println("Nuevo salario:"+function(empleadoId));
	}
	
}
