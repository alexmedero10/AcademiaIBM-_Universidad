package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

public interface GenericoDAO<E>{

	/**
	 * Método genérico para buscar por Id
	 * @param id Parémetro del id
	 * @return Optional
	 */
	public Optional<E> buscarPorId(Long id);
	
	/**
	 * Método genérico para guardar una entidad
	 * @param entidad Parámetro de la entidad
	 * @return Entidad guardada
	 */
	public E guardar(E entidad);
	
	/**
	 * Método genérico para buscar todos los datos
	 * @return Iterador con todos los datos
	 */
	public Iterable<E> buscarTodos();
	
	/**
	 * Método genérico para eliminar por id
	 * @param id Parámetro del id
	 */
	public void eliminarPorId(Long id);
}
