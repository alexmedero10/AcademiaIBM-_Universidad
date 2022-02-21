package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.academia.restapi.universidad.datos.AulaDatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;


public class AulaDAOImplTest {

	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private AulaDAO aulaDao;
	
	@BeforeEach
	void setUp() {
		aulaRepository = mock(AulaRepository.class);
		aulaDao = new AulaDAOImpl(aulaRepository);
	}
	
	@Test
	@DisplayName("Test: buscar aula por tipo de pizarrón")
	void findByTipoPizarron(){
		TipoPizarron tipoPizarron = TipoPizarron.PIZARRON_BLANCO;
		when(aulaRepository.findByTipoPizarron(tipoPizarron))
			.thenReturn(Arrays.asList(AulaDatosDummy.aula03()));
		
		List<Aula> expected = (List<Aula>) aulaDao.findByTipoPizarron(tipoPizarron);
		
		assertThat(expected.get(0)).isEqualTo(AulaDatosDummy.aula03());
		verify(aulaRepository).findByTipoPizarron(tipoPizarron);
		
	}
	
	@Test
	@DisplayName("Test: buscar aula por nombre de pabellón")
	void findByPabellonNombre(){
		String pabellonNombre = "San Jose";
		when(aulaRepository.findByPabellonNombre(pabellonNombre)).thenReturn(Arrays.asList(AulaDatosDummy.aula01()));
		
		List<Aula> expected = (List<Aula>) aulaDao.findByPabellonNombre(pabellonNombre);
		
		assertThat(expected.get(0)).isEqualTo(AulaDatosDummy.aula01());
		verify(aulaRepository).findByPabellonNombre(pabellonNombre);
	}
	
	@Test
	@DisplayName("Test: buscar aula por número de aula")
	void findByNumeroAula(){
		Integer numeroAula = 2;
		when(aulaRepository.findByNumeroAula(numeroAula)).thenReturn(Optional.of(AulaDatosDummy.aula02()));
		
		Optional<Aula> expected = aulaDao.findByNumeroAula(numeroAula);
		
		assertThat(expected.get()).isEqualTo(AulaDatosDummy.aula02());
		verify(aulaRepository).findByNumeroAula(numeroAula);
	}
	
}
