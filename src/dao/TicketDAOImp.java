package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Ticket;
import model.TipoPromocion;

public class TicketDAOImp implements TicketDAO {

	TematicaDAOImp tematica = new TematicaDAOImp();
	TipoPromocionDAOImp tipoPromo = new TipoPromocionDAOImp();
	AtraccionDAOImp atraccion = new AtraccionDAOImp();
	UsuarioDAOImp usuario = new UsuarioDAOImp();
	PromocionDAOImp promocion = new PromocionDAOImp();
	
	@Override
	 public List<Ticket> findAll()  throws SQLException{
		
		List<Ticket> listaDeTickets = new ArrayList<Ticket>();
		
		ResultSet rs = CRUD.select("tickets", "*", "");
		while (rs.next()) {
			String comprador = usuario.findById(rs.getInt("id_usuario")).getNombre();
			ResultSet rsAtraccion = CRUD.select("tickets_atracciones", "*", "id_ticket = " + rs.getInt("id_ticket"));	
			List<String> atracciones = new ArrayList<String>();
			while(rsAtraccion.next()) {
				atracciones.add(atraccion.findById(rsAtraccion.getInt("id_atracion")).getNombre());
			}
			
			ResultSet rsPromocion = CRUD.select("tickets_promociones", "*", "id_ticket = " + rs.getInt("id_ticket"));	
			List<String> promociones = new ArrayList<String>();
			while(rsPromocion.next()) {
				promociones.add(promocion.findById(rsPromocion.getInt("id_promocion")).getNombre());
			}
			
			
			listaDeTickets.add(new Ticket(rs.getInt("id_ticket"), 
										comprador,
										rs.getInt("monedas_gastadas"),
										rs.getDouble("tiempo_gastado"),
										atracciones,
										promociones));	
		}	
		return listaDeTickets;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar = 0; 
				
		ResultSet rs = CRUD.select("tickets", "count(*)", "");
		
		contar = rs.getInt(1);
		
		return contar;
	}

	@Override
	public int insert(Ticket t) throws SQLException {
		
		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("id_usuario");

		tipos.add("String");
		
		values.add(t.getNombre());

				
		return CRUD.insertOrUpdate("tickets", columnas, tipos, values);
	}

	@Override
	public int update(Ticket t) throws SQLException {

		List<String> columnas= new ArrayList<String> ();
		List<String> tipos= new ArrayList<String> (); 
		List<String> values= new ArrayList<String> (); 
		
		columnas.add("id_ticket");
		columnas.add("id_usuario");
		
		tipos.add("Int");
		tipos.add("String");
		
		values.add(t.getId().toString());
		values.add(t.getNombre());
				
		return CRUD.insertOrUpdate("tickets", columnas, tipos, values);
	}

	@Override
	public int delete(Ticket t) throws SQLException {
		return CRUD.delete("tickets", "id_ticket", t.getId().toString(), "int");
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return CRUD.delete("tickets", campo, tipo, valor);
	}

	@Override
	public Ticket findBy(String campo, String operador, String valor) throws SQLException {
		String condicion = campo + " " + operador + " " + valor;
		Ticket tematica = new Ticket("No existe");
		ResultSet rs = CRUD.select("tickets", "*", condicion);
		if(rs.next()) {
			tematica.setId(rs.getInt("id_ticket"));
			tematica.setNombre(rs.getString("id_usuario"));
		}
		return tematica;
	}

	@Override
	public Ticket findById(int id) throws SQLException {
		return this.findBy("id_ticket", "=", String.valueOf(id));
	}

}
