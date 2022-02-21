package com.ibm.academia.restapi.universidad.servicios;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{

	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	/**
	 * Método para buscar aulas por el tipo de pizarrón
	 * @param tipoPizarron Parámetro del tipo de pizarrón
	 * @return Iterador de objeto Aula
	 */
	public Iterable<Aula> findByTipoPizarron(TipoPizarron tipoPizarron) {
		Iterable<Aula> aulas = repository.findByTipoPizarron(tipoPizarron);
		
		if(Arrays.asList(aulas).isEmpty())
			throw new NotFoundException("No existen aulas con pizarrón: " + tipoPizarron);
		
		return aulas;
	}

	@Override
	@Transactional(readOnly = true)
	/**
	 * Método para buscar aulas por el nombre del pabellón
	 * @param pabellonNombre Parámetro del nombre del pabellón
	 * @return Iterador de objeto Aula
	 */
	public Iterable<Aula> findByPabellonNombre(String pabellonNombre) {
		Iterable<Aula> aulas = repository.findByPabellonNombre(pabellonNombre);
		
		if(!aulas.iterator().hasNext())
			throw new NotFoundException(String.format("No existen aulas en el pabellon: %s", pabellonNombre));
		
		return aulas;
	}

	@Override
	@Transactional(readOnly = true)
	/**
	 * Método para buscar aulas por el número de aula
	 * @param numeroAula Parámetro del número de aula
	 * @return Optional de objeto Aula
	 */
	public Optional<Aula> findByNumeroAula(Integer numeroAula) {
		Optional<Aula> oAula = repository.findByNumeroAula(numeroAula);
		
		if(!oAula.isEmpty())
			throw new NotFoundException(String.format("El aula con número: %id no existe", numeroAula));
		
		return oAula;
	}

	@Override
	@Transactional
	public Aula actualizar(Long aulaId, Aula aula) {
		Optional<Aula> oAula = repository.findById(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		Aula aulaActualizada = null;
		
		oAula.get().setCantidadPupitres(aula.getCantidadPupitres());
		oAula.get().setNumeroAula(aula.getNumeroAula());
		oAula.get().setTipoPizarron(aula.getTipoPizarron());
		
		aulaActualizada = repository.save(oAula.get());
		
		return aulaActualizada;
	}

}
