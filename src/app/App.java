package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.UsuarioController;
import jdbc.ConnectionProvider;
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

		input.close();*/
		
		UsuarioController user=new UsuarioController();
		
		for (Usuario u : user.findAll()) {
		System.out.println(u);
		}
	}
}
