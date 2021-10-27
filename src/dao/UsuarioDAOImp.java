package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.NoExisteTematicaException;
import app.Tematica;
import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDAOImp implements UsuarioDAO {

	@Override
	 public List<Usuario> findAll()  throws SQLException, NoExisteTematicaException{
		
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		ResultSet s= CRUD.select("usuarios", "*");
		while (s.next())
		listaDeUsuarios.add(new Usuario(s.getInt("id_usuario"), 
										s.getString("nombre"), 
										s.getInt("monedas"),
										s.getDouble("tiempo"),
										Tematica.toTematica(s.getInt("id_tematica"))));		
		
		return listaDeUsuarios;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar=0; 
				
		ResultSet s= CRUD.select("usuarios", "count(*)");
		
		contar=s.getInt(1);
		
		return contar;
	}

	@Override
	public int insert(Usuario t) throws SQLException {
		
		List <String> columnas= new ArrayList <String> ();
		List <String> tipos= new ArrayList <String> (); 
		List <String> values= new ArrayList <String> (); 
		
		columnas.add("'nombre'");
		columnas.add("'monedas'");
		columnas.add("'tiempo'");
		columnas.add("'id_tematica'");
		tipos.add("String");
		tipos.add("Int");
		tipos.add("Double");
		tipos.add("Int");
		values.add(t.getNombre());
		values.add(t.getCantidadMonedas().toString());
		values.add(t.getTiempoDisponible().toString());
		values.add(String.valueOf(t.getPreferenciaUsuario().ordinal()+1));
				
		return CRUD.insert("usuarios", columnas, tipos, values);
		
	}

	@Override
	public int update(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
