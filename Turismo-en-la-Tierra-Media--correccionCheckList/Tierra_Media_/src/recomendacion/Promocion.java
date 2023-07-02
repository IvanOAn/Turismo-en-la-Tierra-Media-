package recomendacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import usuario.Usuario;

public class Promocion extends Recomendacion {

	protected List<Atraccion> atraccionesIncluidas;

	// -- Constructor --
	public Promocion(String nombre, List<Atraccion> atraccionesIncluidas) {
		super(nombre, 0, 0, null);
		this.tipoDeAtraccion = atraccionesIncluidas.get(0).getTipoDeAtraccion();
		this.atraccionesIncluidas = atraccionesIncluidas;
		for (Atraccion atraccion : atraccionesIncluidas) {
			this.duracion += atraccion.getDuracion();
			this.precio += atraccion.getPrecio();
		}
	}

	// --Métodos--
	public List<String> toNombre() {
		List<String> listaNombres = new ArrayList<String>();
		for (Atraccion atraccion : this.atraccionesIncluidas)
			listaNombres.add(atraccion.getNombre());
		return listaNombres;
	}

	// -- Overrides --
	@Override
	public String toString() {
		return "\nPromocion: " + nombre + "\n" + "-Atracciones incluidas: " + this.toNombre() + "\n-Duración: "
				+ duracion + " horas";
	}

	@Override
	public int getCupo() {

		int cupo = atraccionesIncluidas.get(0).getCupo();

		for (Atraccion atraccion : atraccionesIncluidas) {
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
	public void agregarRecomendacionAItinierario(Usuario usuario) {
		for (Atraccion atraccion : this.atraccionesIncluidas) {
			atraccion.agregarRecomendacionAItinierario(usuario);
		}
	}

	@Override
	public boolean recomendacionIncluyeAtraccionComprada(Usuario usuario) {
		for (Atraccion atraccion : this.atraccionesIncluidas) {
			if (atraccion.recomendacionIncluyeAtraccionComprada(usuario))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionesIncluidas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesIncluidas, other.atraccionesIncluidas);
	}

}