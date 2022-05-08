package es.unican.is2.gestionTienda;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VendedorEnPlantillaTest {
	
	private static VendedorEnPlantilla sutJunior;
	private static VendedorEnPlantilla sutSenior;

	
	@Before
	public void setUp(){
		sutJunior = new VendedorEnPlantilla("Ana", "1", "11111111A", TipoVendedor.JUNIOR);
		sutSenior = new VendedorEnPlantilla("Pepe", "2", "222222222A", TipoVendedor.SENIOR);
	}
	
	@Test
	public void testConstructor() {
		assertEquals("1", sutJunior.getId());
		assertEquals("11111111A", sutJunior.getDni());
		assertEquals("Ana", sutJunior.getNombre());
		assertEquals(TipoVendedor.JUNIOR, sutJunior.tipo());
		assertEquals(TipoVendedor.SENIOR, sutSenior.tipo());
		
	}

	@Test
	public void testAnhadeVenta() {
		
		sutJunior.anhade(200);
		assertEquals(200, sutJunior.getTotalVentas(), 0);
		
		sutJunior.anhade(300);
		assertEquals(500, sutJunior.getTotalVentas(), 0);
		
		sutSenior.anhade(300);
		assertEquals(300, sutSenior.getTotalVentas(), 0);
		
		sutSenior.anhade(300);
		assertEquals(600, sutSenior.getTotalVentas(), 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(2000, sutJunior.getTotalVentas(), 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(4000, sutJunior.getTotalVentas(), 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas(), 0);
		
		sutSenior.setTotalVentas(4500);
		assertEquals(4500, sutSenior.getTotalVentas(), 0);		
		sutSenior.setTotalVentas(4000);
		assertEquals(4000, sutSenior.getTotalVentas(), 0);
		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas(), 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorEnPlantilla igualJunior = new VendedorEnPlantilla("Ana", "1", "11111111A", TipoVendedor.JUNIOR);
		VendedorEnPlantilla distintoIdJunior = new VendedorEnPlantilla("Ana", "2", "11111111A", TipoVendedor.JUNIOR);
		VendedorEnPlantilla distintoDNIJunior = new VendedorEnPlantilla("Ana", "1", "222222222A", TipoVendedor.JUNIOR);
		
		assertEquals(sutJunior, igualJunior);
		assertNotSame(sutJunior, distintoIdJunior);
		assertNotSame(sutJunior, distintoDNIJunior);
		
		VendedorEnPlantilla igualSenior = new VendedorEnPlantilla("Pepe", "2", "222222222A", TipoVendedor.SENIOR);
		VendedorEnPlantilla distintoIdSenior = new VendedorEnPlantilla("Pepe", "3", "222222222A", TipoVendedor.SENIOR);
		VendedorEnPlantilla distintoDNISenior = new VendedorEnPlantilla("Pepe", "2", "33333333A", TipoVendedor.SENIOR);
		
		assertEquals(sutSenior, igualSenior);
		assertNotSame(sutSenior, distintoIdSenior);
		assertNotSame(sutSenior, distintoDNISenior);
		
		
		
		
	}
	
	
}
