package Clases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Archivo {
	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public Queue<Usuario> cargarArchivoUsuarios() {

		Queue<Usuario> colaDeUsuarios = new LinkedList<Usuario>();
		Scanner scanner = null;

		try {
			File file = new File("archivos/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine();
				String vectordeDatos[] = lineaDeDatos.split("\t");

				String nombre = vectordeDatos[0]; // asignas el nombre
				double presupuesto = Double.parseDouble(vectordeDatos[1]); // asignas el presupuesto
				double tiempoDisponible = Double.parseDouble(vectordeDatos[2]); // asginas el tiempo
				String tipoAtraccionPreferida = vectordeDatos[3].toUpperCase(); // lees el tipo

				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, tipoAtraccionPreferida); // asignas el tipo

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

	public void cargarArchivoAtracciones(Map<String, Atraccion> mapaAtracciones,
			List<Recomendacion> listaRecomendaciones) {

		Scanner scanner = null;

		try {
			File file = new File("archivos/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine();
				String vectordeDatos[] = lineaDeDatos.split("\t");

				String nombre = vectordeDatos[0]; // asignas el nombre
				double precio = Double.parseDouble(vectordeDatos[1]); // asignas el precio
				double duracion = Double.parseDouble(vectordeDatos[2]); // asignas la duracion
				int cupoMaximo = Integer.parseInt(vectordeDatos[3]); // asignas el cupo
				String nombreTipo = vectordeDatos[4].toUpperCase(); // lees el tipo

				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo); // asignas el tipo

				Atraccion atraccionAux = new Atraccion(nombre, precio, duracion, cupoMaximo, tipo);
				listaRecomendaciones.add(atraccionAux);
				mapaAtracciones.put(nombre, atraccionAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	public void cargarArchivoPromociones(Map<String, Atraccion> mapaAtracciones,
			List<Recomendacion> listaRecomendaciones) {

		Scanner scanner = null;

		try {
			File file = new File("archivos/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine();
				String vectordeDatos[] = lineaDeDatos.split("\t");

				String nombre = vectordeDatos[1];

				List<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
				for (int i = 2; i < vectordeDatos.length - 1; i++) { // el for no lee la ultima posicion
					Atraccion atraccionAux = mapaAtracciones.get(vectordeDatos[i]);
					atraccionesIncluidas.add(atraccionAux);

					if (atraccionAux == null)
						System.out.println(
								"La atraccion incluida en la oferta no esta incluida en el archivo atracciones");
				}

				Promocion promocion = null;

				switch (vectordeDatos[0].toUpperCase()) {
				case "PORCENTUAL": // si era porcentual, la ultima posicion era el descuento
					double descuento = Double.parseDouble(vectordeDatos[vectordeDatos.length - 1]);
					promocion = new PromocionesPorcentual(nombre, atraccionesIncluidas, descuento);
					break;

				case "ABSOLUTA": // si es absoluta, la ultima posicion es el precio
					double precio = Double.parseDouble(vectordeDatos[vectordeDatos.length - 1]);
					promocion = new PromocionesAbsolutas(nombre, atraccionesIncluidas, precio);
					break;

				case "AXB": // si era AxB entonces la ultima posicion era la atraccion gratis
					Atraccion atraccionGratis = mapaAtracciones.get(vectordeDatos[vectordeDatos.length - 1]);
					promocion = new PromocionesAxB(nombre, atraccionesIncluidas, atraccionGratis);
					break;
				}
				listaRecomendaciones.add(promocion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	public void generarArchivoResumenUsuario(Usuario usuario) {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			file = new FileWriter("archivos/out/" + this.nombre + ".out");
			printerWriter = new PrintWriter(file);

			printerWriter.println("Resumen del Usuario: " + usuario.getNombre());

			printerWriter.println("\nSituacion Inicial:");
			printerWriter.printf(" -%-15s\t$%.2f\n", "Presupuesto:", usuario.getPresupuestoInicial());
			printerWriter.printf(" -%-15s\t%.2f horas\n", "Tiempo Disponible:", usuario.getTiempoInicial());

			printerWriter.println("\nAtracciones compradas:");

			HashMap<String, Atraccion> itinerario = usuario.getItinerario();
			if (itinerario.isEmpty()) {
				printerWriter.printf(" -%-32s\n", "No se aceptaron recomendaciones");
			} else {
				for (String nombre : itinerario.keySet()) {
					Atraccion atraccion = itinerario.get(nombre);
					printerWriter.printf(" -%-32s\t", atraccion.getNombre());
					printerWriter.printf("%.2f horas\n", atraccion.getDuracion());
				}
			}
			printerWriter.print("\nSituacion Final:");
			printerWriter.printf("\n -%-15s\t$%.2f\n", "Costo total:",
					usuario.getPresupuestoInicial() - usuario.getPresupuesto());
			printerWriter.printf(" -%-15s\t%.2f horas\n", "Duración total:",
					(usuario.getTiempoInicial() - usuario.getTiempoDisponible()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}