package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaDAO extends GenericoDAO<Aula>{
	/**
	 * Método para buscar aulas por el tipo de pizarrón
	 * @param tipoPizarron Parámetro del tipo de pizarrón
	 * @return Iterador de objeto Aula
	 */
	public Iterable<Aula> findByTipoPizarron(TipoPizarron tipoPizarron);
	
	/**
	 * Método para buscar aulas por el nombre del pabellón
	 * @param pabellonNombre Parámetro del nombre del pabellón
	 * @return Iterador de objeto Aula
	 */
	public Iterable<Aula> findByPabellonNombre(String pabellonNombre);
	
	/**
	 * Método para buscar aulas por el número de aula
	 * @param numeroAula Parámetro del número de aula
	 * @return Optional de objeto Aula
	 */
	public Optional<Aula> findByNumeroAula(Integer numeroAula);
	
	/**
	 * Método para actualizar un aula
	 * @param aulaId Id de la aula a actualizar
	 * @param aula Datos para actualizar el aula
	 * @return Aula actualizada
	 * @author AML - 16-02-2022
	 */
	public Aula actualizar(Long aulaId, Aula aula);
}
