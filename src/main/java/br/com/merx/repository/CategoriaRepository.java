package br.com.merx.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.merx.model.Categoria;
import br.com.merx.util.EntityManagerUtil;

@Stateless
@LocalBean
public class CategoriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public CategoriaRepository() {
		em = EntityManagerUtil.geEntityManager();
	}

	public void salvarCategoria(Categoria obj) {
		
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		
	}

	public void editarCategoria(Categoria obj) {
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

	public void excluirCategoria(Categoria obj) {
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
	public List<Categoria> bucsarTodasAsCategorias() {
		List<Categoria> resultado = new ArrayList<>();
		Query query = em.createQuery("select c from Categoria c", Categoria.class);
		resultado = query.getResultList();
		return resultado;
	}

}
