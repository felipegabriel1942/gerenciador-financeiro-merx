package br.com.merx.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.merx.model.Categoria;
import br.com.merx.model.Lista;
import br.com.merx.model.ListaItens;
import br.com.merx.model.Produto;
import br.com.merx.repository.CategoriaRepository;
import br.com.merx.repository.ListaItensRepository;
import br.com.merx.repository.ListaRepository;
import br.com.merx.repository.ProdutoRepository;
import lombok.Getter;
import lombok.Setter;

@Stateless
@LocalBean
public class ListaComprasService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private ListaRepository lr = new ListaRepository();

	@Getter
	@Setter
	private ListaItensRepository lir = new ListaItensRepository();

	@Getter
	@Setter
	private ProdutoRepository pr = new ProdutoRepository();
	
	@Getter
	@Setter
	private CategoriaRepository cr = new CategoriaRepository();

	public void salvarListaDeCompras(List<ListaItens> listaDeItens, Double valorTotal, String descricao) {
		Lista lista = new Lista();
		lista.setDescricao(descricao);
		lista.setValorTotal(valorTotal);
		lista.setDataCadastro(new Date());
		lr.salvarLista(lista);

		for (int i = 0; i < listaDeItens.size(); i++) {
			ListaItens item = new ListaItens();
			item.setFkLista(lista.getIdLista());
			item.setFkProduto(listaDeItens.get(i).getFkProduto());
			item.setProduto(listaDeItens.get(i).getProduto());
			item.setQuantidade(listaDeItens.get(i).getQuantidade());
			item.setValor(listaDeItens.get(i).getValor());
			item.setValorTotal(listaDeItens.get(i).getValorTotal());
			item.setCategoria(listaDeItens.get(i).getCategoria());
			lir.salvarListaItens(item);
		}

	}

	public List<Produto> buscarProdutosPorNome(String nome) {
		return pr.buscarProdutoPorNome(nome);
	}
	
	public Categoria buscarCategoriaPorId(Integer id) {
		return cr.buscarCategoriaPorId(id);
	}
	
	public List<Lista> mostrarListasCadastradas(){
		return lr.bucsarTodasAsListas();
	}
	
	public List<ListaItens> buscarItensListaPorPKLista(Integer codLista){
		return lir.buscarItensListaPorPKLista(codLista);
	}
}
