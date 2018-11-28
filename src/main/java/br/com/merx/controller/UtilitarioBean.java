package br.com.merx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.merx.model.Categoria;
import br.com.merx.service.CategoriaService;

@ManagedBean(name = "utilitarioBean")
@ViewScoped
public class UtilitarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CategoriaService categoriaService = new CategoriaService();

	private Categoria categoria = new Categoria();

	private Categoria categoriaSelecionada = new Categoria();

	private List<Categoria> listaCategoria = new ArrayList<Categoria>();

	@PostConstruct
	public void init() {
		listaCategoria = categoriaService.mostrarTodasAsCategorias();
	}

	public void salvarCategoria() {
		if (categoriaService.buscarCategoriaPorId(categoria.getId_categoria()) == null) {
			categoriaService.salvarCategoria(categoria);
			
		} else {
			categoriaService.editarCategoria(categoria);
		}

		listaCategoria = categoriaService.mostrarTodasAsCategorias();
		categoria = new Categoria();
	}

	public void excluirCategoria() {
		categoriaService.excluirCategoria(categoriaSelecionada);
		listaCategoria = categoriaService.mostrarTodasAsCategorias();
		categoriaSelecionada = new Categoria();
	}

	public void editarCategoria() {
		categoria = categoriaSelecionada;
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

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

}
