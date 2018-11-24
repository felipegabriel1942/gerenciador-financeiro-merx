package br.com.merx.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.merx.model.Produto;
import br.com.merx.util.EntityManagerUtil;

@Stateless
@LocalBean
public class ProdutoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public ProdutoRepository() {
		em = EntityManagerUtil.geEntityManager();
	}

	public void salvarProduto(Produto obj) {
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		em.close();
	}

	public void editarProduto(Produto obj) {
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		em.close();
	}

	public void excluirProduto(Produto obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> bucsarTodasAsProdutos() {
		List<Produto> resultado = new ArrayList<>();
		Query query = em.createQuery("select c from Produto c", Produto.class);
		resultado = query.getResultList();
		return resultado;
	}

}
