package Archivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Set;
import java.util.TreeSet;

public class Archivo {
	private String nombre;
	
	public Archivo(String nombre) {
		this.nombre = nombre;
	}
	
	public LinkedList<Usuarios> leerArchivoUsuarios() {
		Scanner scanner = null;
		LinkedList<Usuarios> listaUsuarios = new LinkedList<Usuarios>();
		
		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);
			
			scanner.useLocale(Locale.ENGLISH);
			
			
			//int cant = scanner.nextInt();
			//scanner.nextLine(); // lees el salto de linea
			
			while(scanner.hasNextLine()) {
				String nombre = scanner.nextLine(); // esto leeria el nombre
				double presupuesto = scanner.nextDouble(); 
				double tiempoDisponible = scanner.nextDouble();
				scanner.nextLine(); // salto de linea
				
				String nombreTipo = scanner.nextLine(); // esto leeria el enum como string??
				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo);
				
				Usuarios usuarioAux = new Usuarios(nombre, presupuesto, tiempoDisponible, tipo);
				listaUsuarios.add(usuarioAux);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return listaUsuarios;
	}
	
	public Set<Atracciones> leerArchivoAtracciones() {
		Scanner scanner = null;
		Set<Atracciones> setAtracciones = new TreeSet<Atracciones>();
		
		try {
			File file = new File("casos de prueba/in/" + this.nombre + ".in");
			scanner = new Scanner(file);
			
			scanner.useLocale(Locale.ENGLISH);
					
			while(scanner.hasNextLine()) {
				String nombre = scanner.nextLine(); // esto leeria el nombre
				double precio = scanner.nextDouble(); 
				double duracion = scanner.nextDouble();
				int cupoMaximo = scanner.nextInt();
				scanner.nextLine(); // salto de linea
				
				String nombreTipo = scanner.nextLine(); // esto leeria el enum como string??
				TipoDeAtraccion tipo = Enum.valueOf(TipoDeAtraccion.class, nombreTipo);
				
				Atracciones atraccionAux = new Atracciones(nombre, precio, duracion, cupoMaximo, tipo);
				setAtracciones.add(atraccionAux);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return setAtracciones;
	}
}
