package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ListaOrdenadaAcotadaTest {

	// Objeto para realizar las pruebas
	private ListaOrdenadaAcotada<Integer> sut;

	@Before
	public void setUp() {
		sut = new ListaOrdenadaAcotada<Integer>(10);
	}

	@Test
	public void testGet() {

		// Casos válidos

		sut.add(2);
		sut.add(3);
		sut.add(4);
		// C1
		assertTrue(sut.get(0) == 2);

		// C2
		assertTrue(sut.get(2) == 4);
		sut.add(9);
		sut.add(8);

		// C3
		assertTrue(sut.get(2) == 4);

		// Casos no válidos

		try {
			sut.get(-30);
			fail("Debe fallar si el índice excede.");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			sut.get(-1);
			fail("Debe fallar si el índice excede.");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			sut = new ListaOrdenadaAcotada<Integer>(5);
			sut.add(0);
			sut.add(1);
			sut.add(2);
			sut.add(3);
			sut.add(4);
			sut.get(5);
			fail("Debe fallar si el índice excede.");
		} catch (IndexOutOfBoundsException e) {

		}

		try {
			sut.get(15);
			fail("Debe fallar si el índice excede.");
		} catch (IndexOutOfBoundsException e) {

		}
	}

	@Test
	public void testAdd() {
		// Casos Válidos

		// C1
		sut.add(1);
		assertTrue(sut.get(0) == 1);

		// C2
		sut.add(4);
		sut.add(9);
		assertTrue(sut.get(0) == 1);
		assertTrue(sut.get(1) == 4);
		assertTrue(sut.get(2) == 9);

		// C3
		sut.add(3);
		assertTrue(sut.get(0) == 1);
		assertTrue(sut.get(1) == 3);
		assertTrue(sut.get(2) == 4);
		assertTrue(sut.get(3) == 9);

		// Casos No válidos

		// C1
		// Excedemos tamaño
		sut = new ListaOrdenadaAcotada<Integer>(3);
		sut.add(1);
		sut.add(4);
		sut.add(9);
		try {
			sut.add(6);
			fail("Debe fallar si el excede el tamaño.");
		} catch (IllegalStateException e) {

		}

		// C2
		// Añadimos null
		try {
			sut.add(null);
			fail("Debe fallar si el excede el tamaño.");
		} catch (IllegalStateException e) {

		}
	}

	@Test
	public void testRemove() {
		sut = new ListaOrdenadaAcotada<Integer>(3);
		sut.add(2);
		sut.add(3);
		sut.add(4);

		// Casos Válidos

		// C1
		assertTrue(sut.remove(0) == 2);
		assertTrue(sut.get(0) == 3);
		assertTrue(sut.get(1) == 4);

		// C2
		assertTrue(sut.remove(1) == 4);
		assertTrue(sut.get(0) == 3);

		// C3
		assertTrue(sut.remove(0) == 3);
		assertTrue(sut.size() == 0);

		// Casos No válidos

		// C1
		// Excedemos tamaño
		sut = new ListaOrdenadaAcotada<Integer>();
		sut.add(2);
		sut.add(3);
		sut.add(4);
		try {
			sut.remove(-30);
			fail("Debe fallar si indice excede los indices.");
		} catch (IndexOutOfBoundsException e) {

		}

		// C2
		try {
			sut.remove(-1);
			fail("Debe fallar si indice excede los indices.");
		} catch (IndexOutOfBoundsException e) {

		}

		// C3
		try {
			sut.remove(10);
			fail("Debe fallar si indice excede los indices.");
		} catch (IndexOutOfBoundsException e) {

		}

		// C4
		try {
			sut.remove(15);
			fail("Debe fallar si indice excede los indices.");
		} catch (IndexOutOfBoundsException e) {

		}
	}

	@Test
	public void testSize() {
		
		//C1
		
		sut = new ListaOrdenadaAcotada<Integer>();
		assertEquals(sut.size(), 0);
		
		//C2
		sut.add(2);
		sut.add(3);
		sut.add(4);
		sut.add(5);
		sut.add(6);
		assertEquals(sut.size(), 5);
		
	}
	
	@Test
	public void testClear() {
		
		//C1
		
		sut = new ListaOrdenadaAcotada<Integer>();
		sut.clear();
		assertEquals(sut.size(), 0);
		
		//C2
		sut.add(2);
		sut.add(3);
		sut.add(4);
		sut.add(5);
		sut.add(6);
		sut.clear();
		assertEquals(sut.size(), 0);
		
	}

}
