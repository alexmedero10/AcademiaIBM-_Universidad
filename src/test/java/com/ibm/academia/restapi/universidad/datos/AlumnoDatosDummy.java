package com.ibm.academia.restapi.universidad.datos;

import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public class AlumnoDatosDummy {

	public static Persona alumno01() {
		Direccion direccion = new Direccion("calle londres", "101", "51234", "2", "1", "Ciudad de México");
		return new Alumno(null, "Edgar", "Bara", "121209096", direccion, "AlexMedero");
	}
	
	public static Persona alumno02() {
		Direccion direccion = new Direccion("calle mar", "30", "52274", "6", "3", "Estado de México");
		return new Alumno(null, "Gloria", "Dado", "525205095", direccion, "AlexMedero");
	}
	
}
