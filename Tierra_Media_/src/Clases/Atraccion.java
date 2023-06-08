package Clases;

import java.util.HashSet;

public class Atraccion extends Recomendacion{

	//private String nombre;
	//private double costo;
	//private double tiempo;
	private int cupo;
	//private TipoDeAtraccion tipoDeAtraccion;

	// -- Constructor --
	public Atraccion(String nombre, double precio, double duracion, TipoDeAtraccion tipoDeAtraccion, int cupo) {
		super(nombre,precio,duracion,tipoDeAtraccion);
		this.cupo = cupo;
	}

	// -- Getters --

	public int getCupo() {
		return cupo;
	}
	
	// -- Overrides --

	@Override
	public String toString() {
		return "\nAtraccion: " + this.nombre + "\n" + "-Precio: $" + precio + "\n" + "-Tiempo Requerido: " + duracion + "\n"
				+ "-Tipo de atraccion: " + tipoDeAtraccion;
	}

	/*@Override
	public boolean recomendacionValida(Usuario usuario) {
		HashSet<String> itinerario = usuario.getItinerario();
		if (itinerario.contains(this.nombre))
			return false;
		return true;
	}

	@Override
	public void agregarRecomendacionAItinerario(Usuario usuario) {
		usuario.agregarRecomendacion(this.nombre);
	}*/

/*	@Override
	public void actualizarRecomendaciones(List<Atraccion> listaAtracciones,List<Promocion> listaPromociones){
		Iterator<Atraccion> iterador=listaAtracciones.iterator();
		
		while(iterador.hasNext()) {
			Atraccion atraccion=iterador.next();
			if(atraccion.getNombre().equals(this.nombre)) {
				if(atraccion.getCupo()==0)
					iterador.remove();			}
		}
		
		
	}*/
	
	@Override
	public void decrementarCupo() {
		this.cupo -= 1;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

/*	@Override
	public int hashCode() {
		return Objects.hash(cupo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return cupo == other.cupo;
	}*/
}