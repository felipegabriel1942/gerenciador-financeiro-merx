package br.com.merx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.merx.model.Categoria;
import br.com.merx.service.CategoriaService;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class UtilitarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private CategoriaService categoriaService = new CategoriaService();

	@Getter
	@Setter
	private Categoria categoria = new Categoria();

	@Getter
	@Setter
	private Categoria categoriaSelecionada = new Categoria();

	@Getter
	@Setter
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();

	@PostConstruct
	public void init() {
		listaCategoria = categoriaService.mostrarTodasAsCategorias();
	}

	public void salvarCategoria() {
		if (categoria.getIdCategoria() == null) {
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

}
