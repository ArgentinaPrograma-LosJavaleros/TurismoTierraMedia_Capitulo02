package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import controller.AtraccionController;
import controller.PromocionController;
import controller.TematicaController;
import controller.UsuarioController;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.Tematica;
import model.Usuario;

public class App {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, NoExisteTematicaException {
		
		/*
		boolean existe = false;
		Scanner input = new Scanner(System.in);

		Sistema.setUsuarios(Archivo.cargarUsuarios());
		Collections.sort(Sistema.getUsuarios());
		Sistema.mostrarUsuarios();
		Sistema.setAtracciones(Archivo.cargarAtracciones());
		Sistema.setPromociones(Archivo.cargarPromociones());

		do {
			System.out.println("");
			System.out.print("Ingrese su Nombre de Usuario: ");
			String nombre = input.nextLine();
			existe = Sistema.login(new Usuario(nombre));
		} while (!existe);

		System.out.println("");
		Collections.sort(Sistema.getAtracciones(), new OrdenadorPorTematica());
		Sistema.mostrarAtracciones();

		System.out.println("");
		Collections.sort(Sistema.getPromociones(), new OrdenadorPorTematica());
		Sistema.mostrarPromociones();

		System.out.println("");
		Sistema.cargarOfertas();

		input.close();
		*/
		
		UsuarioController user = new UsuarioController();
		AtraccionController atraccion = new AtraccionController();
		PromocionController promocion = new PromocionController();
//		TematicaController tematica = new TematicaController();
		
//		System.out.println(tematica.insert(new Tematica("Carrera")));
			
//		System.out.println("Cantidad de Usuarios = " + user.countAll());
		
//		Sistema.setUsuarios(Archivo.cargarUsuarios());
//		
//		for (Usuario u: Sistema.getUsuarios())
//			user.insert(u);
//		
//		System.out.println(user.insert(new Usuario(null, "Puflito", 99999, 9.0, tematica.findBy("nombre", "=", "'Extremo'"))));
//		
//		System.out.println(user.insert(new Usuario(null, "Chiruzi", 65, 4.0, tematica.findBy("nombre", "=", "\"Degustacion\""))));
//		
		for (Usuario u : user.findAll())
			System.out.println(u);
				
//		System.out.println(user.insert(new Usuario(null, "Gandalf", 100, 5.0, 2)));
//		System.out.println(user.insert(new Usuario(null, "Puflito", 99999, 9.0, 4)));
//		System.out.println(user.update(new Usuario(1, "Chiruzi", 65, 4.0, 3)));
//		
//		System.out.println(user.insert(new Usuario(null, "Chiruzi", 65, 4.0, Tematica.DEGUSTACION )));
//		System.out.println(user.insert(new Usuario(null, "Chiruzi2", 65, 4.0, Tematica.DEGUSTACION )));
		
		//System.out.println(user.delete(new Usuario(18, "Chiruzi", 65, 4.0, Tematica.DEGUSTACION )));
		//System.out.println(user.deleteBy("nombre", "String", "Chiruzi"));
		
//		Sistema.setAtracciones(Archivo.cargarAtracciones());
//		Sistema.setPromociones(Archivo.cargarPromociones());
//		
//		for (Atraccion a: Sistema.getAtracciones())
//			atraccion.insert(a);
		for (Atraccion a: atraccion.findAll())
			System.out.println(a);
//		int contador = 1;
//		for (Promocion p: Sistema.getPromociones()) {
//			p.setId(contador);
//			promocion.insert(p);
//			contador++;
//		}
		for (Promocion p: promocion.findAll())
			System.out.println(p);
		
		ConnectionProvider.closeConnection();
	}
}
