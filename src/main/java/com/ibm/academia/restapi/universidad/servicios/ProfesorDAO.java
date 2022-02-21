package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface ProfesorDAO extends PersonaDAO{
	
	/**
	 * Método para buscar profesores por carrera
	 * @param carrera Parámetro del nombre de la carrera
	 * @return Iterador de objeto Persona
	 */
	public Iterable<Persona> findProfesoresByCarrera(String carrera);
	
	/**
	 * Método para actualizar un profesor
	 * @param profesorId Id del profesor a actualizar
	 * @param profesor Objeto con datos para actualizar el profesor
	 * @return Profesor actualizada
	 * @author AML - 15-02-2022
	 */
	public Persona actualizar(Long profesorId, Persona profesor);
	
}
