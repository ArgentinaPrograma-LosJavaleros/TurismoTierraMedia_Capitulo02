package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.NoExisteTematicaException;
import model.Atraccion;

public class AtraccionDAOImp implements AtraccionDAO {
	
	TematicaDAOImp tematica = new TematicaDAOImp();

	@Override
	 public List<Atraccion> findAll()  throws SQLException, NoExisteTematicaException{
		
		List<Atraccion> listaDeAtraccions = new ArrayList<Atraccion>();
		
		ResultSet rs = CRUD.select("atracciones", "*", "");
		while (rs.next())
		listaDeAtraccions.add(new Atraccion(rs.getInt("id_Atraccion"), 
										rs.getString("nombre"),
										rs.getInt("cupos"),
										rs.getDouble("tiempo"),
										rs.getInt("costo"),
										tematica.findById(rs.getInt("id_tematica"))));			
		
		return listaDeAtraccions;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar=0; 
				
		ResultSet rs= CRUD.select("atracciones", "count(*)", "");
		
		contar=rs.getInt(1);
		
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
		values.add(t.getTematica().getId().toString());
				
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
		values.add(t.getTematica().getId().toString());
		
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

	@Override
	public Atraccion findBy(String campo, String valor, String operador) throws SQLException {
		String condicion = campo + " " + operador + " " + valor;
		Atraccion atraccion = new Atraccion("No existe");
		ResultSet rs = CRUD.select("atracciones", "*", condicion);
		if(rs.next()) {
			atraccion.setId(rs.getInt("id_atraccion"));
			atraccion.setNombre(rs.getString("nombre"));
			atraccion.setCosto(rs.getInt("monedas"));
			atraccion.setTiempo(rs.getDouble("tiempo"));
			atraccion.setTematica(tematica.findById(rs.getInt("id_tematica")));		
		}
		return atraccion;
	}

	@Override
	public Atraccion findById(int id) throws SQLException {
		return this.findBy("id_atraccion", String.valueOf(id), "=");
	}

}
