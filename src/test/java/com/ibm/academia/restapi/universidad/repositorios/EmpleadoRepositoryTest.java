package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.EmpleadoDatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;


@DataJpaTest
public class EmpleadoRepositoryTest {

	@Autowired
	@Qualifier("repositorioEmpleado")
	private PersonaRepository personaRepository;
	
	@BeforeEach
	void setUp() {
		((Empleado)EmpleadoDatosDummy.empleado01()).setTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);		
		((Empleado)EmpleadoDatosDummy.empleado02()).setTipoEmpleado(TipoEmpleado.MANTENIMIENTO);

		personaRepository.save(EmpleadoDatosDummy.empleado01());
		personaRepository.save(EmpleadoDatosDummy.empleado02());
	}
	
	@Test
	@DisplayName("Test: Buscar empleado por tipo")
	void findEmpleadoByTipoEmpleado(){
		List<Persona> expected = (List<Persona>) ((EmpleadoRepository)personaRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		assertThat(expected.size() == 1).isTrue();
	}
	
}
