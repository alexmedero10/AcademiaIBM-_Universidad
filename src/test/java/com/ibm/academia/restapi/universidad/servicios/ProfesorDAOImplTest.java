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

import com.ibm.academia.restapi.universidad.datos.ProfesorDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;


public class ProfesorDAOImplTest {

	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository personaRepository;
	
	@Autowired
	private PersonaDAO personaDao;
	
	
	@BeforeEach
	void setUp() {
		personaRepository = mock(ProfesorRepository.class);
		personaDao = new ProfesorDAOImpl(personaRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar profesores por carrera")
	void findProfesoresByCarrera(){
		String carrera = "Ingeniería en Sistemas";
		when(((ProfesorRepository)personaRepository).findProfesoresByCarrera(carrera)).thenReturn(Arrays.asList(ProfesorDatosDummy.profesor01()));
		
		List<Persona> expected = (List<Persona>) ((ProfesorDAO)personaDao).findProfesoresByCarrera("Ingeniería en Sistemas");
		
		assertThat(expected.get(0)).isEqualTo(ProfesorDatosDummy.profesor01());
		verify((ProfesorRepository)personaRepository).findProfesoresByCarrera(carrera);
	}
	
	
}
