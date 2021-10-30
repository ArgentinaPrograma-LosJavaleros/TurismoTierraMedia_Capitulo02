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
		
		//values.add(t.getNombre());

		return insertTickets(t) + insertTickets_Atracciones(t) + insertTicket_Promociones(t);		
		//return CRUD.insertOrUpdate("tickets", columnas, tipos, values);
	}

	private int insertTicket_Promociones(Ticket t) throws SQLException {
		
		int contador = 0;
		List<String> columnas = new ArrayList<String> ();
		List<String> tipos = new ArrayList<String> (); 
		List<String> values = new ArrayList<String> (); 
		
		for (String str : t.getPromocionesReservadas()) {
				
			columnas.add("id_ticket");
			columnas.add("id_promocion");
			
			tipos.add("Int");
			tipos.add("Int");
			
			values.add(t.getId().toString());
			values.add(promocion.findBy("nombre", "=", str).getId().toString());
			contador += CRUD.insert("tickets_promociones", columnas, tipos, values);
		}
		return contador;
	}

	private int insertTickets_Atracciones(Ticket t) throws SQLException {

		int contador = 0;
		List<String> columnas = new ArrayList<String> ();
		List<String> tipos = new ArrayList<String> (); 
		List<String> values = new ArrayList<String> (); 
		
		for (String str : t.getAtraccionesReservadas()) {
				
			columnas.add("id_ticket");
			columnas.add("id_atraccion");
						
			tipos.add("Int");
			tipos.add("Int");
			
			values.add(t.getId().toString());
			values.add(atraccion.findBy("nombre", "=", str).getId().toString());
			contador += CRUD.insert("tickets_atraccion", columnas, tipos, values);
		}
		return contador;
	}

	private int insertTickets(Ticket t) throws SQLException {
		
		List<String> columnas = new ArrayList<String> ();
		List<String> tipos = new ArrayList<String> (); 
		List<String> values = new ArrayList<String> (); 
		
		columnas.add("monedas_gastadas");
		columnas.add("id_ticket");
		columnas.add("id_usuario");
		columnas.add("tiempo_gastado");
		
		tipos.add("Int");
		tipos.add("Int");
		tipos.add("Int");
		tipos.add("Double");
		
		values.add(t.getMonedasGastadas().toString());
		values.add(t.getId().toString());
		values.add(usuario.findBy("nombre", "=", t.getComprador()).getId().toString());
		values.add(t.getTiempoGastado().toString());
		return CRUD.insert("tickets_atraccion", columnas, tipos, values);
				
	}

	@Override
	public int update(Ticket t) throws SQLException {
		
		int contador = 0;
		List<String> columnas = new ArrayList<String> ();
		List<String> tipos = new ArrayList<String> (); 
		List<String> values = new ArrayList<String> (); 
				
		for (String str : t.getAtraccionesReservadas()) {
				
			columnas.add("id_ticket");
			columnas.add("id_atraccion");
						
			tipos.add("Int");
			tipos.add("Int");
			
			values.add(t.getId().toString());
			values.add(atraccion.findBy("nombre", "=", str).getId().toString());
			contador += CRUD.update("tickets_atraccion", columnas, tipos, values);
		}
		
		List<String> columnas2 = new ArrayList<String> ();
		List<String> tipos2 = new ArrayList<String> (); 
		List<String> values2 = new ArrayList<String> (); 
		
		for (String str : t.getPromocionesReservadas()) {
				
			columnas2.add("id_ticket");
			columnas2.add("id_promocion");
			
			tipos2.add("Int");
			tipos2.add("Int");
			
			values2.add(t.getId().toString());
			values2.add(promocion.findBy("nombre", "=", str).getId().toString());
			contador += CRUD.update("tickets_promociones", columnas2, tipos2, values2);
		}
		
		List<String> columnas3 = new ArrayList<String> ();
		List<String> tipos3 = new ArrayList<String> (); 
		List<String> values3 = new ArrayList<String> (); 
		
		columnas3.add("monedas_gastadas");
		columnas3.add("id_ticket");
		columnas3.add("id_usuario");
		columnas3.add("tiempo_gastado");
		
		tipos3.add("Int");
		tipos3.add("Int");
		tipos3.add("Int");
		tipos3.add("Double");
		
		values3.add(t.getMonedasGastadas().toString());
		values3.add(t.getId().toString());
		values3.add(usuario.findBy("nombre", "=", t.getComprador()).getId().toString());
		values3.add(t.getTiempoGastado().toString());
		contador += CRUD.update("tickets_atraccion", columnas3, tipos3, values3);
		return contador;
		
		
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
