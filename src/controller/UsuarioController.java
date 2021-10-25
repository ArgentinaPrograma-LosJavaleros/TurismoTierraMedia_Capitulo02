package controller;

import java.sql.SQLException;
import java.util.List;

import app.NoExisteTematicaException;
import dao.UsuarioDAOImp;
import model.Usuario;

public class UsuarioController extends UsuarioDAOImp {
	public List<Usuario> findAll()  throws SQLException, NoExisteTematicaException{
		
		return super.findAll();
		
	}
	
	public int countAll() throws SQLException {
		 return super.countAll();
		
	}
	
	public int insert(Usuario t) throws SQLException {
		
		return super.insert(t);
	}
}
