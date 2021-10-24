package model;

import java.util.ArrayList;

import app.TipoPromocion;
 
public class PromoAxB extends Promocion {

	private Atraccion atraccionGratis;

	public PromoAxB(Integer idPromocion, String nombrePromocion, Atraccion atraccionGratis, ArrayList<Atraccion> atracciones) {
		super(idPromocion, nombrePromocion, TipoPromocion.AXB, atracciones);
		this.setAtraccionGratis(atraccionGratis);
		super.tiempo += this.atraccionGratis.getTiempo();
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	@Override
	public String toString() {
		
		System.out.printf("| Nombre = %-30s"
                        + "| Atracciones = %-50s"
                        + "| Atracción gratis = %-29s"
                        + "| Precio Final = %-5d"
                        + "| Temática = %-15s |", 
                        super.getNombre(),
                        getNombreAtracciones(),
                        getAtraccionGratis().getNombre(),
                        super.getCosto(),
                        getTematica());
		return "";
	}
	
	@Override
	public String mostrarSugerible() {
		super.mostrarSugerible();
		mostrarBeneficio(getAtraccionGratis().getNombre() + " ¡GRATIS! :D");
		return "";
	}

}
