package Clases;

import java.io.File;

import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
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
			File file = new File("casos de prueba/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine(); // lees una linea del archivo y la copias al string
				String vectordeDatos[] = lineaDeDatos.split("\t"); // pasas el string a un vector

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

	public LinkedList<Atraccion> cargarArchivoAtracciones(Map<String, Atraccion> mapaAtracciones) {

		LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
		Scanner scanner = null;

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String lineaDeDatos = scanner.nextLine(); // copias una linea del archivo en un string
				String vectordeDatos[] = lineaDeDatos.split("\t"); // pasas ese string a un vector

				String nombre = vectordeDatos[0]; // asignas el nombre
				double precio = Double.parseDouble(vectordeDatos[1]); // asignas el precio
				double duracion = Double.parseDouble(vectordeDatos[2]); // asignas la duracion
				int cupoMaximo = Integer.parseInt(vectordeDatos[3]); // asignas el cupo
				String nombreTipo = vectordeDatos[4].toUpperCase(); // lees el tipo

				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo); // asignas el tipo

				Atraccion atraccionAux = new Atraccion(nombre, precio, duracion, cupoMaximo, tipo); // creas la atraccion
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
	
	public LinkedList<Promocion> cargarArchivoPromociones(Map<String, Atraccion> mapaAtracciones) {

		LinkedList<Promocion> listaPromociones = new LinkedList<Promocion>();
		Scanner scanner = null;

		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in"); // lees el archivo

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) { 
				String lineaDeDatos = scanner.nextLine(); // copias una linea en este string
				String vectordeDatos[] = lineaDeDatos.split("\t"); // guardas en este vector los datos de la linea
				
				String nombre=vectordeDatos[1]; // nombre del pack
				String nombreTipo = vectordeDatos[2].toUpperCase(); // tipo de atraccion
				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo); // lo pones en el tipo
				
				double precio=0;
				double tiempo=0;
				int cupo=0; 
				List<String> atraccionesIncluidas =new LinkedList<String>(); // lista de atracciones de la oferta
				for(int i=3;i<vectordeDatos.length-1;i++) { // el for no lee la ultima posicion
					atraccionesIncluidas.add(vectordeDatos[i]); 
					Atraccion atraccionAux = mapaAtracciones.get(vectordeDatos[i]);
					
					if(atraccionAux != null) { // lo encontro
						precio += atraccionAux.getCosto();
						tiempo += atraccionAux.getTiempo();
						if(cupo == 0)
							cupo = atraccionAux.getCupo();
						else
							cupo = Math.min(cupo, atraccionAux.getCupo());
					}
					else
						System.out.println("La atraccion incluida en la oferta no esta incluida en el archivo atracciones");
				}
				
				
				/*
				for(Atraccion atraccion: listaDeAtracciones) { // recorres la lista de atracciones
					for(String nombreAtraccion: atraccionesIncluidas) { // recorres la lista de atracciones incluidas en la oferta
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
				*/
				
				Promocion promocion=null;
				
				switch(vectordeDatos[0].toUpperCase()) {
				case "PORCENTUAL": // si era porcentual, la ultima posicion era el descuento
					double descuento=Double.parseDouble(vectordeDatos[vectordeDatos.length-1]);
					promocion=new PromocionPorcentual(nombre,cupo, tiempo, tipo, atraccionesIncluidas, precio, descuento);
					break;
					
				case "ABSOLUTA": // si es absoluta, la ultima posicion es el precio
					precio=Double.parseDouble(vectordeDatos[vectordeDatos.length-1]);
					promocion=new PromocionesAbsolutas(nombre,cupo, tiempo, tipo, atraccionesIncluidas, precio);
					break;
					
				case "AXB": // si era AxB entonces la ultima posicion era la atraccion gratis
					String atraccionGratis =vectordeDatos[vectordeDatos.length-1];
					
					Atraccion atraccionAux = mapaAtracciones.get(atraccionGratis);
					if(atraccionAux != null) {
						precio+=atraccionAux.getCosto();
						tiempo+=atraccionAux.getTiempo();
						cupo=Math.min(cupo, atraccionAux.getCupo());
					}
						
					/*
					for(Atraccion atraccion: listaDeAtracciones) {
						if(atraccion.getNombre().equals(atraccionGratis)) {
							precio+=atraccion.getCosto();
							tiempo+=atraccion.getTiempo();
							cupo=Math.min(cupo, atraccion.getCupo());
						}
					}
					*/
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
