package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.AlumnoDatosDummy;
import com.ibm.academia.restapi.universidad.datos.CarreraDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;


@DataJpaTest
public class AlumnoRepositoryTest {

	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository personaRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp() {
		carreraRepository.save(CarreraDatosDummy.carrera01());
		carreraRepository.save(CarreraDatosDummy.carrera02());
		carreraRepository.save(CarreraDatosDummy.carrera03());
		
		((Alumno)AlumnoDatosDummy.alumno01()).setCarrera(CarreraDatosDummy.carrera01());
		((Alumno)AlumnoDatosDummy.alumno02()).setCarrera(CarreraDatosDummy.carrera01());
		
		personaRepository.save(AlumnoDatosDummy.alumno01());
		personaRepository.save(AlumnoDatosDummy.alumno02());
	}
	
	@AfterEach
	void tearDown() {
		carreraRepository.deleteAll();
		personaRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar alumnos por carrera")
	void buscarAlumnosPorNombreCarrera(){
		List<Persona> expected = (List<Persona>) ((AlumnoRepository)personaRepository).buscarAlumnosPorNombreCarrera("Ingeniería en Sistemas");
		if(expected.size() > 1) {
			System.out.println(expected.get(0));
			System.out.println(expected.get(1));
		}
		assertThat(expected.size() == 1).isTrue();
	}
	
}
