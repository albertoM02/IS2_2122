package es.unican.is2.gestionTienda;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parámetro al
 * crear la tienda
 */
public class Tienda {

	private static final double BONUSSENIOR = 0.01;
	private static final double BONUSJUNIOR = 0.005;
	private LinkedList<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { // WMC + 1
		this.datos = datos;
	}

	/**
	 * Retorna la dirección de la tienda
	 * 
	 * @return Dirección de la tienda
	 */
	public String direccion() { // WMC + 1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * 
	 * @return Nombre de la tienda
	 */
	public String nombre() { // WMC + 1
		return nombre;
	}

	/**
	 * Añade un nuevo vendedor a la tienda
	 * 
	 * @param nuevoVendedor
	 * @return true si el vendedor se ha anhadido false si ya había un vendedor con
	 *         el mismo id
	 */
	public boolean anhade(Vendedor nuevoVendedor) throws IOException { // WMC + 1
		Vendedor v = buscaVendedor(nuevoVendedor.getId());
		if (v != null) { // WMC + 1 //CCog + 1
			return false;
		}
		lista.add(nuevoVendedor);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo dni se pasa como parámetro
	 * 
	 * @param id
	 * @return true si se elimina el vendedor false si no existe ningún vendedor con
	 *         el id indicado
	 */
	public boolean eliminaVendedor(String id) throws IOException { // WMC + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) { // WMC + 1 //CCog + 1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Añade una venta a un vendedor
	 * 
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se añade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws IOException { // WMC + 1
		Vendedor vendedorenplantilla = buscaVendedor(id);
		if (vendedorenplantilla == null) { // WMC + 1 //CCog + 1
			return false;
		}
		double importeFinal = importe;

		if (((VendedorEnPlantilla) vendedorenplantilla).tipo() == TipoVendedor.JUNIOR) {
			importeFinal += importeFinal * BONUSJUNIOR;
		} else if (((VendedorEnPlantilla) vendedorenplantilla).tipo() == TipoVendedor.SENIOR) {
			importeFinal += importeFinal * BONUSSENIOR;
		}
		vendedorenplantilla.anhade(importeFinal);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese id o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) { // WMC + 1
		leeListaVendedores();
		for (Vendedor v : lista) { // WMC + 1
			if (v.getId().equals(id)) { // WMC + 1 //CCog + 2
				return v;
			}
		}
		return null;
	}

	/**
	 * Lee los vendedores junior del fichero pasado en función al formato del usado
	 * particularmente en esta práctica.
	 * 
	 * @param in
	 */
	private void leeJunior(Scanner in) { // WMC + 1
		Vendedor ven;
		while (in.hasNext() && !in.next().equals("Prácticas")) { // WMC + 2 //CCog + 2
			nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.JUNIOR);
			ven.setTotalVentas(totalVentas);
			lista.add(ven);
		}
		while (in.hasNext()) { // WMC + 1 //CCog + 1
			in.next();
			nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new vendedorEnPracticas(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			lista.add(ven);
		}
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() { // WMC + 1
		leeListaVendedores();
		return lista;

	}

	/**
	 * 
	 */
	private void leeListaVendedores() {
		lista = new LinkedList<Vendedor>();

		try (Scanner in = new Scanner(new FileReader(datos))) {
			// abre el fichero
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			// lee los vendedores senior
			leeSenior(in);
			// lee los vendedores junior
			leeJunior(in);
		} catch (FileNotFoundException e) { // CCog + 1

		} // try
	}

	/**
	 * Lee los vendedores senior del fichero pasado en función al formato del usado
	 * particularmente en esta práctica.
	 * 
	 * @param in
	 */
	private void leeSenior(Scanner in) { // WMC + 1
		Vendedor ven;
		while (in.hasNext() && !in.next().equals("Junior")) { // WMC + 2 //CCog + 2

			nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.SENIOR);
			ven.setTotalVentas(totalVentas);
			lista.add(ven);
		}
	}

	/**
	 * Método que actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws IOException { // WMC + 1

		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) { // WMC + 1 //CCog + 1
			if (v instanceof vendedorEnPracticas) { // WMC + 1 //CCog + 2
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) { // WMC + 1 //CCog + 2
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.tipo().equals(TipoVendedor.JUNIOR)) // WMC + 1 //CCog + 3
					junior.add(vp);
				else // CCog + 1
					senior.add(vp);
			}
		}

		muestraDatosVendedores(senior, junior, practicas);
	}

	/**
	 * @param senior
	 * @param junior
	 * @param practicas
	 * @throws IOException
	 */
	private void muestraDatosVendedores(List<Vendedor> senior, List<Vendedor> junior, List<Vendedor> practicas)
			throws IOException { // WMC + 1
		try (PrintWriter out = new PrintWriter(new FileWriter(datos))) {
			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) { // WMC + 1 //CCog + 1
				Vendedor v1 = (Vendedor) v;
				v1.muestraInfoVendedor(out);
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) { // WMC + 1 //CCog + 1
				Vendedor v2 = (Vendedor) v;
				v2.muestraInfoVendedor(out);
			}
			out.println();
			out.println("Prácticas");
			for (Vendedor v : practicas) { // WMC + 1 //CCog + 1
				vendedorEnPracticas v3 = (vendedorEnPracticas) v;
				v3.muestraInfoVendedor(out);
			}
		} catch (FileNotFoundException e) { // CCog + 1

		}
	}
}

// WMC = 35 //WMCn = 35/12 = 2,916... //CCog = 29
