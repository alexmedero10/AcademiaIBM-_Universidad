package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{
	
	/**
	 * Método para buscar pabellones por localidad
	 * @param localidad Parámetro de la localidad
	 * @return Iterador de objeto Pabellon
	 */
	public Iterable<Pabellon> findByDireccionLocalidad(String localidad);
	
	/**
	 * Método para buscar pabellones por nombre
	 * @param nombre Parámetro del nombre
	 * @return Iterador de objeto Pabellon
	 */
	public Iterable<Pabellon> findByNombre(String nombre);
	
	/**
	 * Método para actualizar un pabellón
	 * @param pabellonId Id del pabellón a actualizar
	 * @param pabellon Datos para actualizar el pabellón
	 * @return Pabellon actualizado
	 * @author AML - 16-02-2022
	 */
	public Pabellon actualizar(Long pabellonId, Pabellon pabellon);
}
