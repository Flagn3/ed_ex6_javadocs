package examen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CarrilBiciManager {

	private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
	private final Map<String, String> estadoTramos; // nombre del tramo -> estado

	public CarrilBiciManager() {
		this.tramos = new HashMap<>();
		this.estadoTramos = new HashMap<>();
	}

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

	public void actualizarEstado(String nombre, String nuevoEstado) {
		if (!tramos.containsKey(nombre)) {
			throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
		}
		estadoTramos.put(nombre, nuevoEstado);
	}

	public void cambiarEstado(String nombre, String estado) {
		actualizarEstado(nombre, estado);
	}

	public String consultarEstado(String nombre) {
		if (!estadoTramos.containsKey(nombre)) {
			throw new NoSuchElementException("El tramo indicado no existe");
		}
		return estadoTramos.get(nombre);
	}

	public double longitudTotal() {
		return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
	}

	public Map<String, Double> obtenerTramos() {
		return Collections.unmodifiableMap(tramos);
	}

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
