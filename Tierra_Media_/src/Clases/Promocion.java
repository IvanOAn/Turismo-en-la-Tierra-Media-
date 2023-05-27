package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Promocion implements Recomendacion {
	
	protected String nombre;
	protected int cupo;
	protected double tiempoRequerido;
	protected TipoDeAtraccion tipoDeAtraccion;
	//protected HashMap<String, Atraccion> atraccionesIncluidas;
	protected List<String> atraccionesIncluidas;
	protected double precio;

	//-- Constructor --
/*	public Promocion(ArrayList<Atraccion> atraccionesIncluidas) {
		this.atraccionesIncluidas = new HashMap<>();
		this.tipoDeAtraccion = atraccionesIncluidas.get(0).getTipoDeAtraccion();
		for (Atraccion atraccion : atraccionesIncluidas) {
			this.atraccionesIncluidas.put(atraccion.getNombre(), atraccion);
			this.tiempoRequerido += atraccion.getTiempo();
			this.precio += atraccion.getCosto();
			if (cupo == 0)
				cupo = atraccion.getCupo();
			else
				cupo = Math.min(cupo, atraccion.getCupo());
		}
	}*/
	
	//Esto seria si calculamos precio y cupo en la clase archivo
	public Promocion(String nombre,int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas) {
		this.nombre= nombre;
		this.cupo = cupo;
		this.tiempoRequerido = tiempoRequerido;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.atraccionesIncluidas = atraccionesIncluidas;
	}
	

	///-- Gettters --
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoRequerido() {
		return tiempoRequerido;
	}

	public String getNombre() {
		return nombre;
	}

	public List<String> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	//-- Setters --
	protected void setCupo(int cupo) {
		this.cupo = cupo;
	}

/*	//-- Métodos --
	public HashMap<String, Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}
*/
/*	public static Set<Promocion> promocionesDisponibles(Usuario usuario, Set<Promocion> treeSet) {
		Set<Promocion> res = new TreeSet<>();

		for (Promocion promocion : treeSet) {
			if (promocion.getPrecio() <= usuario.getPresupuesto()
					&& promocion.getTiempoRequerido() <= usuario.getTiempoDisponible() && promocion.getCupo() > 0
					&& !usuario.promocionValida(promocion))
				res.add(promocion);
		}
		return res;
	}*/

	public boolean atraccionEstaEnPromocion(HashSet<String> itinerario) {
		for (String palabra : atraccionesIncluidas) {
			if (itinerario.contains(palabra))
				return false;
		}
		return true;
	}

	//-- Overrides --
	
	@Override
	public String toString() {
		return "Promocion: "+ nombre + "\n" +
				"-Tiempo requerido: " + tiempoRequerido + "\n" + 
				"-Atracciones incluidas: " + atraccionesIncluidas + "\n";		
	}


@Override
public double getPrecio() { 
	return this.precio;
}


@Override
public double getTiempo() {
	return this.tiempoRequerido;
}


@Override
public int getCupo() {
	return this.cupo;
}


@Override
public TipoDeAtraccion getTipoDeRecomendacion() {
	return this.tipoDeAtraccion;
}

@Override
public void actualizarRecomendaciones(List<Atraccion> listaAtracciones,List<Promocion> listaPromociones) {
	Iterator<Atraccion> iterador=listaAtracciones.iterator();
	
	for(String nombre: this.atraccionesIncluidas) {
		while(iterador.hasNext()) {
			Atraccion atraccion=iterador.next();
			if(atraccion.getNombre().equals(nombre)) {
				atraccion.decrementarCupo();
				if(atraccion.getCupo()==0)
					listaAtracciones.remove(atraccion);
			}
		}
	}
	
	Iterator<Promocion> it=listaPromociones.iterator();
	while(it.hasNext()) {
		Promocion promocion=it.next();
		if(promocion.getNombre().equals(this.nombre)) {
			this.cupo-=1;
			if(promocion.getCupo()==0)
				listaPromociones.remove(promocion);
		}
	}
}

@Override
public boolean recomendacionValida(Usuario usuario) {
	HashSet<String> itinerario=usuario.getItinerario();
	for (String palabra : atraccionesIncluidas) {
		if (itinerario.contains(palabra))
			return false;
	}
	return true;
}


@Override
public void agregarRecomendacionAItinerario(Usuario usuario) {
	Iterator<String> iterador =this.atraccionesIncluidas.iterator();
	while(iterador.hasNext())
		usuario.agregarRecomendacion(iterador.next());
}


public void decrementarCupo() {
	this.cupo-=1;
}


public void recalcularCupo(List<Atraccion> listaAtracciones) {
	int cupo=0;
	
	for(String nombreAtraccion:this.atraccionesIncluidas){
		for(Atraccion atraccion: listaAtracciones) {
			if(cupo==0)
				cupo=atraccion.getCupo();
			else
				cupo=Math.min(cupo, atraccion.getCupo());
		}
	}
	this.cupo=cupo;
}



}

