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
		
		
		Connection conex= ConnectionProvider.getConnection();
		PreparedStatement datos= conex.prepareStatement("Select * from usuarios");
		
		ResultSet s= datos.executeQuery();
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
		Connection conex= ConnectionProvider.getConnection();
		PreparedStatement datos= conex.prepareStatement("Select count(*) from usuarios");
		
		
		ResultSet s= datos.executeQuery();
		
		contar=s.getInt(1);
		
		
		
		return contar;
	}

	@Override
	public int insert(Usuario t) throws SQLException {
		int correcto=0;
		
		
		
		Connection conex= ConnectionProvider.getConnection();
		PreparedStatement datos= conex.prepareStatement("Insert into usuarios ('nombre', 'monedas','tiempo','id_tematica')  values(?,?,?,?)");
		
		
		datos.setString(1, t.getNombre());
		datos.setInt(2, t.getCantidadMonedas());
		datos.setDouble(3, t.getTiempoDisponible());
		datos.setInt(4, t.getPreferenciaUsuario().ordinal()+1);
		
		correcto=datos.executeUpdate();
		
		return correcto;
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
