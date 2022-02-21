package com.ibm.academia.restapi.universidad.datos;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

public class CarreraDatosDummy {

	public static Carrera carrera01() {
		return new Carrera(null, "Ingeniería en Sistemas", 50, 5, "AlexMedero");
	}
	
	public static Carrera carrera02() {
		return new Carrera(null, "Licenciatura en Derecho", 40, 4, "AlexMedero");
	}
	
	public static Carrera carrera03() {
		return new Carrera(null, "Ingeniería Aeroespacial", 30, 5, "AlexMedero");
	}
	
}
