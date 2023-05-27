package Clases;

import java.util.List;

public interface Recomendacion {

	public int getCupo();
	
	public double getPrecio();
	
	public double getTiempo();
	
	public TipoDeAtraccion getTipoDeRecomendacion();
	
	public void actualizarRecomendaciones(List<Atraccion> listaAtracciones,List<Promocion> listaPromociones);
	
	public boolean recomendacionValida(Usuario usuario);
	
	public void agregarRecomendacionAItinerario(Usuario usuario);
}
