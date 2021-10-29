package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;

public class CRUD {

	public static ResultSet select(String tabla, String campos, String condicion) throws SQLException {	
		
		String query = "SELECT " + campos + " FROM " + tabla;
		
		if(!condicion.equals(""))
			query += " WHERE " + condicion;
		
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement datos = connection.prepareStatement(query);
		return datos.executeQuery();
		
	}
	
	
	
	
	public static int insertOrUpdate(String tabla, List<String> columna, List<String> tipo, List<String> valor) throws SQLException {
		
		String rows = "";
		String values = "";
		String query = "";
		
		if(columna.get(0).contains("id")) {
			query = "UPDATE " + tabla + " SET ";
			
			for(int i = 1; i < columna.size(); i++) {
				rows += columna.get(i) + " = ?, ";
			}
			
			values = " WHERE " + columna.get(0) + " = " + valor.get(0);
			query += rows.substring(0, rows.length()-2) + values; 
			
			valor.remove(0);
			columna.remove(0);
			tipo.remove(0);
			
		} else {
			query = "INSERT INTO " + tabla + " (";
			
			for (int i = 0; i < columna.size(); i++) {
				rows += columna.get(i)+ ", ";
				values += "?, ";
			}
			
			query += rows.substring(0, rows.length()-2) + ") VALUES (" + values.substring(0, values.length()-2) + ")"; 			
		}
		
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement datos = connection.prepareStatement(query);
		
		for (int i = 0; i < columna.size(); i++) {
			
			if (tipo.get(i).toLowerCase().equals("int"))
				datos.setInt(i + 1, Integer.parseInt(valor.get(i)));
			
			if (tipo.get(i).toLowerCase().equals("double"))
				datos.setDouble(i + 1, Double.parseDouble(valor.get(i)));
			
			if (tipo.get(i).toLowerCase().equals("string"))
				datos.setString(i + 1, valor.get(i));
		}

		return datos.executeUpdate();
	}
	
	public static int delete(String tabla, String columna, String tipo, String valor) throws SQLException {
		
		String query = "DELETE FROM " + tabla + " WHERE " + columna + " = ?";
		
		Connection connection = ConnectionProvider.getConnection();
		PreparedStatement datos = connection.prepareStatement(query);
		
		if (tipo.toLowerCase().equals("int"))
			datos.setInt(1, Integer.parseInt(valor));
		
		if (tipo.toLowerCase().equals("double"))
			datos.setDouble(1, Double.parseDouble(valor));
		
		if (tipo.toLowerCase().equals("string"))
			datos.setString(1, valor);
		
		return datos.executeUpdate();
	}
	
}
