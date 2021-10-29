package controller;

import java.sql.SQLException;
import java.util.List;

import app.NoExisteTematicaException;
import dao.UsuarioDAOImp;
import model.Usuario;

public class UsuarioController extends UsuarioDAOImp {
	@Override
	public List<Usuario> findAll()  throws SQLException, NoExisteTematicaException{
		return super.findAll();
	}
	
	@Override	
	public int countAll() throws SQLException {
		 return super.countAll();	
	}
	
	@Override	
	public int insert(Usuario t) throws SQLException {
		return super.insert(t);
	}

	@Override
	public int update(Usuario t) throws SQLException {
		return super.update(t);
	}
	
	@Override
	public int delete(Usuario t) throws SQLException {
		return super.delete(t);
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return super.deleteBy(campo, tipo, valor);
	}
	
	@Override
	public Usuario findBy(String campo, String valor, String operador) throws SQLException {
		return super.findBy(campo, valor, operador);
	}

	@Override
	public Usuario findById(int id) throws SQLException {
		return super.findById(id);
	}
	
}