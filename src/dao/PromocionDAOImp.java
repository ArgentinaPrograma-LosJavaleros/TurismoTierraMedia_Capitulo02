package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.NoExisteTematicaException;
import model.Atraccion;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.Promocion;
import model.TipoPromocion;

public class PromocionDAOImp implements PromocionDAO {
	
	TematicaDAOImp tematica = new TematicaDAOImp();
	TipoPromocionDAOImp tipoPromo = new TipoPromocionDAOImp();
	AtraccionDAOImp atraccion = new AtraccionDAOImp();

	@Override
	public List<Promocion> findAll()  throws SQLException, NoExisteTematicaException{
		
		List<Promocion> listaDePromociones = new ArrayList<Promocion>();
		
		ResultSet rs = CRUD.select("Promociones", "*", "");
		
		while (rs.next()) {
			int id = rs.getInt("id_Promocion");
			int idFree = 0;
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			TipoPromocion tipoPromocion = tipoPromo.findById(rs.getInt("id_tipo_promocion"));
			ResultSet rs2 = CRUD.select("promo_atracciones", "id_atraccion", "id_promocion = " + id);
			while(rs2.next()) {
				if(rs2.getInt("gratis") != 1) {
					atracciones.add(atraccion.findById(rs2.getInt("id_atraccion")));					
				} else {
					idFree = rs2.getInt("id_atraccion");
				}
			}
			if(tipoPromocion.getId() == 1) {
				listaDePromociones.add(new PromoAbsoluta(
															id, 
															rs.getString("nombre"),
															rs.getInt("costo"),
															atracciones,
															tipoPromocion));
			}
			if(tipoPromocion.getId() == 2) {
				listaDePromociones.add(new PromoAxB(
															id, 
															rs.getString("nombre"),
															atraccion.findById(idFree),
															atracciones,
															tipoPromocion));
			}
			if(tipoPromocion.getId() == 3) {
				double oferton = 0.0;
				ResultSet rs3 = CRUD.select("promo_descuento", "descuento", "id_promocion = " + id);
				
				if(rs3.next())
					oferton = rs3.getDouble("descuento");
					
				listaDePromociones.add(new PromoPorcentual(
															id, 
															rs.getString("nombre"),
															oferton,
															atracciones,
															tipoPromocion));
			}
		}
		return listaDePromociones;
	}

	@Override
	public int countAll() throws SQLException {
		Integer contar = 0; 	
		ResultSet rs = CRUD.select("Promociones", "count(*)", "");
		contar = rs.getInt(1);
		return contar;
	}

	@Override
	public int insert(Promocion t) throws SQLException {
		return insertPromociones(t) + insertPromo_Atracciones(t) + insertPromoDatoExtra(t);
	}

	private int insertPromoDatoExtra(Promocion t) throws SQLException {
		List<String> columnas3 = new ArrayList <String> ();
		List<String> tipos3 = new ArrayList <String> (); 
		List<String> values3 = new ArrayList <String> (); 
		
		if(t.getTipoPromocion().getId() == 2) {
			
			Atraccion atraccion1 = atraccion.findBy(
					"nombre", "=", "\"" + ((PromoAxB)t).getAtraccionGratis().getNombre() + "\"");
			
			columnas3.add("gratis");
			columnas3.add("id_promocion");
			columnas3.add("id_atraccion");
			
			tipos3.add("Int");
			tipos3.add("Int");
			tipos3.add("Int");
			
			values3.add(String.valueOf(1));
			values3.add(t.getId().toString());
			values3.add(atraccion1.getId().toString());

			return CRUD.insertOrUpdate("promo_atracciones", columnas3, tipos3, values3);
		}
		
		if(t.getTipoPromocion().getId() == 3) {
			
			columnas3.add("descuento");
			columnas3.add("id_promocion");
			
			tipos3.add("Double");
			tipos3.add("Int");
			
			values3.add(((PromoPorcentual)t).getPorciento().toString());
			values3.add(t.getId().toString());
			
			return CRUD.insertOrUpdate("promo_descuento", columnas3, tipos3, values3);
		}
		
		return 0;
	}

	private int insertPromo_Atracciones(Promocion t) throws SQLException {
			
			Atraccion atraccion1 = atraccion.findBy(
					"nombre", "=", "\"" + t.getAtracciones().get(0).getNombre() + "\"");
			Atraccion atraccion2 = atraccion.findBy(
					"nombre", "=", "\"" + t.getAtracciones().get(1).getNombre() + "\"");
		
			List<String> columnas = new ArrayList <String> ();
			List<String> tipos = new ArrayList <String> (); 
			List<String> values = new ArrayList <String> ();
			
			List<String> columnas2 = new ArrayList <String> ();
			List<String> tipos2 = new ArrayList <String> (); 
			List<String> values2 = new ArrayList <String> ();	
			
			columnas.add("gratis");
			columnas.add("id_promocion");
			columnas.add("id_atraccion");
			
			columnas2.add("gratis");
			columnas2.add("id_promocion");
			columnas2.add("id_atraccion");
			
			tipos.add("Int");
			tipos.add("Int");
			tipos.add("Int");
			
			tipos2.add("Int");
			tipos2.add("Int");
			tipos2.add("Int");
			
			values.add(String.valueOf(0));
			values.add(t.getId().toString());
			values.add(atraccion1.getId().toString());
			
			values2.add(String.valueOf(0));
			values2.add(t.getId().toString());
			values2.add(atraccion2.getId().toString());
		
		return CRUD.insertOrUpdate("promo_atracciones", columnas, tipos, values) 
	         + CRUD.insertOrUpdate("promo_atracciones", columnas2, tipos2, values2);
	}

	private int insertPromociones(Promocion t) throws SQLException {
		List<String> columnas = new ArrayList <String> ();
		List<String> tipos = new ArrayList <String> (); 
		List<String> values = new ArrayList <String> (); 

		columnas.add("nombre");
		columnas.add("costo");
		columnas.add("id_tipo_promocion");
		
		tipos.add("String");
		tipos.add("Int");
		tipos.add("Int");
		
		values.add(t.getNombre());
		values.add(t.getCosto().toString());
		values.add(t.getTipoPromocion().getId().toString());

		return CRUD.insertOrUpdate("Promociones", columnas, tipos, values);
	}

	@Override
	public int update(Promocion t) throws SQLException {

		List<String> columnas = new ArrayList <String> ();
		List<String> tipos = new ArrayList <String> (); 
		List<String> values = new ArrayList <String> (); 
		
		List<String> columnas1 = new ArrayList <String> ();
		List<String> tipos1 = new ArrayList <String> (); 
		List<String> values1 = new ArrayList <String> (); 
		
		columnas1.add("id_promocion");
		columnas1.add("nombre");
		columnas1.add("costo");
		columnas1.add("id_tipo_promocion");
		
		tipos1.add("Int");
		tipos1.add("String");
		tipos1.add("Int");
		tipos1.add("Int");
		
		values1.add(t.getId().toString());
		values1.add(t.getNombre());
		values1.add(t.getCosto().toString());
		values1.add(t.getTipoPromocion().getId().toString());
		
		for(Atraccion a : t.getAtracciones()) {
			List<String> columnas2 = new ArrayList <String> ();
			List<String> tipos2 = new ArrayList <String> (); 
			List<String> values2 = new ArrayList <String> ();
			
			columnas2.add("id_promocion");
			columnas2.add("id_atraccion");
			columnas2.add("gratis");
			
			tipos2.add("Int");
			tipos2.add("Int");
			tipos2.add("Int");
			
			values2.add(t.getId().toString());
			values2.add(a.getId().toString());
			values2.add(String.valueOf(0));
			
			System.out.println(CRUD.insertOrUpdate("promo_atracciones", columnas2, tipos2, values2));
			
		}
		
		if(t.getTipoPromocion().getId() == 2) {
			
			columnas.add("id_promocion");
			columnas.add("id_atraccion");
			columnas.add("gratis");
			
			tipos.add("Int");
			tipos.add("Int");
			tipos.add("Int");
			
			values.add(t.getId().toString());
			values.add(((PromoAxB)t).getAtraccionGratis().getId().toString());
			values.add(String.valueOf(1));
			
			System.out.println(CRUD.insertOrUpdate("promo_atracciones", columnas, tipos, values));
		}
		
		if(t.getTipoPromocion().getId() == 3) {
			
			columnas.add("id_promocion");
			columnas.add("descuento");
			
			tipos.add("Int");
			tipos.add("Double");
			
			values.add(t.getId().toString());
			values.add(((PromoPorcentual)t).getPorciento().toString());
			
			System.out.println(CRUD.insertOrUpdate("promo_descuento", columnas, tipos, values));
			
		}
		
		return CRUD.insertOrUpdate("Promociones", columnas1, tipos1, values1);
	}

	@Override
	public int delete(Promocion t) throws SQLException {
		return CRUD.delete("Promociones", "id_Promocion", t.getId().toString(), "int");
	}

	@Override
	public int deleteBy(String campo, String tipo, String valor) throws SQLException {
		return CRUD.delete("Promociones", campo, tipo, valor);
	}

	@SuppressWarnings("null")
	@Override
	public Promocion findBy(String campo, String operador, String valor) throws SQLException {
		
		Promocion promocion = null;
		promocion.setNombre("No Existe");
		
		String consulta = campo + " " + operador + " " + valor;
		
		ResultSet rs = CRUD.select("Promociones", "*", consulta);
				
		if (rs.next()) {
			
			int id = rs.getInt("id_Promocion");
			int idFree = 0;
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			TipoPromocion tipoPromocion = tipoPromo.findById(rs.getInt("id_tipo_promocion"));
			ResultSet rs2 = CRUD.select("promo_atracciones", "id_atraccion", "id_promocion = " + id);

			while(rs2.next()) {
				if(rs2.getInt("gratis") != 1) {
					atracciones.add(atraccion.findById(rs2.getInt("id_atraccion")));					
				} else {
					idFree = rs2.getInt("id_atraccion");
				}
			}
			
			if(tipoPromocion.getId() == 1) {
				((PromoAbsoluta) promocion).setId(id);
				((PromoAbsoluta) promocion).setNombre(rs.getString("nombre"));
				((PromoAbsoluta) promocion).setCosto(rs.getInt("costo"));
				((PromoAbsoluta) promocion).setAtracciones(atracciones);
				((PromoAbsoluta) promocion).setTipoPromocion(tipoPromocion);
				
			}
			
			if(tipoPromocion.getId() == 2) {
				((PromoAxB) promocion).setId(id);
				((PromoAxB) promocion).setNombre(rs.getString("nombre"));
				((PromoAxB) promocion).setAtraccionGratis(atraccion.findById(idFree));
				((PromoAxB) promocion).setAtracciones(atracciones);
				((PromoAxB) promocion).setTipoPromocion(tipoPromocion);
			}
			
			if(tipoPromocion.getId() == 3) {
				double oferton = 0.0;
				ResultSet rs3 = CRUD.select("promo_descuento", "descuento", "id_promocion = " + id);
				
				if(rs3.next())
					oferton = rs3.getDouble("descuento");
					
				((PromoPorcentual) promocion).setId(id);
				((PromoPorcentual) promocion).setNombre(rs.getString("nombre"));
				((PromoPorcentual) promocion).setPorciento(oferton);
				((PromoPorcentual) promocion).setAtracciones(atracciones);
				((PromoPorcentual) promocion).setTipoPromocion(tipoPromocion);
			}
		}
		
		return promocion;
	}

	@Override
	public Promocion findById(int id) throws SQLException {
		return findBy("id_promocion", "=", String.valueOf(id));
	}

}