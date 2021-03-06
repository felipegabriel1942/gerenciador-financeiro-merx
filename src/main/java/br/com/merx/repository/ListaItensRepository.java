package br.com.merx.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.merx.model.ListaItens;
import br.com.merx.util.EntityManagerUtil;

@Stateless
@LocalBean
public class ListaItensRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public ListaItensRepository() {
		em = EntityManagerUtil.geEntityManager();
	}

	public void salvarListaItens(ListaItens obj) {
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	public void editarListaItens(ListaItens obj) {
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	public void excluirListaItens(ListaItens obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	@SuppressWarnings("unchecked")
	public List<ListaItens> buscarTodasAsListaItenss() {
		List<ListaItens> resultado = new ArrayList<>();
		Query query = em.createQuery("select c from ListaItens c", ListaItens.class);
		resultado = query.getResultList();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<ListaItens> buscarItensListaPorPKLista(Integer codLista) {
		List<ListaItens> resultado = new ArrayList<>();
		Query query = em.createQuery("select c from ListaItens c where c.fkLista = :codLista", ListaItens.class)
				.setParameter("codLista", codLista);
		resultado = query.getResultList();
		return resultado;
	}
}
