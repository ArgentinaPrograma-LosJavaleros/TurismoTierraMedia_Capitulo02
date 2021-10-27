package app;

public class Tematica {
//	AVENTURA("Aventura"), 
//	PAISAJE("Paisaje"), 
//	DEGUSTACION("Desgutaci�n"), 
//	EXTREMO("Extremo"),
//	ENTRETENIMIENTO("Entretenimiento"),
//	CARRERA("Carrera"); 
	private Integer id;
	private String nombre;

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
	
	private Tematica(String nombre) {
		this.nombre = nombre;
	}

	// Valida que las tem�ticas en el archivo concuerden con las de este enum.
	// Tira un error si una tem�tica en el archivo no corresponde con las del enum.
			
	@Override
	public String toString() {
		return this.nombre;
	}
	
}