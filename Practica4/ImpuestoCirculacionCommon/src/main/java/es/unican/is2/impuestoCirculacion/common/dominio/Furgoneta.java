package es.unican.is2.impuestoCirculacion.common.dominio;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;
@SuppressWarnings("serial")
public class Furgoneta
    extends Turismo implements Serializable
{
    

    private boolean comercial;
    
    public Furgoneta(String matricula, LocalDate fechaMatriculacion, double potencia, boolean comercial) throws OperacionNoValida {
		super(matricula, fechaMatriculacion, potencia);
		this.comercial = comercial;
	}
    
   /**
    * Retorna el valor del atributo comercial
    * @return true si la furgoneta es de uso comercial
    *         false si no es de uso comercial
    */
    public boolean getComercial() {
    	return comercial;
    }
        
	@Override
    public double precioImpuesto() {
		LocalDate fechaMatriculacion = this.getFechaMatriculacion();
	    LocalDate actualidad = LocalDate.now();
	    Period diferenciaTiempo = Period.between(fechaMatriculacion, actualidad);
	    double bonificacion = 1;
	    double potencia = this.getPotencia();
	    //Caso vehiculo comercial
	    if (comercial == true) {
	    	//Si son comerciales pagan el 80% (bonificacion del 20%)
	    	bonificacion = 0.80;
	    }
		if(diferenciaTiempo.getYears() >= 25) {
			return 0.0; //Caso vehiculos viejos no pagan.
		}
		if (potencia < 8.00) {
			return 25.24 * bonificacion;
		} else if (potencia >= 8.00 && potencia < 12.00) {
			return 68.16 * bonificacion;
		} else if (potencia >= 12.00 && potencia < 16.00 ) {
			return 143.88 * bonificacion;
		} else if (potencia >= 16.00 && potencia < 20.00) {
			return 179.22 * bonificacion;
		} else if (potencia >= 20.00) {
			return 224.00 * bonificacion;
		}
		//error
		return -1;
    	
    }
}
