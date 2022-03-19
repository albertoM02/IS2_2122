package es.unican.is2.impuestoCirculacion.main;
import es.unican.is2.impuestoCirculacion.business.business.GestionImpuestoCirculacion;
import es.unican.is2.impuestoCirculacion.dao.dao.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacion.dao.dao.VehiculosDAO;
import es.unican.is2.impuestoCirculacion.gui.business.VistaFuncionario;

/**
 * Clase principal que construye la aplicación de tres capas y lanza su ejecución
 */
public class Runner {

	public static void main(String[] args) {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		// Componentes casa presentacion
		VistaFuncionario vista = new VistaFuncionario(negocio, negocio, negocio);
		
		// Lanza ejecución
		vista.setVisible(true);
	}

}
