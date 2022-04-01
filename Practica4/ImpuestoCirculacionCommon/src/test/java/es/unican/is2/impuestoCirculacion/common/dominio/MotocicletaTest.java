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
	
	@Test
	public void testPrecioImpuestoMotocicleta() throws OperacionNoValida {
		//C1
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(25) , 75);
		assertTrue(sut.precioImpuesto()==0.00);
		
		//C2
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(50) , 190);
		assertTrue(sut.precioImpuesto()==0.00);
		
		//C3
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(25).plusDays(1) , 0);
		assertTrue(sut.precioImpuesto()==8.84);
		
		//C4
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 125);
		assertTrue(sut.precioImpuesto()==8.84);
		
		//C5
		sut = new Motocicleta("0000ABC", LocalDate.now() , 126);
		assertTrue(sut.precioImpuesto()==15.14);
		
		//C6
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 250);
		assertTrue(sut.precioImpuesto()==15.14);
		
		//C7
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 251);
		assertTrue(sut.precioImpuesto()==30.30);
		
		//C8
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 375);
		assertTrue(sut.precioImpuesto()==30.30);
		
		//C9
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 500);
		assertTrue(sut.precioImpuesto()==30.30);
		
		//C10
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 501);
		assertTrue(sut.precioImpuesto()==60.58);
		
		//C11
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 750);
		assertTrue(sut.precioImpuesto()==60.58);
		
		//C12
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 1000);
		assertTrue(sut.precioImpuesto()==60.58);
		
		//C13
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 1001);
		assertTrue(sut.precioImpuesto()==121.16);
		
		//C14
		sut = new Motocicleta("0000ABC", LocalDate.now().minusYears(13) , 2694);
		assertTrue(sut.precioImpuesto()==121.16);

	}

}
