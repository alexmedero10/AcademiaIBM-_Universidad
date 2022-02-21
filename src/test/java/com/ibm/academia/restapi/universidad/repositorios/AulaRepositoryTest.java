package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.AulaDatosDummy;
import com.ibm.academia.restapi.universidad.datos.PabellonDatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

@DataJpaTest
public class AulaRepositoryTest {

	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		pabellonRepository.save(PabellonDatosDummy.pabellon01());
		pabellonRepository.save(PabellonDatosDummy.pabellon02());

		aulaRepository.save(AulaDatosDummy.aula01());
		aulaRepository.save(AulaDatosDummy.aula02());
		aulaRepository.save(AulaDatosDummy.aula03());
		
		AulaDatosDummy.aula01().setPabellon(PabellonDatosDummy.pabellon01());
		AulaDatosDummy.aula02().setPabellon(PabellonDatosDummy.pabellon02());
		AulaDatosDummy.aula03().setPabellon(PabellonDatosDummy.pabellon01());
		
		aulaRepository.save(AulaDatosDummy.aula01());
		aulaRepository.save(AulaDatosDummy.aula02());
		aulaRepository.save(AulaDatosDummy.aula03());
		
	}
	
	@AfterEach
	void tearDown() {
		pabellonRepository.deleteAll();
		aulaRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar aula por tipo de pizarrón")
	@Disabled
	void findByTipoPizarron() {
		List<Aula> expected = (List<Aula>) aulaRepository.findByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
		assertThat(expected.size() == 1).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar aula por nombre de pabellón")
	void findByPabellonNombre() {
		List<Aula> expected = (List<Aula>) aulaRepository.findByPabellonNombre("San Jose");
		System.out.println(expected.size());
		assertThat(expected.size() == 1).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar aula por número de aula")
	@Disabled
	void findByNumeroAula() {
		Optional<Aula> expected =  aulaRepository.findByNumeroAula(1);
		assertThat(expected.get().getMedidas()).isEqualTo("40x40");
	}
}
