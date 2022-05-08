package es.unican.is2.gestionTienda;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VendedorEnPracticasTest {
	
	private static vendedorEnPracticas sut;

	@Before
	public void setUp(){
		sut = new vendedorEnPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("1", sut.getId());
		assertEquals("Ana", sut.getNombre());
		assertEquals("11111111A", sut.getDni());
		assertTrue(sut.getTotalVentas()==0.0);
	}
	
	@Test
	public void testSetTotalVentas() {
		sut.setTotalVentas(100);
		assertTrue(sut.getTotalVentas()==100.0);
		
		sut.setTotalVentas(230);
		assertTrue(sut.getTotalVentas()==230.0);
		
		sut.setTotalVentas(0);
		assertTrue(sut.getTotalVentas()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhade(200);
		assertTrue(sut.getTotalVentas() == 200.0);
		
		sut.anhade(300);
		assertTrue(sut.getTotalVentas() == 500.0);	
		
		sut.anhade(0);
		assertTrue(sut.getTotalVentas() == 500.0);
		
		
	}
	
	@Test
	public void testEquals() {
		vendedorEnPracticas igual = new vendedorEnPracticas("Ana", "1", "11111111A");
		vendedorEnPracticas distintoId = new vendedorEnPracticas("Ana", "2", "11111111A");
		vendedorEnPracticas distintoNombre = new vendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertEquals(sut, igual);
		assertNotSame(sut, distintoId);
		assertNotSame(sut, distintoNombre);
	}
	
	
	
}
