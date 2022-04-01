package es.unican.is2.impuestoCirculacion.common.dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;

public class TurismoTest {
	
	//Objeto para realizar las pruebas
	private Turismo sut;
	
	@Before
	public void setUp() throws OperacionNoValida {
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(2) , 0.00); 
	}

	@Test
	public void testConstructorTurismo() throws OperacionNoValida {
		//Casos Validos
		
		//C1
		
		assertTrue(sut.getFechaMatriculacion().equals(LocalDate.now().minusYears(2)));
		assertTrue(sut.getMatricula()=="0000ABC");
		assertTrue(sut.getPotencia()==0.00);
		
		//C2
		
		sut = new Turismo("0000ABC", LocalDate.now() , 50.00);
		
		assertTrue(sut.getFechaMatriculacion().equals(LocalDate.now()));
		assertTrue(sut.getMatricula()=="0000ABC");
		assertTrue(sut.getPotencia()==50.00);
		
		//Casos No Validos
		
		
		//C1
		
		try {
			sut = new Turismo(null, LocalDate.now().minusYears(2) , 50.00);
			fail("Debe fallar si la matricula es NULL");
		} catch (NullPointerException e) {
		}	
		
		
		//C2
		
		try {
			sut = new Turismo("0000ABC", LocalDate.now().plusDays(1) , 50.00);
			fail("Debe fallar si la fecha de matriculacion es posterior al dia actual");
		} catch (OperacionNoValida e) {
		}	
		
		//C3
		
		try {
			sut = new Turismo("0000ABC", LocalDate.now().plusYears(10) , 50.00);
			fail("Debe fallar si la fecha de matriculacion es posterior al dia actual");
		} catch (OperacionNoValida e) {
		}	
		
		//C4
		try {
			sut = new Turismo("0000ABC", LocalDate.now() , -900);
			fail("Debe fallar si la potencia es negativa");
		} catch (OperacionNoValida e) {
		}	
		
		//C5
		try {
			sut = new Turismo("0000ABC", LocalDate.now().minusYears(2) , -0.01);
			fail("Debe fallar si la potencia es negativa");
		} catch (OperacionNoValida e) {
		}	
		
	}
	
	@Test
	public void testPrecioImpuestoTurismo() throws OperacionNoValida {
		//C1
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(25) , 10.00);
		assertTrue(sut.precioImpuesto()==0.00);
		
		//C2
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(50) , 14.00);
		assertTrue(sut.precioImpuesto()==0.00);
		
		//C3
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(25).plusDays(1) , 0.00);
		assertTrue(sut.precioImpuesto()==25.24);
		
		//C4
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 4.00);
		assertTrue(sut.precioImpuesto()==25.24);
		
		//C5
		sut = new Turismo("0000ABC", LocalDate.now() , 7.99);
		assertTrue(sut.precioImpuesto()==25.24);
		
		//C6
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 8.00);
		assertTrue(sut.precioImpuesto()==68.16);
		
		//C7
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 11.99);
		assertTrue(sut.precioImpuesto()==68.16);
		
		//C8
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 12.00);
		assertTrue(sut.precioImpuesto()==143.88);
		
		//C9
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 15.99);
		assertTrue(sut.precioImpuesto()==143.88);
		
		//C10
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 16.00);
		assertTrue(sut.precioImpuesto()==179.22);
		
		//C11
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 18.00);
		assertTrue(sut.precioImpuesto()==179.22);
		
		//C12
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 19.99);
		assertTrue(sut.precioImpuesto()==179.22);
		
		//C13
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 20.00);
		assertTrue(sut.precioImpuesto()==224.00);
		
		//C14
		sut = new Turismo("0000ABC", LocalDate.now().minusYears(13) , 55.82);
		assertTrue(sut.precioImpuesto()==224.00);

	}
}
