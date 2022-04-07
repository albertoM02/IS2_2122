package es.unican.is2.impuestoCirculacion.common.dominio;

import java.io.Serializable;
import java.time.LocalDate;

import es.unican.is2.impuestoCirculacion.common.business.OperacionNoValida;


@SuppressWarnings("serial")
public abstract class Vehiculo implements Serializable {
  
    private String matricula;
	private LocalDate fechaMatriculacion;	

	public Vehiculo(String matricula, LocalDate fechaMatriculacion) throws OperacionNoValida {
		if (matricula == null) {
			throw new NullPointerException();
		} else if (fechaMatriculacion.isAfter(LocalDate.now())) {
			throw new OperacionNoValida("La fecha de matriculacion es posterior a hoy.");
		}
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
	}


	/**
     * Retorna el valor del impuesto de circulacion
     *  @return valor del impuesto circulacion
     */
	public abstract double precioImpuesto (); 


	/**
	 * Retorna la matricula del vehiculo
	 * @return matricula
	 */
    public String getMatricula() {
		return matricula;
	}

    /**
     * Retorna la fecha de matriculacion del vehiculo
     * @return fecha de matriculacion
     */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true; //Si son el mismo elemento exacto
        if (o == null) return false;
        if (o.getClass() != this.getClass()) {
        	return false; //es de otra clase
        }
        if (((Vehiculo)o).matricula == this.matricula) {
        	return true; //es el mismo objeto
        }
			
		return false;
		
	}

}
