package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

@RestController
@RequestMapping("/restapi")
public class AulaController {

	@Autowired
	private AulaDAO aulaDao;
	
	@GetMapping("/aulas/lista")
	public ResponseEntity<?> listarTodas(){
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	@GetMapping("/aula/aulaId/{aulaId}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long aulaId){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
	@PostMapping("/aula")
	public ResponseEntity<?> guardar(@Valid @RequestBody Aula aula, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/aula/eliminar/aulaId/{aulaId}")
	public ResponseEntity<?> eliminar(@PathVariable Long aulaId){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		aulaDao.eliminarPorId(aulaId);
		return new ResponseEntity<>("El aula con id: "+ aulaId + "fue eliminada", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/aula/actualizar/aulaId/{aulaId}")
	public ResponseEntity<?> actualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()){
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Aula aulaActualizada = null;
		
		try {
			aulaActualizada = aulaDao.actualizar(aulaId, aula);
		}catch(Exception e) {
			throw e;
		}
		
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	@GetMapping("/aulas/lista/pizarron/{tipoPizarron}")
	public ResponseEntity<?> findByTipoPizarron(@PathVariable TipoPizarron tipoPizarron){
		List<Aula> aulas = (List<Aula>) aulaDao.findByTipoPizarron(tipoPizarron);
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	@GetMapping("/aulas/lista/pabellon/{pabellonNombre}")
	public ResponseEntity<?> findByPabellonNombre(@PathVariable String pabellonNombre){
		List<Aula> aulas = (List<Aula>) aulaDao.findByPabellonNombre(pabellonNombre);
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	@GetMapping("/aula/numero/{numeroAula}")
	public ResponseEntity<?> findByNumeroAula(@PathVariable Integer numeroAula){
		Optional<Aula> oAula = aulaDao.findByNumeroAula(numeroAula);
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
}
