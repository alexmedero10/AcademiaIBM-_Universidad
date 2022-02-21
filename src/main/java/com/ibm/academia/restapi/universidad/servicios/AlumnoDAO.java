package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface AlumnoDAO extends PersonaDAO{
	/**
	 * Metodo para buscar alumnos por el nombre de su carrera
	 * @param nombreCarrera Parametro de el nombre de la carrera
	 * @return Iterador de objeto Persona
	 */
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera);
	
	/**
	 * Método para actualizar los datos de un Alumno
	 * @param alumnoId Id del Alumno a actualizar
	 * @param alumno Objeto con datos para actualizar el alumno
	 * @return Alumno actualizado
	 * @author AML - 15-02-2022
	 */
	public Persona actualizar(Long alumnoId, Persona alumno);
	
	/**
	 * Método para asociar una carrera y un alumno
	 * @param carreraId Id de la carrera
	 * @param alumnoId Id del alumno
	 * @return Alumno con carrera asociada
	 * @author AML - 15-02-2022
	 */
	public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId);
}
