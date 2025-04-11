package examen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Esta clase permite gestionar todos los carriles bici que conectan las
 * distintas localidades de la Bahía de Cádiz. Para ello, cuenta con un registro
 * de los carriles con su longitud en kilómetros, y otro registro de los
 * carriles con el estado en que se encuentran.
 * 
 * @version 2.4.0
 * @author CádizTech
 *         {https://institucional.cadiz.es/area/Plan-de-Movilidad-Urbana-Sostenible/2021}
 */
public class CarrilBiciManager {

	private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
	private final Map<String, String> estadoTramos; // nombre del tramo -> estado

	/**
	 * Constructor de la clase, que define el objeto creado con un mapa de tramos en
	 * el cual se recogen el nombre del tramo y la longitud del mismo en km, y un
	 * mapa estadoTramos en el cual se recogen el nombre del tramo y el estado del
	 * mismo
	 */
	public CarrilBiciManager() {
		this.tramos = new HashMap<>();
		this.estadoTramos = new HashMap<>();
	}

	/**
	 * Método empleado para añadir un tramo a los mapas de la clase
	 * 
	 * @param nombre   Nombre que tendrá el tramo que queremos añadir
	 * @param longitud Longitud en kilómetros que tendrá el tramo que queremos
	 *                 añadir
	 * @throws IllegalArgumentException si el nombre del tramo está vacio o la
	 *                                  longitud es inferior a 0
	 */
	public void añadirTramo(String nombre, double longitud) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
		}
		if (longitud <= 0) {
			throw new IllegalArgumentException("La longitud debe ser mayor que cero");
		}
		tramos.put(nombre, longitud);
		estadoTramos.put(nombre, "En servicio");
	}

	/**
	 * Método que actualiza el estado de un tramo, poniendo el nuevo que recibe como
	 * parámetro
	 * 
	 * @param nombre      Nombre del tramo cuyo estado queremos cambiar
	 * @param nuevoEstado Nuevo estado que queremos asignarle al tramo
	 * @throws NoSuchElementException Si el tramo no existe en el registro de tramos
	 */
	public void actualizarEstado(String nombre, String nuevoEstado) {
		if (!tramos.containsKey(nombre)) {
			throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
		}
		estadoTramos.put(nombre, nuevoEstado);
	}

	/**
	 * Método que cambia el estado de un tramo, poniendo el nuevo que recibe como
	 * parámetro
	 * 
	 * @param nombre Nombre del tramo cuyo estado queremos cambiar
	 * @param estado Nuevo estado que queremos asignarle al tramo
	 * @deprecated
	 */
	public void cambiarEstado(String nombre, String estado) {
		actualizarEstado(nombre, estado);
	}

	/**
	 * Método que comprueba el estado que tiene asignado el tramo que le pasamos
	 * como parámetro
	 * 
	 * @param nombre Nombre del tramo cuyo estado queremos consultar
	 * @return String con el estado del tramo
	 * @throws NoSuchElementException Si el tramo no existe en el registro de tramos
	 */
	public String consultarEstado(String nombre) {
		if (!estadoTramos.containsKey(nombre)) {
			throw new NoSuchElementException("El tramo indicado no existe");
		}
		return estadoTramos.get(nombre);
	}

	/**
	 * Métod que calcula la longitud total en kilómetros de todos los tramos
	 * registrados
	 * 
	 * @return Double con la longitud en kilómetros de todos los tramos registrados
	 */
	public double longitudTotal() {
		return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
	}

	/**
	 * Método que que devuelve una copia del mapa tramos, que registra cada tramo y
	 * su longitud en kilómetros
	 * 
	 * @return Map donde la clave es un String con el nombre de los tramos y el
	 *         valor un Double con su longitud en kilómetros
	 */
	public Map<String, Double> obtenerTramos() {
		return Collections.unmodifiableMap(tramos);
	}

	/**
	 * Método que genera un informe de todos los tramos, especificando su longitud y
	 * su estado
	 * 
	 * @return String Con el informe generado con toda la información de los tramos
	 *         registrados
	 */
	public String generarInforme() {
		StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
		sb.append("===========================================\n");
		for (String nombre : tramos.keySet()) {
			sb.append("- ").append(nombre).append(" (").append(tramos.get(nombre)).append(" km): ")
					.append(estadoTramos.get(nombre)).append("\n");
		}
		sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
		return sb.toString();
	}
}
