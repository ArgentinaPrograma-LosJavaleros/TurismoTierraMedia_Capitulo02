package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.NoExisteTematicaException;
import model.Usuario;

public class UsuarioDAOImp implements UsuarioDAO {
	
	TematicaDAOImp tematica = new TematicaDAOImp();

	@Override
	 public List<Usuario> findAll()  throws SQLException, NoExisteTematicaException{
		
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		ResultSet rs = CRUD.select("usuarios", "*", "");
		while (rs.next())
		listaDeUsuarios.add(new Usuario(rs.getInt("id_usuario"), 
										rs.getString("nombre"), 
										rs.getInt("monedas"),
										rs.getDouble("tiempo"),
										tematica.findById(rs.getInt("id_tematica"))));		
		
		return listaDeUsuarios;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar = 0; 
				
		ResultSet rs = CRUD.select("usuarios", "count(*)", "");
		
		contar = rs.getInt(1);
		
		return contar;
	}

	@Override
	public int insert(Usuario t) throws SQLException {
		
		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("nombre");
		columnas.add("monedas");
		columnas.add("tiempo");
		columnas.add("id_tematica");
		
		tipos.add("String");
		tipos.add("Int");
		tipos.add("Double");
		tipos.add("Int");
		
		values.add(t.getNombre());
		values.add(t.getCantidadMonedas().toString());
		values.add(t.getTiempoDisponible().toString());
		values.add(t.getPreferencia().getId().toString());
				
		return CRUD.insertOrUpdate("usuarios", columnas, tipos, values);
	}

	@Override
	public int update(Usuario t) throws SQLException {

		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("id_usuario");
		columnas.add("nombre");
		columnas.add("monedas");
		columnas.add("tiempo");
		columnas.add("id_tematica");
		
		tipos.add("Int");
		tipos.add("String");
		tipos.add("Int");
		tipos.add("Double");
		tipos.add("Int");
		
		values.add(t.getId().toString());
		values.add(t.getNombre());
		values.add(t.getCantidadMonedas().toString());
		values.add(t.getTiempoDisponible().toString());
		values.add(t.getPreferencia().getId().toString());
				
		return CRUD.insertOrUpdate("usuarios", columnas, tipos, values);
	}

	@Override
	public int delete(Usuario t) throws SQLException {
		return CRUD.delete("usuarios", "id_usuario", t.getId().toString(), "int");
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return CRUD.delete("usuarios", campo, tipo, valor);
	}

	@Override
	public Usuario findBy(String campo, String valor, String operador) throws SQLException {
		String condicion = campo + " " + operador + " " + valor;
		Usuario usuario = new Usuario("No existe");
		ResultSet rs = CRUD.select("usuarios", "*", condicion);
		if(rs.next()) {
			usuario.setId(rs.getInt("id_usuario"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setCantidadMonedas(rs.getInt("monedas"));
			usuario.setTiempoDisponible(rs.getDouble("tiempo"));
			usuario.setPreferencia(tematica.findById(rs.getInt("id_tematica")));
		}
		return usuario;
	}

	@Override
	public Usuario findById(int id) throws SQLException {
		return this.findBy("id_usuario", String.valueOf(id), "=");
	}
}
