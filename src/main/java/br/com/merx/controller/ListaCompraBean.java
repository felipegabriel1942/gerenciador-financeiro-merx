package br.com.merx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.merx.converter.ProdutoConverter;
import br.com.merx.model.Produto;
import br.com.merx.service.ListaComprasService;
import br.com.merx.util.MensagensUtil;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class ListaCompraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private ListaComprasService listaComprasService = new ListaComprasService();

	@Getter
	@Setter
	private List<Produto> listaProdutos = new ArrayList<>();

	@Getter
	@Setter
	private List<Produto> listaDeComprasProduto = new ArrayList<>();

	@Getter
	@Setter
	private Produto produtoPesquisado = new Produto();

	@Getter
	@Setter
	private Produto produto = new Produto();

	@Getter
	@Setter
	private Produto produtoSelecionado = new Produto();

	@Getter
	@Setter
	private ProdutoConverter produtoConverter = new ProdutoConverter();

	@Getter
	@Setter
	private boolean renderizarLista;

	public List<Produto> buscarProdutoPorNome(String nome) {

		listaProdutos = listaComprasService.buscarProdutosPorNome(nome);

		return listaProdutos;
	}

	public void adicionarProdutoLista() {
		if (listaDeComprasProduto.contains(produtoSelecionado)) {
			MensagensUtil.mensagemGenerica("Atenção!", "Produto já está na lista");
		} else {
			listaDeComprasProduto.add(produtoSelecionado);
		}

		if (listaDeComprasProduto.size() == 1) {
			renderizarLista = true;
		}
		produtoSelecionado = new Produto();
	}

}
