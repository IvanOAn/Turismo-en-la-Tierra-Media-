package recomendacion;

import org.junit.Assert;
import org.junit.Before;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class PromocionesAxBTest {
	private Atraccion atraccionGratis;
	private List<Atraccion> listaAtracciones;
	private PromocionesAxB promoAxB;

	@Before
	public void setUp() {
		this.listaAtracciones = new LinkedList<Atraccion>();

		listaAtracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		listaAtracciones.add(new Atraccion("Isildur", 25, 2, 5, TipoDeAtraccion.AVENTURA));
		this.atraccionGratis = new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA);

		this.promoAxB = new PromocionesAxB("Pack Aventura", listaAtracciones, atraccionGratis);
	}

	@Test
	public void devuelvePrecioCorrecto() {
		Assert.assertEquals(35, promoAxB.getPrecio(), 0.000001);
	}

	@Test
	public void devuelveTiempoCorrecto() {
		Assert.assertEquals(9, promoAxB.getDuracion(), 0.000001);
	}

	@Test
	public void devuelveCupoCorrecto() {
		Assert.assertEquals(5, promoAxB.getCupo());
	}
}