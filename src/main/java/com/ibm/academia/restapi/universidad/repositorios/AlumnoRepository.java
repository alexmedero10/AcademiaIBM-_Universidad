package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@Repository("repositorioAlumno")
public interface AlumnoRepository extends PersonaRepository{

	/**
	 * Metodo para buscar alumnos por el nombre de su carrera
	 * @param nombreCarrera Parametro de el nombre de la carrera
	 * @return Iterador de objetos Persona de tipo Alumno
	 */
	@Query("select a from Alumno a join fetch a.carrera c where c.nombre = ?1")
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera);
	
	public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId);
	
}
