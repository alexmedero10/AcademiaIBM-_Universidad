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

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

@RestController
@RequestMapping("/restapi")
public class PabellonController {

	@Autowired
	private PabellonDAO pabellonDao;
	
	@GetMapping("/pabellones/lista")
	public ResponseEntity<?> listarTodos(){
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		
		if(pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones");
		
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
	@GetMapping("/pabellon/pabellonId/{pabellonId}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long pabellonId){
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellón con id: %d no existe", pabellonId));
		
		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
	}
	
	@PostMapping("/pabellon")
	public ResponseEntity<?> guardar(@Valid @RequestBody Pabellon pabellon, BindingResult result){
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
		
		Pabellon pabellonGuardada = pabellonDao.guardar(pabellon);
		return new ResponseEntity<Pabellon>(pabellonGuardada, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/pabellon/eliminar/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminar(@PathVariable Long pabellonId){
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellón con id: %d no existe", pabellonId));
		
		pabellonDao.eliminarPorId(pabellonId);
		return new ResponseEntity<>("El pabellón con id: "+ pabellonId + "fue eliminado", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/pabellon/actualizar/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizar(@PathVariable Long pabellonId, @Valid @RequestBody Pabellon pabellon, BindingResult result){
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()){
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Pabellon pabellonActualizado = null;
		
		try {
			pabellonActualizado = pabellonDao.actualizar(pabellonId, pabellon);
		}catch(Exception e) {
			throw e;
		}
		
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK);
	}
	
	@GetMapping("/pabellones/lista/localidad/{localidad}")
	public ResponseEntity<?> findByDireccionLocalidad(@PathVariable String localidad){
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.findByDireccionLocalidad(localidad);
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
	@GetMapping("/pabellones/lista/nombre/{nombre}")
	public ResponseEntity<?> findByNombre(@PathVariable String nombre){
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.findByNombre(nombre);
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
}
