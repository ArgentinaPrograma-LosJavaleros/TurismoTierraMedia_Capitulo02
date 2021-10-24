package model;

import java.util.ArrayList;

import app.TipoPromocion;

public class PromoAbsoluta extends Promocion {

	// Constructor
	public PromoAbsoluta(Integer idPromocion, String nombrePromocion, Integer costoPromocion, ArrayList<Atraccion> atracciones) {
		super(idPromocion, nombrePromocion, TipoPromocion.ABSOLUTA, atracciones);
		super.setCosto(costoPromocion);
	}
	
	// Getter
	//----------------------------------------------
	public Integer getCostoAnterior() {
		double suma = 0.0;
		for (Atraccion a : super.getAtracciones()) {
			suma += a.getCosto();
		}
		return (int) Math.ceil(suma);
	}

	@Override
	public String toString() {

		System.out.printf("| Nombre = %-30s"
		                + "| Atracciones = %-50s"
		                + "| Costo Anterior = %-31d"
		                + "| Precio Final = %-5d"
		                + "| Temática = %-15s |", 
		                super.getNombre(),
		                getNombreAtracciones(),
		                getCostoAnterior(),
		                super.getCosto(),
		                getTematica());
		return "";
	}
	
	@Override
	public String mostrarSugerible() {
		super.mostrarSugerible();
		mostrarBeneficio("-$" + (getCostoAnterior() - getCosto()));
		return "";
	}

}
