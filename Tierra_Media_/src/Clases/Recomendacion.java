package Clases;

public interface Recomendacion {

	public int getCupo();

	public String getNombre();

	public double getPrecio();

	public double getTiempo();

	public void decrementarCupo();

	public TipoDeAtraccion getTipoDeRecomendacion();

	//public void actualizarRecomendaciones(List<Atraccion> listaAtracciones,List<Promocion> listaPromociones);

	public boolean recomendacionValida(Usuario usuario);

	public void agregarRecomendacionAItinerario(Usuario usuario);

	public int prioridad();
}
