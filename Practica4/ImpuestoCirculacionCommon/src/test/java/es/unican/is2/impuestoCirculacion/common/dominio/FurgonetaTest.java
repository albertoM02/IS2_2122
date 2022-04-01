package es.unican.is2.impuestoCirculacion.common.dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;

public class FurgonetaTest {

	//Objeto para realizar las pruebas
	private Furgoneta sut;
	
	@Before
	public void setUp() throws OperacionNoValida {
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(2) , 0.00, false); 
	}

	@Test
	public void testConstructor() throws OperacionNoValida {
		//Casos Validos
		
		//C1
		
		assertTrue(sut.getFechaMatriculacion().equals(LocalDate.now().minusYears(2)));
		assertTrue(sut.getMatricula()=="0000ABC");
		assertTrue(sut.getPotencia()==0.00);
		
		//C2
		
		sut = new Furgoneta("0000ABC", LocalDate.now() , 50.00, false);
		
		assertTrue(sut.getFechaMatriculacion().equals(LocalDate.now()));
		assertTrue(sut.getMatricula()=="0000ABC");
		assertTrue(sut.getPotencia()==50.00);
		
		//Casos No Validos
		
		
		//C1
		
		try {
			sut = new Furgoneta(null, LocalDate.now().minusYears(2) , 50.00, false);
			fail("Debe fallar si la matricula es NULL");
		} catch (NullPointerException e) {
		}	
		
		
		//C2
		
		try {
			sut = new Furgoneta("0000ABC", LocalDate.now().plusDays(1) , 50.00, false);
			fail("Debe fallar si la fecha de matriculacion es posterior al dia actual");
		} catch (OperacionNoValida e) {
		}	
		
		//C3
		
		try {
			sut = new Furgoneta("0000ABC", LocalDate.now().plusYears(10) , 50.00, false);
			fail("Debe fallar si la fecha de matriculacion es posterior al dia actual");
		} catch (OperacionNoValida e) {
		}	
		
		//C4
		try {
			sut = new Furgoneta("0000ABC", LocalDate.now() , -900, false);
			fail("Debe fallar si la potencia es negativa");
		} catch (OperacionNoValida e) {
		}	
		
		//C5
		try {
			sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(2) , -0.01, false);
			fail("Debe fallar si la potencia es negativa.");
		} catch (OperacionNoValida e) {
		}	
		
	}
	
	@Test
	public void testPrecioImpuestoFurgoneta() throws OperacionNoValida {
		//C1
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(25) , 10.00, false);
		assertTrue(sut.precioImpuesto()==0.00);
		
		//C2
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(50) , 14.00, false);
		assertTrue(sut.precioImpuesto()==0.00);
		
		//C3
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(25).plusDays(1) , 0.00, false);
		assertTrue(sut.precioImpuesto()==25.24);
		
		//C4
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 4.00, false);
		assertTrue(sut.precioImpuesto()==25.24);
		
		//C5
		sut = new Furgoneta("0000ABC", LocalDate.now() , 7.99, false);
		assertTrue(sut.precioImpuesto()==25.24);
		
		//C6
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13), 8.00, false);
		assertTrue(sut.precioImpuesto()==68.16);
		
		//C7
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 11.99, false);
		assertTrue(sut.precioImpuesto()==68.16);
		
		//C8
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 12.00, false);
		assertTrue(sut.precioImpuesto()==143.88);
		
		//C9
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 15.99, false);
		assertTrue(sut.precioImpuesto()==143.88);
		
		//C10
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 16.00, false);
		assertTrue(sut.precioImpuesto()==179.22);
		
		//C11
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 18.00, true);
		assertTrue(sut.precioImpuesto()==143.376);
		
		//C12
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 19.99, false);
		assertTrue(sut.precioImpuesto()==179.22);
		
		//C13
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 20.00, false);
		assertTrue(sut.precioImpuesto()==224.00);
		
		//C14
		sut = new Furgoneta("0000ABC", LocalDate.now().minusYears(13) , 55.82, false);
		assertTrue(sut.precioImpuesto()==224.00);

	}
}
