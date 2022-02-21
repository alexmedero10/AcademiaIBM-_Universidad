package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.PabellonDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;


@DataJpaTest
public class PabellonRepositoryTest {

	@Autowired
	private PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		pabellonRepository.save(PabellonDatosDummy.pabellon01());
		pabellonRepository.save(PabellonDatosDummy.pabellon02());
	}
	
	@AfterEach
	void tearDown() {
		pabellonRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar pabellones por localidad")
	void findByDireccionLocalidad(){
		List<Pabellon> expected = (List<Pabellon>) pabellonRepository.findByDireccionLocalidad("Ciudad de México");
		assertThat(expected.size() == 1).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar pabellones por nombre")
	void findByNombre(){
		List<Pabellon> expected = (List<Pabellon>) pabellonRepository.findByNombre("San Jose");
		assertThat(expected.size() == 1).isTrue();
	}
	
}
