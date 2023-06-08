package Clases;

import java.util.HashMap;

public class Itinerario {
	private HashMap<String, Atraccion> itinerario;

	public Itinerario() {
		this.itinerario = new HashMap<String, Atraccion>();
	}

	public HashMap<String, Atraccion> getItinerario() {
		return this.itinerario;
	}
}
