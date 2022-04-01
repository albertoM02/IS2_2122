package es.unican.is2.impuestoCirculacion.common.dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;

public class MotocicletaTest {

	//Objeto para realizar las pruebas
	private Motocicleta sut;
	
	@Before
	public void setUp() throws OperacionNoValida {
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(2) , 0); 
	}

	@Test
	public void testConstructor() throws OperacionNoValida {
		//Casos Validos
		
		//C1
		
		assertTrue(sut.getFechaMatriculacion().equals(LocalDate.now().minusYears(2)));
		assertTrue(sut.getMatricula()=="0000ABC");
		assertTrue(sut.getCilindrada()==0);
		
		//C2
		
		sut = new Motocicleta("0000ABC", LocalDate.now() , 50);
		
		assertTrue(sut.getFechaMatriculacion().equals(LocalDate.now()));
		assertTrue(sut.getMatricula()=="0000ABC");
		assertTrue(sut.getCilindrada()==50);
		
		//Casos No Validos
		
		
		//C1
		
		try {
			sut = new Motocicleta(null, LocalDate.now().minusYears(2) , 50);
			fail("Debe fallar si la matricula es NULL");
		} catch (NullPointerException e) {
		}	
		
		
		//C2
		
		try {
			sut = new Motocicleta("0000ABC", LocalDate.now().plusDays(1) , 50);
			fail("Debe fallar si la fecha de matriculacion es posterior al dia actual");
		} catch (OperacionNoValida e) {
		}	
		
		//C3
		
		try {
			sut = new Motocicleta("0000ABC", LocalDate.now().plusYears(10) , 50);
			fail("Debe fallar si la fecha de matriculacion es posterior al dia actual.");
		} catch (OperacionNoValida e) {
		}	
		
		//C4
		try {
			sut = new Motocicleta("0000ABC", LocalDate.now() , -900);
			fail("Debe fallar si la potencia es negativa.");
		} catch (OperacionNoValida e) {
		}	
		
		//C5
		try {
			sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(2) , -1);
			fail("Debe fallar si la potencia es negativa.");
		} catch (OperacionNoValida e) {
		}	
		
	}

}