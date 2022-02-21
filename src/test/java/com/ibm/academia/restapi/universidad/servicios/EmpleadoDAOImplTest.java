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

import com.ibm.academia.restapi.universidad.datos.EmpleadoDatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;


public class EmpleadoDAOImplTest {

	@Autowired
	@Qualifier("repositorioEmpleado")
	private PersonaRepository personaRepository;
	
	@Autowired
	private PersonaDAO personaDao;
	
	
	@BeforeEach
	void setUp() {
		personaRepository = mock(EmpleadoRepository.class);
		personaDao = new EmpleadoDAOImpl(personaRepository);
	}
	
	@Test
	@DisplayName("Test: Buscar empleado por tipo")
	void findEmpleadoByTipoEmpleado(){
		TipoEmpleado tipoEmpleado = TipoEmpleado.ADMINISTRATIVO;
		when(((EmpleadoRepository)personaRepository).findEmpleadoByTipoEmpleado(tipoEmpleado)).thenReturn(Arrays.asList(EmpleadoDatosDummy.empleado01()));
		
		List<Persona> expected = (List<Persona>) ((EmpleadoDAO)personaDao).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		
		assertThat(expected.get(0)).isEqualTo(EmpleadoDatosDummy.empleado01());
		verify((EmpleadoRepository)personaRepository).findEmpleadoByTipoEmpleado(tipoEmpleado);
	}
	
}
