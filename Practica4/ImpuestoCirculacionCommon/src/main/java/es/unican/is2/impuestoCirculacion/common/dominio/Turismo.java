package es.unican.is2.impuestoCirculacion.common.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;

@SuppressWarnings("serial")
public class Turismo 
    extends Vehiculo implements Serializable 
{

	private double potencia;
	
	public Turismo(String matricula , LocalDate fechaMatriculacion, double potencia) throws OperacionNoValida {
		super(matricula, fechaMatriculacion);
		if (potencia < 0.00) {
			throw new OperacionNoValida("La potencia no puede ser negativa.");
		}
		this.potencia = potencia;
		//Caso Matriculado en el futuro ya tratado en Superclase
	}


	/**
	 * Retorna la potencia del turismo
	 * @return potencia en caballos fiscales
	 */
    public double getPotencia() {
        return potencia;
    }
       
    
    /**
     * Retorna el precio del impuesto a pagar
     *  @return precio
     */
	@Override
    public double precioImpuesto() {
		LocalDate fechaMatriculacion = this.getFechaMatriculacion();
	    LocalDate actualidad = LocalDate.now();
	    Period diferenciaTiempo = Period.between(fechaMatriculacion, actualidad);
		if(diferenciaTiempo.getYears() >= 25) {
			return 0.0; //Caso vehiculos viejos no pagan.
		}
		if (potencia < 8.00) {
			return 25.24;
		} else if (potencia <= 11.99) {
			return 68.16;
		} else if (potencia <= 15.99 ) {
			return 143.88;
		} else if (potencia <=19.99) {
			return 179.22;
		} else {
			return 224.00;
		}
    }
    
}
