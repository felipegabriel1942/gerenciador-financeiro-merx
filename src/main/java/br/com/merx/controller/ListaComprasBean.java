package br.com.merx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import br.com.merx.model.Categoria;
import br.com.merx.service.CategoriaService;

@ManagedBean(name = "listaComprasBean")
@ViewScoped
public class ListaComprasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private CategoriaService categoriaService;
	
	
	private Categoria categoria;
	
	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categoriaService = new CategoriaService();
	}
	
	public void salvarCategoria() {
		categoriaService.salvarCategoria(categoria);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
