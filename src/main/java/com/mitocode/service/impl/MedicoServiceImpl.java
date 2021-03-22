package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Medico;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IMedicoRepo;
import com.mitocode.service.IMedicoService;

@Service
public class MedicoServiceImpl extends CRUDImpl<Medico, Integer> implements IMedicoService {

	@Autowired
	private IMedicoRepo repo;

	@Override
	protected IGenericRepo<Medico, Integer> getRepo() {		
		return repo;
	}	
	
	/*@Override
	public Medico registrar(Medico p) throws Exception {	
		return repo.save(p);
	}

	@Override
	public Medico modificar(Medico p) throws Exception {		
		return repo.save(p);
	}

	@Override
	public List<Medico> listar() throws Exception {
		return repo.findAll();
	}

	@Override
	public Medico listarPorId(Integer id) throws Exception {
		Optional<Medico> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Medico();
		
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		repo.deleteById(id);
	}*/

}
