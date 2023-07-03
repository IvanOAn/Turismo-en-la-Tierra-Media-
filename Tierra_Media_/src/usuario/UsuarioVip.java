package usuario;

import recomendacion.Recomendacion;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class UsuarioVip extends Usuario {
	
	private int cupones;
	private final int cuponesIniciales;

	public UsuarioVip(String nombre, double presupuesto, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionPreferida,int cupones) {
		super(nombre,presupuesto, tiempoDisponible, tipoDeAtraccionPreferida);
		this.cupones=cupones;
		this.cuponesIniciales=cupones;
	}
	
	public int getCupones() {
		return cupones;
	}
	
	public int getCuponesIniciales() {
		return this.cuponesIniciales;
	}

	@Override
	public void comprarRecomendacion(Recomendacion recomendacion){
		if(cupones>0)
		{
			recomendacion.agregarRecomendacionAItinierario(this);
			this.cupones--;
			this.tiempoDisponible -= recomendacion.getDuracion();
			recomendacion.decrementarCupo();
		}
		else {
			super.comprarRecomendacion(recomendacion);
		}
	}
	
	@Override
	public boolean esVip(){
		return true;
	}

}
