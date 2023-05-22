package Clases;

import java.io.File;

import java.util.Locale;
import java.util.Scanner;
import java.util.LinkedList;

import java.util.Set;
import java.util.TreeSet;

public class Archivo {
	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public LinkedList<Usuario> leerArchivoUsuarios() {
		Scanner scanner = null;
		LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			// int cant = scanner.nextInt();
			// scanner.nextLine(); // lees el salto de linea

			while (scanner.hasNextLine()) {
				String nombre = scanner.nextLine(); // esto leeria el nombre
				double presupuesto = scanner.nextDouble();
				double tiempoDisponible = scanner.nextDouble();
				scanner.nextLine(); // salto de linea

				String nombreTipo = scanner.nextLine(); // esto leeria el enum como string??
				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo);

				Usuario usuarioAux = new Usuario(nombre, presupuesto, tiempoDisponible, tipo);
				listaUsuarios.add(usuarioAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return listaUsuarios;
	}

	public Set<Atraccion> leerArchivoAtracciones() {
		Scanner scanner = null;
		Set<Atraccion> setAtracciones = new TreeSet<Atraccion>();

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String nombre = scanner.nextLine(); // esto leeria el nombre
				double precio = scanner.nextDouble();
				double duracion = scanner.nextDouble();
				int cupoMaximo = scanner.nextInt();
				scanner.nextLine(); // salto de linea

				String nombreTipo = scanner.nextLine(); // esto leeria el enum como string??
				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo);

				Atraccion atraccionAux = new Atraccion(nombre, precio, duracion, cupoMaximo, tipo);
				setAtracciones.add(atraccionAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return setAtracciones;
	}
}
