package br.com.merx.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.merx.model.Lista;
import br.com.merx.util.EntityManagerUtil;

@Stateless
@LocalBean
public class ListaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public ListaRepository() {
		em = EntityManagerUtil.geEntityManager();
	}

	public void salvarLista(Lista obj) {
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

	public void editarLista(Lista obj) {
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

	public void excluirLista(Lista obj) {
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
	public List<Lista> bucsarTodasAsListas() {
		List<Lista> resultado = new ArrayList<>();
		Query query = em.createQuery("select c from Lista c", Lista.class);
		resultado = query.getResultList();
		return resultado;
	}

}
