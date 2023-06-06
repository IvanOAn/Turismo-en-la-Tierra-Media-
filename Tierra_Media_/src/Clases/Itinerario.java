package Clases;

import java.util.HashMap;
import java.util.List;

public class Itinerario {
	private HashMap<String,Atraccion>itinerario;
	
	public Itinerario() {
		this.itinerario = new HashMap<String,Atraccion>();
	}

	public void agregarRecomendacion(Recomendacion recomendacion){
		if(recomendacion.esPromocion()){
			for(Atraccion atraccion:((Promocion)recomendacion).getAtraccionesIncluidas()) {
				this.itinerario.put(atraccion.getNombre(),atraccion);
			}
		}else {
			this.itinerario.put(((Atraccion)recomendacion).getNombre(),(Atraccion)recomendacion);
		}
	}
	
	public boolean recomendacionValida(Recomendacion recomendacion) {
		if(recomendacion.esPromocion()){
			for(Atraccion atraccion:((Promocion)recomendacion).getAtraccionesIncluidas()) {
				if(this.itinerario.containsKey(atraccion.getNombre()))
					return false;
			}
		}else {
			if(this.itinerario.containsKey(((Atraccion)recomendacion).getNombre()))
				return false;
		}
		return true;
	}

	public HashMap<String, Atraccion> getItinerario() {
		return this.itinerario;
	}
}
