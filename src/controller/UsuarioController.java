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
	
}
