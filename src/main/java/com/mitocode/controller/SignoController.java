package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signo;
import com.mitocode.service.ISignoService;

@RestController
@RequestMapping("/signos")
public class SignoController {
	
	@Autowired
	private ISignoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Signo>> listar() throws Exception {
		List<Signo> lista = service.listar();
		return new ResponseEntity<List<Signo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Signo> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Signo obj = service.listarPorId(id);
		
		if(obj.getIdSigno() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Signo>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Signo> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		Signo obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		// localhost:8080/signos/{id}
		EntityModel<Signo> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		
		recurso.add(link.withRel("signo-recurso"));
		
		return recurso;

	}
	
	
	@PostMapping
	public ResponseEntity<Signo> registrar(@Valid @RequestBody Signo p) throws Exception{
		Signo obj = service.registrar(p);
		
		// localhost:8080/signos/{2}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSigno()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Signo> modificar(@Valid @RequestBody Signo p) throws Exception{
		Signo obj = service.modificar(p);
		
		return new ResponseEntity<Signo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Signo obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Signo>> listarPageable(Pageable pageable) throws Exception{			
		Page<Signo> signos = service.listarPageable(pageable);
		return new ResponseEntity<Page<Signo>>(signos, HttpStatus.OK);
	}

}
