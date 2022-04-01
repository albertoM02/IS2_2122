package es.unican.is2.impuestoCirculacion.common.dominio;

import java.time.LocalDate;
import java.time.Period;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;

@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;
	
	public Motocicleta(String matricula, LocalDate fechaMatriculacion, int cilindrada) throws OperacionNoValida {
		super(matricula, fechaMatriculacion);
		if (cilindrada < 0) {
			throw new OperacionNoValida("La cilindrada no puede ser negativa.");
		}
		this.cilindrada = cilindrada;
	}


	

    /**
     * Retorna la cilindrada de la motocicleta
     * @return cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }
    
  
	@Override
    public double precioImpuesto() {
		LocalDate fechaMatriculacion = this.getFechaMatriculacion();
	    LocalDate actualidad = LocalDate.now();
	    Period diferenciaTiempo = Period.between(fechaMatriculacion, actualidad);
		if(diferenciaTiempo.getYears() > 25) {
			return 0.0; //Caso vehiculos viejos no pagan.
		}
		if (cilindrada <= 125) {
			return 8.84;
		} else if (cilindrada > 125 && cilindrada <= 250) {
			return 15.14;
		} else if (cilindrada > 250 && cilindrada <= 500) {
			return 30.30;
		} else if (cilindrada > 500 && cilindrada <= 1000) {
			return 60.58;
		} else if (cilindrada > 1000) {
			return 121.16;
		}
		//error
		return -1;
    }
}
