package es.unican.is2.impuestoCirculacion.main;

import static org.junit.Assert.*;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacion.business.business.GestionImpuestoCirculacion;
import es.unican.is2.impuestoCirculacion.dao.dao.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.dao.VehiculosDAO;
import es.unican.is2.impuestoCirculacion.gui.business.VistaFuncionario;

public class RunnerTest {

	private FrameFixture demo;

	@Before
	public void setUp() {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		VistaFuncionario gui = new VistaFuncionario(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	

	@Test
	public void test() {
		// Comprobacion de aspectos de interfaz.
		demo.button("btnBuscar").requireText("Buscar");
		
		//Prueba de correcto funcionamiento.
		
		//Introducimos el DNI del primer contribuyente
		demo.textBox("txtDniContribuyente").enterText("11111111A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Pepe L�pez Mart�nez");
		demo.textBox("txtTotalContribuyente").requireText("448.0");
		
		demo.textBox("txtDniContribuyente").deleteText();
		demo.textBox("txtDniContribuyente").enterText("22222222B");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Ana P�rez L�pez");
		demo.textBox("txtTotalContribuyente").requireText("8.84");	
		
		demo.textBox("txtDniContribuyente").deleteText();
		demo.textBox("txtDniContribuyente").enterText("33333333C");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Luis Toca P�rez");
		demo.textBox("txtTotalContribuyente").requireText("249.24");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
