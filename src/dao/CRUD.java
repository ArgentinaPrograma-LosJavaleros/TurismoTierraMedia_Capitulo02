package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;

public class CRUD {

	public static ResultSet select (String tabla, String campos) throws SQLException {
		
		Connection conex= ConnectionProvider.getConnection();
		PreparedStatement datos= conex.prepareStatement("Select " + campos + " from " + tabla);
		
		return datos.executeQuery();
		
		
	}
	
	public static int insert (String tabla, List <String> columna, List <String> tipo,List <String> valor) throws SQLException {
		
		String rows = "";
		String values = "";
		
		
		String query = "insert into " + tabla + " (";
		for (int i= 0; i< columna.size(); i++) {
			rows = rows + columna.get(i)+ ", ";
			values = values+ "?, ";
		}
		
		query= query+ rows.substring(0, rows.length()-2) + ") values (" + values.substring(0, values.length()-2) + ")"; 
		
		Connection conex= ConnectionProvider.getConnection();
		PreparedStatement datos= conex.prepareStatement(query);
		for (int i= 0; i< columna.size(); i++) {
			
			if (tipo.get(i).toLowerCase().equals("int"))
				datos.setInt(i+1, Integer.parseInt(valor.get(i)));
			
			if (tipo.get(i).toLowerCase().equals("double"))
				datos.setDouble(i+1, Double.parseDouble(valor.get(i)));
			
			if (tipo.get(i).toLowerCase().equals("string"))
				datos.setString(i+1, valor.get(i));
		}

		return datos.executeUpdate();
	}
	
}
