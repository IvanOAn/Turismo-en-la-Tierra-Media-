package Clases;

import java.util.HashMap;

public class Itinerario {
	private HashMap<String, Atraccion> itinerario;

	public Itinerario() {
		this.itinerario = new HashMap<String, Atraccion>();
	}

	public void agregarRecomendacion(Recomendacion recomendacion) {
		recomendacion.agregarRecomendacionAItinierario(this);
	}

	public boolean recomendacionDisponibleParaOfrecer(Recomendacion recomendacion) {
		return !recomendacion.recomendacionIncluyeAtraccionComprada(this);
		// devuelve true (es valida, se puede ofrecer) si no incluye ninguna atraccion ya comprada
	}

	public HashMap<String, Atraccion> getItinerario() {
		return this.itinerario;
	}
}
