package Clases;

import java.io.File;

import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

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

	public LinkedList<Atraccion> cargarArchivoAtracciones() {

		LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
		Scanner scanner = null;

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine();
				String vectordeDatos[] = lineaDeDatos.split("\t");

				String nombre = vectordeDatos[0];
				double precio = Double.parseDouble(vectordeDatos[1]);
				double duracion = Double.parseDouble(vectordeDatos[2]);
				int cupoMaximo = Integer.parseInt(vectordeDatos[3]);
				String nombreTipo = vectordeDatos[4].toUpperCase();

				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo);

				Atraccion atraccionAux = new Atraccion(nombre, precio, duracion, cupoMaximo, tipo);
				listaAtracciones.add(atraccionAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return listaAtracciones;
	}
	
	public LinkedList<Promocion> cargarArchivoPromociones(List<Atraccion> listaDeAtracciones) {

		LinkedList<Promocion> listaPromociones = new LinkedList<Promocion>();
		Scanner scanner = null;

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine();
				String vectordeDatos[] = lineaDeDatos.split("\t");
				
				String nombre=vectordeDatos[1];
				String nombreTipo = vectordeDatos[2].toUpperCase();
				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo);
				
				List<String> atraccionesIncluidas =new LinkedList<String>();
				for(int i=3;i<vectordeDatos.length-1;i++)
					atraccionesIncluidas.add(vectordeDatos[i]);
				
				double precio=0;
				double tiempo=0;
				int cupo=0;
				for(Atraccion atraccion: listaDeAtracciones) {
					for(String nombreAtraccion: atraccionesIncluidas) {
						if (atraccion.getNombre().equals(nombreAtraccion)){
							precio += atraccion.getCosto();
							tiempo += atraccion.getTiempo();
							if (cupo == 0)
								cupo = atraccion.getCupo();
							else
								cupo = Math.min(cupo, atraccion.getCupo());
						}
					}
				}
				
				Promocion promocion=null;
				
				switch(vectordeDatos[0].toUpperCase()) {
				case "PORCENTUAL":
					double descuento=Double.parseDouble(vectordeDatos[vectordeDatos.length-1]);
					promocion=new PromocionPorcentual(nombre,cupo, tiempo, tipo, atraccionesIncluidas, precio, descuento);
					break;
					
				case "ABSOLUTA":
					precio=Double.parseDouble(vectordeDatos[vectordeDatos.length-1]);
					promocion=new PromocionesAbsolutas(nombre,cupo, tiempo, tipo, atraccionesIncluidas, precio);
					break;
					
				case "AXB":
					String atraccionGratis =vectordeDatos[vectordeDatos.length-1];
					for(Atraccion atraccion: listaDeAtracciones) {
						if(atraccion.getNombre().equals(atraccionGratis)) {
							precio+=atraccion.getCosto();
							tiempo+=atraccion.getTiempo();
							cupo=Math.min(cupo, atraccion.getCupo());
						}
					}
					promocion=new PromocionesAxB(nombre,cupo, tiempo, tipo, atraccionesIncluidas, precio,atraccionGratis);
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
}
