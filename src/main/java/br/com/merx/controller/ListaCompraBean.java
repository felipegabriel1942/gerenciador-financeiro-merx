package br.com.merx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.merx.converter.ProdutoConverter;
import br.com.merx.model.ListaItens;
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
	private List<ListaItens> listaDeComprasProduto = new ArrayList<>();

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

	@Getter
	@Setter
	private Integer indexProdutoSelecionadoLista;

	public List<Produto> buscarProdutoPorNome(String nome) {

		listaProdutos = listaComprasService.buscarProdutosPorNome(nome);

		return listaProdutos;
	}

	public void adicionarProdutoLista() {

		if (produtoSelecionado == null) {
			MensagensUtil.mensagemGenerica("Atenção!", "Selecione um produto");

		} else if (verificarConteudoLista(produtoSelecionado.getIdProduto())) {
			MensagensUtil.mensagemGenerica("Atenção!", "Produto já está na lista");

		} else {
			ListaItens lista = new ListaItens();
			lista.setProduto(produtoSelecionado.getProduto());
			lista.setFkProduto(produtoSelecionado.getIdProduto());
			lista.setQuantidade(1);
			lista.setValor(produtoSelecionado.getValor());
			lista.setValorTotal((produtoSelecionado.getValor()));
			listaDeComprasProduto.add(lista);
			produtoSelecionado = new Produto();

		}

		if (listaDeComprasProduto.size() == 1) {
			renderizarLista = true;

		}

	}

	public boolean verificarConteudoLista(Integer codProduto) {

		for (int i = 0; i < listaDeComprasProduto.size(); i++) {
			if (listaDeComprasProduto.get(i).getFkProduto() == codProduto) {
				return true;
			}
		}

		return false;

	}

	public void ajusteQuantidadeProduto() {
		pegarIndexProduto();
		listaDeComprasProduto.get(indexProdutoSelecionadoLista)
				.setValorTotal(listaDeComprasProduto.get(indexProdutoSelecionadoLista).getQuantidade()
						* listaDeComprasProduto.get(indexProdutoSelecionadoLista).getValor());
	}

	public Integer pegarIndexProduto() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return indexProdutoSelecionadoLista = Integer.parseInt(params.get("index"));
	}

}
