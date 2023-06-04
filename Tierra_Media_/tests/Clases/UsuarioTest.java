package Clases;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTest {

	@Test
	public void devuelveBienEstado() {
		Usuario usuarioEstadoBien = new Usuario("Frodo",30,10,TipoDeAtraccion.AVENTURA);
		Usuario usuarioSinPresupuesto = new Usuario("Galardiel",0,5,TipoDeAtraccion.DEGUSTACION);
		Usuario usuarioSinTiempo = new Usuario("Galardiel",100,0,TipoDeAtraccion.DEGUSTACION);
		
		Assert.assertTrue(usuarioEstadoBien.estado());
		Assert.assertFalse(usuarioSinPresupuesto.estado());
		Assert.assertFalse(usuarioSinTiempo.estado());
		
	}
	
	/*
	@Test
	public void compraBienAtraccion() {
		Usuario usuario = new Usuario("Frodo",30,10,TipoDeAtraccion.AVENTURA);
		Atraccion atraccion = new Atraccion("Moria",10,2,6,TipoDeAtraccion.AVENTURA);
		List<String> listaAtraccionesIncluidas = new LinkedList<String>();
		listaAtraccionesIncluidas.add("Bosque Negro");
		listaAtraccionesIncluidas.add("Mordor");
		Promocion promocion = new Promocion("Pack aventura",1,5,TipoDeAtraccion.AVENTURA,listaAtraccionesIncluidas);
		
		List<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
		List<Promocion> listaPromociones = new LinkedList<Promocion>();
		HashMap<String,Atraccion> mapaAtracciones = new HashMap<String,Atraccion>();
		
		listaAtracciones.add(atraccion);
		mapaAtracciones.put(atraccion.getNombre(), atraccion);
		
		usuario.comprarRecomendacion(atraccion, listaAtracciones, listaPromociones, mapaAtracciones);
		
		Assert.assertEquals(0, 0, 0);
	}
	*/
//	public void comprarRecomendacion(Recomendacion recomendacion, List<Atraccion> listaAtracciones,
	//		List<Promocion> listaDePromociones, HashMap<String, Atraccion> mapaAtracciones) 
	
	@Test
	public void agregaBienRecomendacion() {
		Usuario usuario = new Usuario("Frodo",30,10,TipoDeAtraccion.AVENTURA);
		Atraccion atraccion = new Atraccion("Moria",10,2,6,TipoDeAtraccion.AVENTURA);
		
		usuario.agregarRecomendacion(atraccion.getNombre());
		
		Assert.assertTrue(usuario.getItinerario().contains(atraccion.getNombre()));
	}

}