package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.Signo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISignoRepo;
import com.mitocode.service.ISignoService;

@Service
public class SignoServiceImpl extends CRUDImpl<Signo, Integer> implements ISignoService {
	
	@Autowired
	private ISignoRepo repo;

	@Override
	public Page<Signo> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	protected IGenericRepo<Signo, Integer> getRepo() {
		return repo;
	}

}
