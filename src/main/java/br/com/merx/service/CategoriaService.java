package br.com.merx.service;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.merx.repository.CategoriaRepository;

@Stateless
@LocalBean
public class CategoriaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaRepository cr;
	
	
	
	
	
	
}
