package es.unican.is2.impuestoCirculacion.business.business;
import es.unican.is2.impuestoCirculacion.common.business.*;
import es.unican.is2.impuestoCirculacion.common.dao.*;
import es.unican.is2.impuestoCirculacion.common.dominio.*;
/**
 * Clase que implementa la capa de negocio de la aplicacion
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}
	
	public Contribuyente altaContribuyente(Contribuyente c) {
		contribuyentes.creaContribuyente(c);
		return c;
	}

	
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		
		Contribuyente cExiste = contribuyentes.eliminaContribuyente(dni);
		
		if (cExiste == null) {
			//Caso contribuyente no existe
			return null;
		}
		if (cExiste.getVehiculos().isEmpty() == false) {
			throw new OperacionNoValida("El contribuyente tiene coches a su nombre.");
		} else {
			return cExiste;
		}
		
	 }
	
	public Contribuyente contribuyente(String dni) {
		return contribuyentes.contribuyente(dni);
	}
	
	

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		Contribuyente c = contribuyentes.contribuyente(dni);
		if (c == null) {
			//Caso no existe el contribuyente.
			return null;
		}
		if (vehiculos.vehiculo(v.getMatricula()) == null) {
			//caso no existe vehículo
			return null;
		}
		if (c.getVehiculos().contains(v)) {
			throw new OperacionNoValida("El contribuyente ya posee este coche.");
		} else {
			c.getVehiculos().add(v);
		}
		return v;
	}

	
	
	
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		Contribuyente c = contribuyentes.contribuyente(dni);
		Vehiculo vBaja = vehiculos.vehiculo(matricula);
		if (c == null) {
			//Caso no existe el contribuyente.
			return null;
		}
		if (vBaja == null) {
			//caso no existe vehículo
			return null;
		}
		if (c.getVehiculos().contains(vBaja)) {
			c.getVehiculos().remove(vBaja);
			return vBaja;
		} else {
			throw new OperacionNoValida("El contribuyente no posee ese vehiculo.");
		} 
	}

	public Vehiculo vehiculo(String matricula) {
		return vehiculos.vehiculo(matricula);
	}	
}

