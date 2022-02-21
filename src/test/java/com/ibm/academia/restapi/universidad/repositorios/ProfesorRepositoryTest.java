package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.CarreraDatosDummy;
import com.ibm.academia.restapi.universidad.datos.ProfesorDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest {

	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository personaRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp() {
		carreraRepository.save(CarreraDatosDummy.carrera01());
		carreraRepository.save(CarreraDatosDummy.carrera02());
		carreraRepository.save(CarreraDatosDummy.carrera03());
		
		Set<Carrera> carreras = new HashSet<>();
		carreras.add(CarreraDatosDummy.carrera01());
		carreras.add(CarreraDatosDummy.carrera02());
		((Profesor)ProfesorDatosDummy.profesor01()).setCarreras(carreras);
		
		Set<Carrera> carreras2 = new HashSet<>();
		carreras2.add(CarreraDatosDummy.carrera01());
		((Profesor)ProfesorDatosDummy.profesor02()).setCarreras(carreras2);
		
		personaRepository.save(ProfesorDatosDummy.profesor01());
		personaRepository.save(ProfesorDatosDummy.profesor02());
	}
	
	@AfterEach
	void tearDown() {
		carreraRepository.deleteAll();
		personaRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar profesores por carrera")
	void findProfesoresByCarrera(){
		List<Persona> expected = (List<Persona>) ((ProfesorRepository)personaRepository).findProfesoresByCarrera("Ingeniería en Sistemas");
		assertThat(expected.size() == 1);
	}
	
}
