package br.com.merx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.merx.converter.ProdutoConverter;
import br.com.merx.model.Lista;
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
	private ListaItens listDeComprasProdutoExclusao = new ListaItens();

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
	private Lista listaDeComprasSelecionada = new Lista();

	@Getter
	@Setter
	private boolean renderizarLista;

	@Getter
	@Setter
	private boolean renderizarInicioLista;

	@Getter
	@Setter
	private boolean renderizarListasCadastradas;

	@Getter
	@Setter
	private int indexProdutoSelecionadoLista;

	@Getter
	@Setter
	private double valorTotalLista;

	@Getter
	@Setter
	private String descricaoLista;

	@Getter
	@Setter
	private List<Lista> listasDeComprasCadastradas = new ArrayList<>();

	@PostConstruct
	public void init() {
		valorTotalLista = 0.0;
	}

	public List<Produto> buscarProdutoPorNome(String nome) {

		listaProdutos = listaComprasService.buscarProdutosPorNome(nome);

		return listaProdutos;
	}

	public void criarLista() {
		if ("".equals(descricaoLista)) {
			MensagensUtil.mensagemGenerica("Atenção!", "Dê um nome para a lista");
		} else {
			listaDeComprasProduto.clear();
			renderizarInicioLista = true;
			renderizarLista =  false;
			renderizarListasCadastradas = false;
		}

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
			lista.setCategoria(
					listaComprasService.buscarCategoriaPorId(produtoSelecionado.getFkCategoria()).getCategoria());
			listaDeComprasProduto.add(lista);
			ajusteDoValorTotalDaLista();
			produtoSelecionado = new Produto();

		}

		if (listaDeComprasProduto.size() == 1) {
			renderizarLista = true;

		}

	}

	public void mostrarListasCadastradas() {
		listasDeComprasCadastradas = listaComprasService.mostrarListasCadastradas();
		renderizarListasCadastradas = true;
		renderizarLista = false;
		renderizarInicioLista = false;
	}

	public void excluirProdutoLista() {

		pegarIndexProduto();
		listaDeComprasProduto.remove(indexProdutoSelecionadoLista);
		ajusteDoValorTotalDaLista();
		if (listaDeComprasProduto.isEmpty()) {
			renderizarLista = false;
		}

	}

	public void salvarLista() {
		listaComprasService.salvarListaDeCompras(listaDeComprasProduto, valorTotalLista, descricaoLista);
		renderizarLista = false;
		renderizarInicioLista = false;
		MensagensUtil.mensagemGenerica("Sucesso!", "Lista foi cadastrada");
		listaDeComprasProduto.clear();
		valorTotalLista = 0.0;
		descricaoLista = "";
	}

	public void editarLista() {
		listaDeComprasProduto = listaComprasService.buscarItensListaPorPKLista(listaDeComprasSelecionada.getIdLista());
		renderizarListasCadastradas = false;
		renderizarLista = true;
		renderizarInicioLista = true;
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
		ajusteDoValorTotalDaLista();
	}

	public Integer pegarIndexProduto() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return indexProdutoSelecionadoLista = Integer.parseInt(params.get("index"));
	}

	public void ajusteDoValorTotalDaLista() {
		valorTotalLista = 0.0;
		for (int i = 0; i < listaDeComprasProduto.size(); i++) {
			valorTotalLista = valorTotalLista + listaDeComprasProduto.get(i).getValorTotal();
		}
	}

}
