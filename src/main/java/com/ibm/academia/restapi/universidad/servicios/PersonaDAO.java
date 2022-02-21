package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface PersonaDAO extends GenericoDAO<Persona>{

	/**
	 * Método para buscar personas por nombre y apellido
	 * @param nombre Parámetro del nombre
	 * @param apellido Parámetro del apellido
	 * @return Optional de objeto Persona
	 */
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
	
	/**
	 * Método para buscar personas por dni
	 * @param dni Parámetro del dni
	 * @return Optional de objeto Persona
	 */
	public Optional<Persona> buscarPorDni(String dni);
	
	/**
	 * Método para buscar personas por apellido
	 * @param apellido Parámetro del apellido
	 * @return Iterador de objetos Persona
	 */
	public Iterable<Persona> buscarPersonaPorApellido(String apellido);

}
