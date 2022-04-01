package es.unican.is2.impuestoCirculacion.common.dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;

public class FurgonetaTest {

	//Objeto para realizar las pruebas
	private Turismo sut;
	
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
		
		sut = new Turismo("0000ABC", LocalDate.now() , 50.00);
		
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
}
