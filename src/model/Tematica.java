package model;

public class Tematica {
	
//	AVENTURA("Aventura"), 
//	PAISAJE("Paisaje"), 
//	DEGUSTACION("Desgutación"), 
//	EXTREMO("Extremo"),
//	ENTRETENIMIENTO("Entretenimiento"),
//	CARRERA("Carrera"); 
	
	private Integer id;
	private String nombre;
	
	public Tematica(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Tematica(String nombre) {
		this(0, nombre);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Valida que las temáticas en el archivo concuerden con las de este enum.
	// Tira un error si una temática en el archivo no corresponde con las del enum.
			
	@Override
	public String toString() {
		return this.nombre;
	}
	
}