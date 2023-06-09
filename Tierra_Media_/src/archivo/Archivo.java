package archivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import recomendacion.Atraccion;
import recomendacion.Promocion;
import recomendacion.PromocionesAbsolutas;
import recomendacion.PromocionesAxB;
import recomendacion.PromocionesPorcentual;
import tiposDeRecomendaciones.TipoDeAtraccion;
import usuario.Usuario;
import usuario.UsuarioVip;

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
				
				Usuario usuario = null;
				
				if(vectordeDatos[vectordeDatos.length-2].equals("VIP")){
					int cupones =Integer.parseInt(vectordeDatos[vectordeDatos.length-1]);
					usuario = new UsuarioVip(nombre, presupuesto, tiempoDisponible, tipo,cupones);
				}else{
					usuario = new Usuario(nombre, presupuesto, tiempoDisponible, tipo);
				}
				colaDeUsuarios.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return colaDeUsuarios;
	}

	public List<Atraccion> cargarArchivoAtracciones(Map<String, Atraccion> mapaAtracciones) {

		List<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
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
				listaAtracciones.add(atraccionAux);
				mapaAtracciones.put(nombre, atraccionAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return listaAtracciones;
	}

	public List<Promocion> cargarArchivoPromociones(Map<String, Atraccion> mapaAtracciones) {

		List<Promocion> listaPromociones = new LinkedList<Promocion>();
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
				}

				Promocion promocion = null;

				switch (vectordeDatos[0].toUpperCase()) {
				case "PORCENTUAL":
					double descuento = Double.parseDouble(vectordeDatos[vectordeDatos.length - 1]);
					promocion = new PromocionesPorcentual(nombre, atraccionesIncluidas, descuento);
					break;

				case "ABSOLUTA":
					double precio = Double.parseDouble(vectordeDatos[vectordeDatos.length - 1]);
					promocion = new PromocionesAbsolutas(nombre, atraccionesIncluidas, precio);
					break;

				case "AXB":
					Atraccion atraccionGratis = mapaAtracciones.get(vectordeDatos[vectordeDatos.length - 1]);
					promocion = new PromocionesAxB(nombre, atraccionesIncluidas, atraccionGratis);
					break;
				}
				listaPromociones.add(promocion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return listaPromociones;
	}

	public void generarArchivoResumenUsuario(Usuario usuario, Map<String, Atraccion> mapaAtracciones) {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			file = new FileWriter("archivos/out/" + this.nombre + ".out");
			printerWriter = new PrintWriter(file);

			printerWriter.println("Resumen del Usuario: " + usuario.getNombre());

			printerWriter.println("\nSituacion Inicial:");
			printerWriter.printf(" -%-15s\t$%.2f\n", "Presupuesto:", usuario.getPresupuestoInicial());
			printerWriter.printf(" -%-15s\t%.2f horas\n", "Tiempo Disponible:", usuario.getTiempoInicial());
			if(usuario.esVip()){
				printerWriter.printf(" -%-15s\t%d cupones\n", "Cupones Disponible:",((UsuarioVip) usuario).getCupones());
			}

			printerWriter.println("\nAtracciones compradas:");

			boolean usuarioNoComproNada = true;
			for (String nombreAtraccion : mapaAtracciones.keySet()) {
				if (usuario.tieneAtraccion(mapaAtracciones.get(nombreAtraccion))) {
					printerWriter.printf(" -%-32s\t", mapaAtracciones.get(nombreAtraccion).getNombre());
					printerWriter.printf("%.2f horas\n", mapaAtracciones.get(nombreAtraccion).getDuracion());
					usuarioNoComproNada = false;
				}
			}
			if (usuarioNoComproNada) {
				printerWriter.printf(" -%-32s\n", "No se aceptaron recomendaciones");
			}

			printerWriter.print("\nSituacion Final:");
			printerWriter.printf("\n -%-15s\t$%.2f\n", "Costo total:",
					usuario.getPresupuestoInicial() - usuario.getPresupuesto());
			printerWriter.printf(" -%-15s\t%.2f horas\n", "Duración total:",
					(usuario.getTiempoInicial() - usuario.getTiempoDisponible()));
			
			
			if(usuario.esVip()){
				UsuarioVip usuarioV= (UsuarioVip) usuario;
				
				printerWriter.printf(" -%-15s\t %d"," CuponesGastados \t",
						(usuarioV.getCuponesIniciales() - usuarioV.getCupones()));
			}

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