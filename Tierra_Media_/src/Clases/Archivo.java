package Clases;

import java.io.File;

import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class Archivo {
	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public Queue<Usuario> cargarArchivoUsuarios() {

		Queue<Usuario> colaDeUsuarios = new LinkedList<Usuario>();
		Scanner scanner = null;

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine();

				String vectordeDatos[] = lineaDeDatos.split("\t");

				String nombre = vectordeDatos[0];
				double presupuesto = Double.parseDouble(vectordeDatos[1]);
				double tiempoDisponible = Double.parseDouble(vectordeDatos[2]);
				String tipoAtraccionPreferida = vectordeDatos[3].toUpperCase();

				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, tipoAtraccionPreferida);

				Usuario usuario = new Usuario(nombre, presupuesto, tiempoDisponible, tipo);
				colaDeUsuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return colaDeUsuarios;
	}

	public LinkedList<Atraccion> leerArchivoAtracciones() {
		Scanner scanner = null;
		// Set<Atraccion> setAtracciones = new TreeSet<Atraccion>(new
		// ComparadorAtracciones(null));
		LinkedList<Atraccion> listaAtraccion = new LinkedList<Atraccion>();

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
				listaAtraccion.add(atraccionAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return listaAtraccion;
	}
}
