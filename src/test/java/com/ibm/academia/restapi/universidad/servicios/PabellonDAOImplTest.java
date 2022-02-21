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

import com.ibm.academia.restapi.universidad.datos.PabellonDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;


public class PabellonDAOImplTest {

	@Autowired
	private PabellonRepository pabellonRepository;
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@BeforeEach
	void setUp() {
		pabellonRepository = mock(PabellonRepository.class);
		pabellonDao = new PabellonDAOImpl(pabellonRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar pabellones por localidad")
	void findByDireccionLocalidad(){
		String localidad = "Estado de México";
		when(pabellonRepository.findByDireccionLocalidad(localidad))
			.thenReturn(Arrays.asList(PabellonDatosDummy.pabellon01()));
		
		List<Pabellon> expected = (List<Pabellon>) pabellonDao.findByDireccionLocalidad("Estado de México");
		
		assertThat(expected.get(0)).isEqualTo(PabellonDatosDummy.pabellon01());
		verify(pabellonRepository).findByDireccionLocalidad(localidad);
	}
	
	@Test
	@DisplayName("Test: Buscar pabellones por nombre")
	void findByNombre(){
		String nombre = "Santa Marta";
		when(pabellonRepository.findByNombre(nombre))
			.thenReturn(Arrays.asList(PabellonDatosDummy.pabellon02()));
		
		List<Pabellon> expected = (List<Pabellon>) pabellonDao.findByNombre("Santa Marta");
		
		assertThat(expected.get(0)).isEqualTo(PabellonDatosDummy.pabellon02());
		verify(pabellonRepository).findByNombre(nombre);
	}
	
}
