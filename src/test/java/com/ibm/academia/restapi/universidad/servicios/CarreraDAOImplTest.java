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

import com.ibm.academia.restapi.universidad.datos.CarreraDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;


public class CarreraDAOImplTest {

	@Autowired
	private CarreraRepository carreraRepository;
	
	@Autowired
	private CarreraDAO carreraDao;
	
	@BeforeEach
	void setUp() {
		carreraRepository = mock(CarreraRepository.class);
		carreraDao = new CarreraDAOImpl(carreraRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarreraByNombreContains() {
		String nombreCarrera = "Ingeniería";
		when(carreraRepository.findCarreraByNombreContains(nombreCarrera))
			.thenReturn(Arrays.asList(CarreraDatosDummy.carrera01(),CarreraDatosDummy.carrera03()));
		
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarreraByNombreContains(nombreCarrera);
		
		assertThat(expected.get(0)).isEqualTo(CarreraDatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(CarreraDatosDummy.carrera03());
		
		verify(carreraRepository).findCarreraByNombreContains(nombreCarrera);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre NO case sensitive")
	void findCarreraByNombreContainsIgnoreCase() {
		String nombreCarrera = "licenciatura";
		when(carreraRepository.findCarreraByNombreContainsIgnoreCase(nombreCarrera))
			.thenReturn(Arrays.asList(CarreraDatosDummy.carrera02()));
		
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarreraByNombreContainsIgnoreCase(nombreCarrera);
		
		assertThat(expected.get(0)).isEqualTo(CarreraDatosDummy.carrera02());
		
		verify(carreraRepository).findCarreraByNombreContainsIgnoreCase(nombreCarrera);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por duración en años mayor a")
	void findCarreraByCantidadAniosAfter() {
		Integer carreraAnios = 3;
		when(carreraRepository.findCarreraByCantidadAniosAfter(carreraAnios))
			.thenReturn(Arrays.asList(CarreraDatosDummy.carrera01(), CarreraDatosDummy.carrera02(),CarreraDatosDummy.carrera03()));
		
		List<Carrera> expected = (List<Carrera>) carreraDao.findCarreraByCantidadAniosAfter(carreraAnios);
		
		assertThat(expected.get(0)).isEqualTo(CarreraDatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(CarreraDatosDummy.carrera02());
		assertThat(expected.get(2)).isEqualTo(CarreraDatosDummy.carrera03());
		
		verify(carreraRepository).findCarreraByCantidadAniosAfter(carreraAnios);
	}
	
	@Test
	@DisplayName("Test: Buscar carreras por nombre y apellido del profesor")
	void buscarCarrerasPorProfesorNombreYApellido() {
		String nombreProfesor = "Benito";
		String apellidoProfesor = "Medel";
		when(carreraRepository.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor))
			.thenReturn(Arrays.asList(CarreraDatosDummy.carrera01()));
		
		List<Carrera> expected = (List<Carrera>) carreraDao.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);
		
		assertThat(expected.get(0)).isEqualTo(CarreraDatosDummy.carrera01());
		
		verify(carreraRepository).buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);
	}
	
	
}
