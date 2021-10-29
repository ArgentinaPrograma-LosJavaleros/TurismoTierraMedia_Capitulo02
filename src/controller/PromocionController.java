package controller;

import java.sql.SQLException;
import java.util.List;

import app.NoExisteTematicaException;
import dao.PromocionDAOImp;
import model.Promocion;

public class PromocionController extends PromocionDAOImp {
	@Override
	public List<Promocion> findAll()  throws SQLException, NoExisteTematicaException{
		return super.findAll();
	}
	
	@Override	
	public int countAll() throws SQLException {
		 return super.countAll();	
	}
	
	@Override	
	public int insert(Promocion t) throws SQLException {
		return super.insert(t);
	}

	@Override
	public int update(Promocion t) throws SQLException {
		return super.update(t);
	}

	@Override
	public int delete(Promocion t) throws SQLException {
		return super.delete(t);
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return super.deleteBy(campo, tipo, valor);
	}
}