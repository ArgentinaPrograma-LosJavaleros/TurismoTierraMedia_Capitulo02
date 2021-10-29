package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoPromocion;

public class TipoPromocionDAOImp implements TipoPromocionDAO {

	@Override
	 public List<TipoPromocion> findAll()  throws SQLException {
		
		List<TipoPromocion> listaDetipo_promociones = new ArrayList<TipoPromocion>();
		
		ResultSet rs = CRUD.select("tipo_promociones", "*", "");
		while (rs.next())
		listaDetipo_promociones.add(new TipoPromocion(rs.getInt("id_tipo_promocion"), 
										rs.getString("nombre")));	
		
		return listaDetipo_promociones;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar = 0; 
				
		ResultSet rs = CRUD.select("tipo_promociones", "count(*)", "");
		
		contar = rs.getInt(1);
		
		return contar;
	}

	@Override
	public int insert(TipoPromocion t) throws SQLException {
		
		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("nombre");

		tipos.add("String");
		
		values.add(t.getNombre());

				
		return CRUD.insertOrUpdate("tipo_promociones", columnas, tipos, values);
	}

	@Override
	public int update(TipoPromocion t) throws SQLException {

		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("id_tipo_promocion");
		columnas.add("nombre");
		
		tipos.add("Int");
		tipos.add("String");
		
		values.add(t.getId().toString());
		values.add(t.getNombre());
				
		return CRUD.insertOrUpdate("tipo_promociones", columnas, tipos, values);
	}

	@Override
	public int delete(TipoPromocion t) throws SQLException {
		return CRUD.delete("tipo_promociones", "id_tipo_promocion", t.getId().toString(), "int");
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return CRUD.delete("tipo_promociones", campo, tipo, valor);
	}

	@Override
	public TipoPromocion findBy(String campo, String valor, String operador) throws SQLException {
		String condicion = campo + " " + operador + " " + valor;
		TipoPromocion tipoPromocion = new TipoPromocion("No existe");
		ResultSet rs = CRUD.select("tipo_promociones", "*", condicion);
		if(rs.next()) {
			tipoPromocion.setId(rs.getInt("id_tipo_promocion"));
			tipoPromocion.setNombre(rs.getString("nombre"));
		}
		return tipoPromocion;
	}

	@Override
	public TipoPromocion findById(int id) throws SQLException {
		return this.findBy("id_tipo_promocion", String.valueOf(id), "=");
	}

}