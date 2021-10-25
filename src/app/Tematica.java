package app;

public enum Tematica {
	AVENTURA("Aventura"), 
	PAISAJE("Paisaje"), 
	DEGUSTACION("Desgutaci�n"), 
	EXTREMO("Extremo"),
	ENTRETENIMIENTO("Entretenimiento"),
	CARRERA("Carrera"); 

	private String nombre;

	private Tematica(String nombre) {
		this.nombre = nombre;
	}

	// Valida que las tem�ticas en el archivo concuerden con las de este enum.
	// Tira un error si una tem�tica en el archivo no corresponde con las del enum.
	public static Tematica toTematica(String nombre) throws NoExisteTematicaException {
		nombre = nombre.toLowerCase()
				.replace('�', 'a')
				.replace('�', 'e')
				.replace('�', 'i')
				.replace('�', 'o')
				.replace('�', 'u');
		
		return Tematica.valueOf(nombre.toUpperCase());
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	
	public static Tematica toTematica(Integer id) throws NoExisteTematicaException {
		
		
		return Tematica.values()[id-1];
	}
	
	

}