package recomendacion;

import java.util.Objects;

import tiposDeRecomendaciones.TipoDeAtraccion;
import usuario.Usuario;

public class Atraccion extends Recomendacion {

	private int cupo;

	// -- Constructor --
	public Atraccion(String nombre, double precio, double duracion, int cupo, TipoDeAtraccion tipoDeAtraccion) {
		super(nombre, precio, duracion, tipoDeAtraccion);
		this.cupo = cupo;
	}

	// -- Overrides --

	@Override
	public int getCupo() {
		return cupo;
	}

	@Override
	public String toString() {
		return "\nAtraccion: " + this.nombre + "\n" + "-Precio: $" + precio + "\n" + "-Duración: " + duracion
				+ " horas\n" + "-Tipo de atraccion: " + tipoDeAtraccion;
	}

	@Override
	public void decrementarCupo() {
		this.cupo -= 1;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public void agregarRecomendacionAItinierario(Usuario usuario) {
		usuario.agregaAtraccion(this);
	}

	@Override
	public boolean recomendacionIncluyeAtraccionComprada(Usuario usuario) {
		return usuario.tieneAtraccion(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cupo);
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
		Atraccion other = (Atraccion) obj;
		return cupo == other.cupo;
	}

}