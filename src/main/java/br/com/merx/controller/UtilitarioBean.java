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
import br.com.merx.service.ProdutoService;
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
	private ProdutoService produtoService = new ProdutoService();

	@Getter
	@Setter
	private Categoria categoria = new Categoria();

	@Getter
	@Setter
	private Categoria categoriaSelecionada = new Categoria();

	@Getter
	@Setter
	private Produto produto = new Produto();
	
	@Getter
	@Setter
	private Produto produtoSelecionado = new Produto();

	@Getter
	@Setter
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	
	@Getter
	@Setter
	private List<Produto> listaProdutos = new ArrayList<>();

	@Getter
	@Setter
	private Integer categoriaMenuProdutoSelecionado;
	
	@Getter
	@Setter
	private boolean renderizarCadastroEdicaoCategoria;
	
	@Getter
	@Setter
	private boolean renderizarListaCategoria;
	
	@Getter
	@Setter
	private String txtBotaoCategoria;
	
	@Getter
	@Setter
	private String txtTituloCategoria;
	
	
	@PostConstruct
	public void init() {
		listaCategoria = categoriaService.mostrarTodasAsCategorias();
		listaProdutos = produtoService.mostrarTodosOsProdutos();
		
	}

	public void salvarCategoria() {

		try {
			if (categoria.getIdCategoria() == null) {
				categoriaService.salvarCategoria(categoria);
				MensagensUtil.mensagemGenerica("Sucesso!", "Categoria salva.");

			} else {
				categoriaService.editarCategoria(categoria);
				mostrarLista();
				MensagensUtil.mensagemGenerica("Sucesso!", "Categoria editada.");
				
			}
			listaCategoria = categoriaService.mostrarTodasAsCategorias();
			categoria = new Categoria();
		} catch (Exception e) {
			e.printStackTrace();
			MensagensUtil.mensagemGenerica("Erro!", "Um erro aconteceu ao salvar/editar a categoria.");
		}
	}

	public void excluirCategoria() {
		categoriaService.excluirCategoria(categoriaSelecionada);
		listaCategoria = categoriaService.mostrarTodasAsCategorias();
		MensagensUtil.mensagemGenerica("Sucesso!", "Categoria excluída com sucesso.");
		categoriaSelecionada = new Categoria();
	}

	public void editarCategoria() {
		categoria = categoriaSelecionada;
		mostrarEdicao();
	}

	public void salvarProduto() {
		try {
			if (categoriaMenuProdutoSelecionado == null) {
				MensagensUtil.mensagemGenerica("Atenção!", "Por favor, Selecione uma categoria.");
			} else if ("".equals(produto.getProduto())) {
				MensagensUtil.mensagemGenerica("Atenção!", "Por favor, Informe o nome do produto.");
			} else if (produto.getValor() == null) {
				MensagensUtil.mensagemGenerica("Atenção!", "Por favor, Informe o valor do produto.");
			} else {
				if (produto.getIdProduto() == null) {
					produto.setFkCategoria(categoriaMenuProdutoSelecionado);
					produtoService.salvarProduto(produto);
					MensagensUtil.mensagemGenerica("Sucesso!", "Produto cadastrado.");

				} else {
					produto.setFkCategoria(categoriaMenuProdutoSelecionado);
					produtoService.editarProduto(produto);
					MensagensUtil.mensagemGenerica("Sucesso!", "Produto editado.");
				}
				listaProdutos = produtoService.mostrarTodosOsProdutos();
				categoriaMenuProdutoSelecionado = null;
				produto = new Produto();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MensagensUtil.mensagemGenerica("Erro!", "Um erro aconteceu ao salvar/editar o produto.");
		}
	}
	
	public void editarProduto() {
		produto = produtoSelecionado;
		produtoSelecionado = new Produto();
		categoriaMenuProdutoSelecionado = produto.getFkCategoria();
	}
	
	public void excluirProduto() {
		produtoService.excluirProduto(produtoSelecionado);
		listaProdutos = produtoService.mostrarTodosOsProdutos();
		MensagensUtil.mensagemGenerica("Sucesso!", "Produto excluído com sucesso.");
		produtoSelecionado = new Produto();
	}
	
	public void mostrarCadastro() {
		renderizarCadastroEdicaoCategoria = true;
		renderizarListaCategoria = false;
		txtBotaoCategoria = "Cadastrar";
		txtTituloCategoria = "Cadastro de categoria";
		categoria = new Categoria();
		categoriaSelecionada = new Categoria();
	}
	
	public void mostrarLista() {
		renderizarCadastroEdicaoCategoria = false;
		renderizarListaCategoria = true;
		categoria = new Categoria();
		categoriaSelecionada = new Categoria();
	}
	
	public void mostrarEdicao() {
		renderizarCadastroEdicaoCategoria = true;
		renderizarListaCategoria = false;
		txtBotaoCategoria = "Editar";
		txtTituloCategoria = "Edição de categoria";
	}

}
