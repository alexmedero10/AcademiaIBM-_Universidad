package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera, CarreraRepository> implements CarreraDAO{

	@Autowired
	public CarreraDAOImpl(CarreraRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarreraByNombreContains(String nombre) {
		return repository.findCarreraByNombreContains(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre) {
		return repository.findCarreraByNombreContainsIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios) {
		return repository.findCarreraByCantidadAniosAfter(cantidadAnios);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) {
		return repository.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
	}

	@Override
	@Transactional
	public Carrera actualizar(Long carreraId, Carrera carrera) {
		Optional<Carrera> oCarrera = repository.findById(carreraId);
		
		if(!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con ID %d no existe", carreraId)); 
		
		Carrera carreraActualizada = null;
		oCarrera.get().setCantidadAnios(carrera.getCantidadAnios());
		oCarrera.get().setCantidadMaterias(carrera.getCantidadMaterias());
		carreraActualizada = repository.save(oCarrera.get());
		return carreraActualizada;
	}	
	
}
