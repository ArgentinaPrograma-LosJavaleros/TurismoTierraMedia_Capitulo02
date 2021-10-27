package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.NoExisteTematicaException;
import app.Tematica;
import model.Atraccion;

public class AtraccionDAOImp implements AtraccionDAO {

	@Override
	 public List<Atraccion> findAll()  throws SQLException, NoExisteTematicaException{
		
		List<Atraccion> listaDeAtraccions = new ArrayList<Atraccion>();
		
		ResultSet s= CRUD.select("atracciones", "*");
		while (s.next())
		listaDeAtraccions.add(new Atraccion(s.getInt("id_Atraccion"), 
										s.getString("nombre"),
										s.getInt("cupos"),
										s.getDouble("tiempo"),
										s.getInt("costo"),
										Tematica.toTematica(s.getInt("id_tematica"))));		
		
		return listaDeAtraccions;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar=0; 
				
		ResultSet s= CRUD.select("atracciones", "count(*)");
		
		contar=s.getInt(1);
		
		return contar;
	}

	@Override
	public int insert(Atraccion t) throws SQLException {
		
		List<String> columnas= new ArrayList <String> ();
		List<String> tipos= new ArrayList <String> (); 
		List<String> values= new ArrayList <String> (); 
		
		columnas.add("nombre");
		columnas.add("cupos");
		columnas.add("tiempo");
		columnas.add("costo");
		columnas.add("id_tematica");
		
		tipos.add("String");
		tipos.add("Int");
		tipos.add("Double");
		tipos.add("Int");
		tipos.add("Int");
		
		values.add(t.getNombre());
		values.add(t.getCupoUsuarios().toString());
		values.add(t.getTiempo().toString());
		values.add(t.getCosto().toString());
		values.add(String.valueOf(t.getTematica().ordinal()+1));
				
		return CRUD.insertOrUpdate("atracciones", columnas, tipos, values);
	}

	@Override
	public int update(Atraccion t) throws SQLException {

		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("id_Atraccion");
		columnas.add("nombre");
		columnas.add("cupos");
		columnas.add("tiempo");
		columnas.add("costo");
		columnas.add("id_tematica");
		
		tipos.add("Int");
		tipos.add("String");
		tipos.add("Int");
		tipos.add("Double");
		tipos.add("Int");
		tipos.add("Int");
		
		values.add(t.getId().toString());
		values.add(t.getNombre());
		values.add(t.getCupoUsuarios().toString());
		values.add(t.getTiempo().toString());
		values.add(t.getCosto().toString());
		values.add(String.valueOf(t.getTematica().ordinal()+1));
				
		return CRUD.insertOrUpdate("atracciones", columnas, tipos, values);
	}

	@Override
	public int delete(Atraccion t) throws SQLException {
		return CRUD.delete("atracciones", "id_Atraccion", t.getId().toString(), "int");
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return CRUD.delete("atracciones", campo, tipo, valor);
	}

}
