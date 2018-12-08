package br.com.merx.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.merx.model.Produto;
import br.com.merx.repository.ProdutoRepository;

@Stateless
@LocalBean
public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProdutoRepository pr = new ProdutoRepository();

	public void salvarProduto(Produto produto) {
		pr.salvarProduto(produto);
	}

	public void editarProduto(Produto produto) {
		pr.editarProduto(produto);
	}

	public void excluirProduto(Produto produto) {
		pr.excluirProduto(produto);
	}

	public List<Produto> mobuscarTodosOsProdutos() {
		return pr.buscarTodosOsProdutos();
	}
}
