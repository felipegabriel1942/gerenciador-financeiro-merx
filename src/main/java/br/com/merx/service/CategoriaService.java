package br.com.merx.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.merx.model.Categoria;
import br.com.merx.repository.CategoriaRepository;

@Stateless
@LocalBean
public class CategoriaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private CategoriaRepository cr = new CategoriaRepository();
	
	
	public void salvarCategoria(Categoria obj) {
		cr.salvarCategoria(obj);
	}
	
	public void editarCategoria(Categoria obj) {
		cr.editarCategoria(obj);
	}
	
	public void excluirCategoria(Categoria obj) {
		cr.excluirCategoria(obj);
	}
	
	public List<Categoria> mostrarTodasAsCategorias() {
		return cr.bucsarTodasAsCategorias();
	}
	
	public Categoria buscarCategoriaPorId(Integer id) {
		return cr.buscarCategoriaPorId(id);
	} 
	
	
	
}
