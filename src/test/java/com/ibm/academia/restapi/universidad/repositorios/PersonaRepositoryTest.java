package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.AlumnoDatosDummy;
import com.ibm.academia.restapi.universidad.datos.EmpleadoDatosDummy;
import com.ibm.academia.restapi.universidad.datos.ProfesorDatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

@DataJpaTest
public class PersonaRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository alumnoRepository;
	
	@Autowired
	@Qualifier("empleadoRepository")
	private PersonaRepository empleadoRepository;
	
	@Autowired
	@Qualifier("profesorRepository")
	private PersonaRepository profesorRepository;
	
	
	@Test
	@DisplayName("Test: Buscar por Nombre y Apellido")
	void buscarPorNombreYApellido(){
		Persona personaEmpleado = empleadoRepository.save(EmpleadoDatosDummy.empleado01());
		
		Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(EmpleadoDatosDummy.empleado01().getNombre(), EmpleadoDatosDummy.empleado01().getApellido());
		
		assertThat(expected.get()).isInstanceOf(Empleado.class);
		assertThat(expected.get()).isEqualTo(personaEmpleado);
		
	}
	
	@Test
	@DisplayName("Test: Buscar persona por DNI")
	void buscarPorDni() {
        Persona personaProfesor = profesorRepository.save(ProfesorDatosDummy.profesor01());

        Optional<Persona> expected = profesorRepository.buscarPorDni(ProfesorDatosDummy.profesor01().getDni());

        assertThat(expected.get()).isInstanceOf(Profesor.class);
        assertThat(expected.get()).isEqualTo(personaProfesor);
        assertThat(expected.get().getDni()).isEqualTo(personaProfesor.getDni());
	}
	
	@Test
	@DisplayName("Test: Buscar persona por Apellido")
	void buscarPersonaPorApellido(){
        List<Persona> personas = (List<Persona>) alumnoRepository.saveAll(
                Arrays.asList(
                		AlumnoDatosDummy.alumno01(),
                		AlumnoDatosDummy.alumno02())
        );

        String apellido = "Dado";
        List<Persona> expected = (List<Persona>) alumnoRepository.buscarPersonaPorApellido(apellido);

        assertThat(expected.size() == personas.size()).isTrue();
	}
}