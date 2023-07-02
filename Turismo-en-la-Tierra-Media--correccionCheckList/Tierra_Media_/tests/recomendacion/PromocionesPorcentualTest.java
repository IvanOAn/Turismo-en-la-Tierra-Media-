package recomendacion;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class PromocionesPorcentualTest {

	@Test
	public void devuelvePrecioConPorcentajeAplicado() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Minas Tirith", 10, 3, 20, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Abismo de Helm", 25, 2, 50, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Erebor", 25, 2, 13, TipoDeAtraccion.PAISAJES));

		PromocionesPorcentual promoPorcentual = new PromocionesPorcentual("Pack Porcentual", atracciones, 50);

		Assert.assertEquals(30, promoPorcentual.getPrecio(), 0.000001);
	}

}