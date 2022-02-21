package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.CarreraDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;


@DataJpaTest
public class CarreraRepositoryTest {

	@Autowired
	private CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp() {
		carreraRepository.save(CarreraDatosDummy.carrera01());
		carreraRepository.save(CarreraDatosDummy.carrera02());
		carreraRepository.save(CarreraDatosDummy.carrera03());
	}
	
	@AfterEach
	void tearDown() {
		carreraRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarreraByNombreContains() {
		Iterable<Carrera> expected = carreraRepository.findCarreraByNombreContains("Ingeniería");
		List<Carrera> expectedList = new ArrayList<>();
		expected.forEach(expectedList::add);
		assertThat(expectedList.size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre NO case sensitive")
	void findCarreraByNombreContainsIgnoreCase() {
		Iterable<Carrera> expected = carreraRepository.findCarreraByNombreContainsIgnoreCase("Ingeniería");
		List<Carrera> expectedList = new ArrayList<>();
		expected.forEach(expectedList::add);
		assertThat(expectedList.size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por duración en años mayor a")
	void findCarreraByCantidadAniosAfter() {
		Iterable<Carrera> expected = carreraRepository.findCarreraByCantidadAniosAfter(4);
		List<Carrera> expectedList = new ArrayList<>();
		expected.forEach(expectedList::add);
		assertThat(expectedList.size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre y apellido del profesor")
	void buscarCarrerasPorProfesorNombreYApellido() {
		Iterable<Carrera> expected = carreraRepository.buscarCarrerasPorProfesorNombreYApellido("Tona","Martinez");
		List<Carrera> expectedList = new ArrayList<>();
		expected.forEach(expectedList::add);
		assertThat(expectedList.size() == 1).isTrue();
	}
	
}
