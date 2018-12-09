package br.com.merx.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.merx.model.Produto;
import br.com.merx.repository.ListaItensRepository;
import br.com.merx.repository.ListaRepository;
import br.com.merx.repository.ProdutoRepository;
import lombok.Getter;
import lombok.Setter;

@Stateless
@LocalBean
public class ListaComprasService implements Serializable{

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
	
	public List<Produto> buscarProdutosPorNome(String nome){
		return pr.buscarProdutoPorNome(nome);
	}
}
