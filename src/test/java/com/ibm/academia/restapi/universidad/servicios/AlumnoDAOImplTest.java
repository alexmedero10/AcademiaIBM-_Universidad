package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.academia.restapi.universidad.datos.AlumnoDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;

public class AlumnoDAOImplTest {

	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository personaRepository;
	
	@Autowired
	private PersonaDAO personaDao;
	
	
	@BeforeEach
	void setUp() {
		personaRepository = mock(AlumnoRepository.class);
		personaDao = new AlumnoDAOImpl(personaRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar alumnos por carrera")
	void buscarAlumnosPorNombreCarrera(){
		String nombreCarrera = "Licenciatura en Derecho";
		when(((AlumnoRepository)personaRepository).buscarAlumnosPorNombreCarrera(nombreCarrera))
			.thenReturn(Arrays.asList(AlumnoDatosDummy.alumno02()));
		
		List<Persona> expected = (List<Persona>) ((AlumnoDAO)personaDao).buscarAlumnosPorNombreCarrera("Licenciatura en Derecho");
		
		assertThat(expected.get(0)).isEqualTo(AlumnoDatosDummy.alumno02());
		verify((AlumnoRepository)personaRepository).buscarAlumnosPorNombreCarrera(nombreCarrera);
	}
	
}
