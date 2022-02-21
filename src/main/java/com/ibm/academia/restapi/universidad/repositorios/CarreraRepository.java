package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Long>{

	public Iterable<Carrera> findCarreraByNombreContains(String nombre);
	public Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre);
	public Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios);
	
	@Query("select c from Carrera c join fetch c.profesores p where p.nombre = ?1 and p.apellido = ?2")
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
