package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface EmpleadoDAO extends PersonaDAO{

	/**
	 * Método para buscar empleados por tipo de empleado
	 * @param tipoEmpleado Parámetro del tipo de empleado
	 * @return Iterador de objeto Persona
	 */
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
	
	/**
	 * Métdodo para actualizar un empleado
	 * @param empleadoId Id del empleado a actualizar
	 * @param empleado Datos para actualizar al empleado
	 * @return Persona tipo empleado actualizado
	 * @author AML - 16-02-2022
	 */
	public Persona actualizar(Long empleadoId, Persona empleado);
}
