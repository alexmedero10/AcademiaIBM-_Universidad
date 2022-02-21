package com.ibm.academia.restapi.universidad.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public class EmpleadoDatosDummy {

	public static Persona empleado01() {
		Direccion direccion = new Direccion("calle jopol", "40", "55121", "50", "5", "Estado de México");
		return new Empleado(null, "Andrea", "Fernandez", "657483920", direccion, "AlexMedero", new BigDecimal(10000));
	}
	
	public static Persona empleado02() {
		Direccion direccion = new Direccion("calle rio", "23", "54125", "10", "3", "Ciudad de México");
		return new Empleado(null, "Armando", "Casas", "607483020", direccion, "AlexMedero", new BigDecimal(9000));
	}
	
}
