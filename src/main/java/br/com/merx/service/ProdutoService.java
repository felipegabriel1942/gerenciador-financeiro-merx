package br.com.merx.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.merx.model.Produto;
import br.com.merx.repository.CategoriaRepository;
import br.com.merx.repository.ProdutoRepository;

@Stateless
@LocalBean
public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProdutoRepository pr = new ProdutoRepository();

	private CategoriaRepository cr = new CategoriaRepository();

	public void salvarProduto(Produto produto) {
		pr.salvarProduto(produto);
	}

	public void editarProduto(Produto produto) {
		pr.editarProduto(produto);
	}

	public void excluirProduto(Produto produto) {
		pr.excluirProduto(produto);
	}

	public List<Produto> mostrarTodosOsProdutos() {
		List<Produto> produtos = new ArrayList<>();
		produtos = pr.buscarTodosOsProdutos();
		
		for (int i = 0; i < produtos.size(); i++) {
			produtos.get(i).setNomeCategoria(cr.buscarCategoriaPorId(produtos.get(i).getFkCategoria()).getCategoria());
		}

		return produtos;
	}
}
