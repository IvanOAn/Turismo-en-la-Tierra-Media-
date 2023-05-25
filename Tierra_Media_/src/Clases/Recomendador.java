package Clases;

import java.util.LinkedList;
import java.util.Queue;

public class Recomendador {
	Queue<Usuario> ColaDeUsuarios;

	public Recomendador() {
		this.ColaDeUsuarios =new LinkedList<Usuario>();
	}
	
	public void cargarUsuarios() {
		Archivo archivoUsuarios= new Archivo("Usuarios");
		this.ColaDeUsuarios=archivoUsuarios.cargarArchivoUsuarios();
	}
}
