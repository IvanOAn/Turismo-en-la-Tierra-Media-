package Clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Promocion extends Recomendacion{

	protected List<Atraccion> atraccionesIncluidas;

	// -- Constructor --
	public Promocion(String nombre, List<Atraccion> atraccionesIncluidas) {
		this.nombre=nombre;
		this.tipoDeAtraccion = atraccionesIncluidas.get(0).getTipoDeAtraccion();
		this.atraccionesIncluidas = atraccionesIncluidas;
		for (Atraccion atraccion : atraccionesIncluidas) {
			this.duracion += atraccion.getDuracion();
			this.precio += atraccion.getPrecio();
		}
	}

	/// -- Gettters --
	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}
	
	//--Métodos--
	public List<String> toNombre() {
		List<String> listaNombres = new ArrayList<String>();
		for (Atraccion atraccion : this.atraccionesIncluidas)
			listaNombres.add(atraccion.getNombre());
		return listaNombres;
	}

	// -- Overrides --
	@Override
	public String toString() {
		return "\nPromocion: " + nombre + "\n" + "-Atracciones incluidas: " + this.toNombre() 
				+ "\n-Duración: " + duracion;
	}

	@Override
	public int getCupo() {

		int cupo =atraccionesIncluidas.get(0).getCupo();
		
		for (Atraccion atraccion:atraccionesIncluidas) {
			cupo = Math.min(cupo, atraccion.getCupo());
		}
		return cupo;
	}

	@Override
	public void decrementarCupo() {
		for (Atraccion atraccion : this.atraccionesIncluidas) {
			atraccion.decrementarCupo();
		}
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesIncluidas, other.atraccionesIncluidas);
	}
}
