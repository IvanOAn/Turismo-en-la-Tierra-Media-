package Clases;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Recomendador {
	Queue<Usuario> colaDeUsuarios;
	List<Atraccion> listaDeAtracciones;
	List<Promocion> listaDePromociones;

	public Recomendador() {
		this.colaDeUsuarios =new LinkedList<Usuario>();
		this.listaDeAtracciones= new LinkedList<Atraccion>();
		this.listaDePromociones= new LinkedList<Promocion>();
	}
	
	public void cargarUsuarios() {
		Archivo archivoUsuarios= new Archivo("Usuarios");
		this.colaDeUsuarios=archivoUsuarios.cargarArchivoUsuarios();
	}
	
	public void cargarAtracciones() {
		Archivo archivoAtracciones= new Archivo("Atracciones");
		this.listaDeAtracciones=archivoAtracciones.cargarArchivoAtracciones();
	}
	
	public void cargarPromociones() {
		Archivo archivoPromociones= new Archivo("Promociones");
		this.listaDePromociones=archivoPromociones.cargarArchivoPromociones(listaDeAtracciones);
	}
	
}
