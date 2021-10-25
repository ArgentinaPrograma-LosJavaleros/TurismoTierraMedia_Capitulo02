package app;

public enum Tematica {
	AVENTURA("Aventura"), 
	PAISAJE("Paisaje"), 
	DEGUSTACION("Desgutación"), 
	EXTREMO("Extremo"),
	ENTRETENIMIENTO("Entretenimiento"),
	CARRERA("Carrera"); 

	private String nombre;

	private Tematica(String nombre) {
		this.nombre = nombre;
	}

	// Valida que las temáticas en el archivo concuerden con las de este enum.
	// Tira un error si una temática en el archivo no corresponde con las del enum.
	public static Tematica toTematica(String nombre) throws NoExisteTematicaException {
		nombre = nombre.toLowerCase()
				.replace('á', 'a')
				.replace('é', 'e')
				.replace('í', 'i')
				.replace('ó', 'o')
				.replace('ú', 'u');
		
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