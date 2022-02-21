package com.ibm.academia.restapi.universidad.datos;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public class AulaDatosDummy {

	public static Aula aula01() {
		return new Aula(null, 1, "40x40", 30, TipoPizarron.PIZARRON_TIZA, "AlexMedero");
	}
	
	public static Aula aula02() {
		return new Aula(null, 2, "60x50", 60, TipoPizarron.PIZARRON_TIZA, "AlexMedero");
	}
	
	public static Aula aula03() {
		return new Aula(null, 3, "110x70", 130, TipoPizarron.PIZARRON_BLANCO, "AlexMedero");
	}
	
}
