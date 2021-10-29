package controller;

import java.sql.SQLException;
import java.util.List;

import app.NoExisteTematicaException;
import dao.AtraccionDAOImp;
import model.Atraccion;

public class AtraccionController extends AtraccionDAOImp {
	@Override
	public List<Atraccion> findAll()  throws SQLException, NoExisteTematicaException{
		return super.findAll();
	}
	
	@Override	
	public int countAll() throws SQLException {
		 return super.countAll();	
	}
	
	@Override	
	public int insert(Atraccion t) throws SQLException {
		return super.insert(t);
	}

	@Override
	public int update(Atraccion t) throws SQLException {
		return super.update(t);
	}

	@Override
	public int delete(Atraccion t) throws SQLException {
		return super.delete(t);
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return super.deleteBy(campo, tipo, valor);
	}
	
	@Override
	public Atraccion findBy(String campo, String valor, String operador) throws SQLException {
		return super.findBy(campo, valor, operador);
	}

	@Override
	public Atraccion findById(int id) throws SQLException {
		return super.findById(id);
	}
	
}
