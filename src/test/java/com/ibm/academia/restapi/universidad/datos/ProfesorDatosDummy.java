package com.ibm.academia.restapi.universidad.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

public class ProfesorDatosDummy {

	public static Persona profesor01() {
		Direccion direccion = new Direccion("calle canto", "67", "55679", "22", "6", "Ciudad de México");
		return new Profesor(1L, "José", "Nadal", "345612789", direccion, "AlexMedero", new BigDecimal(30000));
	}
	
	public static Profesor profesor02() {
		Direccion direccion = new Direccion("calle flor", "17", "55374", "2", "1", "Estado de México");
		return new Profesor(2L, "Benito", "Medel", "645392781", direccion, "AlexMedero", new BigDecimal(20000));
	}
	
}
