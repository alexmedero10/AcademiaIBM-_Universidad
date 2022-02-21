package com.ibm.academia.restapi.universidad.datos;

import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

public class PabellonDatosDummy {

	public static Pabellon pabellon01() {
		Direccion direccion = new Direccion("calle petra", "34", "50221", "4", "3", "Estado de México");
		return new Pabellon(null, 250.00, "San Jose", direccion, "AlexMedero");
	}
	
	public static Pabellon pabellon02() {
		Direccion direccion = new Direccion("calle santa", "12", "51231", "7", "1", "Ciudad de México");
		return new Pabellon(null, 650.00, "Santa Marta", direccion, "AlexMedero");
	}
	
}
