package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO{
	
	@Autowired
	public ProfesorDAOImpl(@Qualifier("repositorioProfesor")PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findProfesoresByCarrera(String carrera) {
		return ((ProfesorRepository)repository).findProfesoresByCarrera(carrera);
	}

	@Override
	@Transactional
	public Persona actualizar(Long profesorId, Persona profesor) {
		Optional<Persona> oProfesor = repository.findById(profesorId);
		
		if(!oProfesor.isEmpty())
			throw new NotFoundException(String.format("El profesor con ID %d no existe", profesorId));
		
		Persona profesorActualizado = null;
		oProfesor.get().setDireccion(profesor.getDireccion());
		
		profesorActualizado = repository.save(oProfesor.get());
		return profesorActualizado;
	}
	

}
