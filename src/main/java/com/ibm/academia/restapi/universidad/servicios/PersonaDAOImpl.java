package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO{

	public PersonaDAOImpl(PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
		Optional<Persona> persona = repository.buscarPorNombreYApellido(nombre, apellido);
		
		if(persona.isEmpty())
			throw new NotFoundException(String.format("No existen personas con el nombre: %s y apellido: %s", nombre,apellido));
		
		return persona;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorDni(String dni) {
		Optional<Persona> persona = repository.buscarPorDni(dni);
		
		if(persona.isEmpty())
			throw new NotFoundException(String.format("No existen personas con el dni: %s", dni));
		
		return persona;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarPersonaPorApellido(String apellido) {
		Iterable<Persona> personas = repository.buscarPersonaPorApellido(apellido);
		
		if(!personas.iterator().hasNext())
			throw new NotFoundException(String.format("No existen personas con el apellido: %s", apellido));
		
		return personas;
	}

}
