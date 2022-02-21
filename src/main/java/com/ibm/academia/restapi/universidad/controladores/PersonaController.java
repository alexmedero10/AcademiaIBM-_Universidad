package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

@RestController
@RequestMapping("/restapi")
public class PersonaController {

	@Autowired
	@Qualifier("alumnoDAOImpl")
	private PersonaDAO alumnoDao;
	
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDao;
	
	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	@GetMapping("/persona/nombreYApellido/{nombre}/{apellido}")
	public ResponseEntity<?> buscarPorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido){
		Optional<Persona> oAlumno = alumnoDao.buscarPorNombreYApellido(nombre, apellido);
		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}
	
	@GetMapping("/persona/dni/{dni}")
	public ResponseEntity<?> buscarPorDni(@PathVariable String dni){
		Optional<Persona> oEmpleado = empleadoDao.buscarPorDni(dni);
		return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
	}
	
	@GetMapping("/personas/lista/apellido/{apellido}")
	public ResponseEntity<?> buscarPersonaPorApellido(@PathVariable String apellido){
		List<Persona> profesores = (List<Persona>) profesorDao.buscarPersonaPorApellido(apellido);
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}
	
}
