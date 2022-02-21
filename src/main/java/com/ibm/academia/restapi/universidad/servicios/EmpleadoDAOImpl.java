package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO{

	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleado")PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		Iterable<Persona> empleados = ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
		
		if(!empleados.iterator().hasNext())
			throw new NotFoundException("No existen empleados de tipo: " + tipoEmpleado);
		
		return empleados;
	}

	@Override
	@Transactional
	public Persona actualizar(Long empleadoId, Persona empleado) {
		Optional<Persona> oEmpleado = repository.findById(empleadoId);
		
		if(!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con ID %d no existe", empleadoId));
		
		Persona empleadoActualizado = null;
		oEmpleado.get().setNombre(empleado.getNombre());
		oEmpleado.get().setApellido(empleado.getApellido());
		oEmpleado.get().setDireccion(empleado.getDireccion());
		empleadoActualizado = repository.save(oEmpleado.get());
		
		return empleadoActualizado;
	}
	
}
