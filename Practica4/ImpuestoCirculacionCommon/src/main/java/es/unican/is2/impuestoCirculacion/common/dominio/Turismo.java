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
		this.potencia = potencia;
		//Caso Matriculado en el futuro
		if (LocalDate.now().isBefore(fechaMatriculacion)) {
			throw new OperacionNoValida("La fecha de matriculacion no puede ser posterior a hoy");
		}
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
		if(diferenciaTiempo.getYears() > 25) {
			return 0.0; //Caso vehiculos viejos no pagan.
		}
		if (potencia < 8.00) {
			return 25.24;
		} else if (potencia >= 8.00 && potencia <= 11.99) {
			return 68.16;
		} else if (potencia >= 12.00 && potencia <= 15.99 ) {
			return 143.88;
		} else if (potencia >= 16.00 && potencia <=19.99) {
			return 179.22;
		} else if (potencia >= 20.00) {
			return 224.00;
		}
		//error
		return -1;
    }
    
}
