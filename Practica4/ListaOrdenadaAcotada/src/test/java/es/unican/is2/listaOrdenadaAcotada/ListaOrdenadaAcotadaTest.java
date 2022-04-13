package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ListaOrdenadaAcotadaTest {

	//Objeto para realizar las pruebas
	private ListaOrdenadaAcotada<Integer> sut;
	
	@Before
	public void setUp()  {
		sut = new ListaOrdenadaAcotada<Integer>(10);
	}

	@Test
	public void testGet() {

		sut.add(3);
		sut.add(4);
		sut.add(2);
		System.out.print(sut.get(0));
		System.out.print(sut.get(1));
		System.out.print(sut.get(2));
		assertTrue(sut.get(0) == 3);
		assertTrue(sut.get(1) == 4);
		assertTrue(sut.get(2) == 2);


		
	}

}
