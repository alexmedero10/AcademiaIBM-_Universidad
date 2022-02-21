package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO{

	@Autowired
	public PabellonDAOImpl(PabellonRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> findByDireccionLocalidad(String localidad) {
		Iterable<Pabellon> pabellones = repository.findByDireccionLocalidad(localidad);
		
		if(!pabellones.iterator().hasNext())
			throw new NotFoundException(String.format("No existen pabellones en la localidad: %s",localidad));
		
		return pabellones;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Pabellon> findByNombre(String nombre) {
		Iterable<Pabellon> pabellones = repository.findByNombre(nombre);
		
		if(!pabellones.iterator().hasNext())
			throw new NotFoundException(String.format("No existen pabellones con el nombre: %s",nombre));
		
		return pabellones;
	}

	
	@Override
	@Transactional
	public Pabellon actualizar(Long pabellonId, Pabellon pabellon) {
		Optional<Pabellon> oPabellon = repository.findById(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellón con id: %d no existe", pabellonId));
		
		Pabellon pabellonActualizado = null;
		
		oPabellon.get().setNombre(pabellon.getNombre());
		oPabellon.get().setMetrosCuadrados(pabellon.getMetrosCuadrados());
		
		pabellonActualizado = repository.save(oPabellon.get());
		
		return pabellonActualizado;
	}
	
}
