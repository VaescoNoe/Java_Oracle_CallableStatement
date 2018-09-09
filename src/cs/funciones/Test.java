package cs.funciones;

import java.sql.*;

import datos.Conexion;

public class Test {

	public static void main(String[] args) {
		int empleadoId=100;
		
		try {
			Connection con = Conexion.getConnection();
			CallableStatement cs = null;
			double salarioMensual;
			
			cs = con.prepareCall("{ ? = call get_employees_salary(?)}");
			
			cs.registerOutParameter(1,java.sql.Types.INTEGER);
			cs.setInt(2, empleadoId);
			cs.execute();
			salarioMensual = cs.getDouble(1);
			cs.close();
			System.out.println("Empleado con el ID:"+empleadoId);
			System.out.println("Salario $"+salarioMensual);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
