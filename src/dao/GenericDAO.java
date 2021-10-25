package dao;

import java.sql.SQLException;
import java.util.List;

import app.NoExisteTematicaException;

public interface GenericDAO<T> {

	public List<T> findAll() throws SQLException, NoExisteTematicaException;
	
	public int countAll() throws SQLException;
	
	public int insert(T t) throws SQLException;
	
	public int update(T t) throws SQLException;
	
	public int delete(T t) throws SQLException;
	
}
