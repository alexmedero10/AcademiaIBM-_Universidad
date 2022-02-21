package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera>{

	/**
	 * Método para buscar carreras que contengan en su nombre el parámetro
	 * @param nombre Parámetro de la palabra que debe de contener el nombre de la carrera
	 * @return Iterador de objeto Carrera
	 */
	public Iterable<Carrera> findCarreraByNombreContains(String nombre);
	
	/**
	 * Método para buscar carreras que contengan en su nombre el parámetro(NO CASE)
	 * @param nombre Parámetro de la palabra(NO CASE) que debe de contener el nombre de la carrera
	 * @return Iterador de objeto Carrera
	 */
	public Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre);
	
	/**
	 * Método para buscar carreras que tengan más años que el parámetro
	 * @param cantidadAnios Parámetro de los años de la carrera
	 * @return Iterador de objeto Carrera
	 */
	public Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios);
	
	/**
	 * Método para buscar carreras por nombre y apellido del profesor
	 * @param nombre Parámetro del nombre del profesor
	 * @param apellido Parámetro del apellido del profesor
	 * @return Iterador de objeto Carrera
	 */
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
	
	/**
	 * Método para actualizar una carrera
	 * @param carreraId Id de la carrera a actualizar
	 * @param carrera Objeto con datos para actualizar la carrera
	 * @return Carrera actualizada
	 * @author AML - 15-02-2022
	 */
	public Carrera actualizar(Long carreraId, Carrera carrera);
}
