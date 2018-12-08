package br.com.merx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.merx.model.Categoria;
import br.com.merx.model.Produto;
import br.com.merx.service.CategoriaService;
import br.com.merx.util.MensagensUtil;
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
	private Produto produto =  new Produto();

	@Getter
	@Setter
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();

	@PostConstruct
	public void init() {
		listaCategoria = categoriaService.mostrarTodasAsCategorias();
	}

	public void salvarCategoria() {

		try {
			if (categoria.getIdCategoria() == null) {
				categoriaService.salvarCategoria(categoria);
				MensagensUtil.mensagemGenerica("Sucesso!", "Categoria salva.");

			} else {
				categoriaService.editarCategoria(categoria);
				MensagensUtil.mensagemGenerica("Sucesso!", "Categoria editada.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MensagensUtil.mensagemGenerica("Erro!", "Um erro aconteceu ao salvar/editar a categoria.");
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
	
	public void salvarProduto() {
		try {
			if (produto.getIdProduto() == null) {
				categoriaService.salvarCategoria(categoria);
				MensagensUtil.mensagemGenerica("Sucesso!", "Categoria salva.");

			} else {
				categoriaService.editarCategoria(categoria);
				MensagensUtil.mensagemGenerica("Sucesso!", "Categoria editada.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MensagensUtil.mensagemGenerica("Erro!", "Um erro aconteceu ao salvar/editar a categoria.");
		}

		listaCategoria = categoriaService.mostrarTodasAsCategorias();
		categoria = new Categoria();
	}

}
