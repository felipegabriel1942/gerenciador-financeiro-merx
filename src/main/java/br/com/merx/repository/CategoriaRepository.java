package br.com.merx.repository;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.merx.util.EntityManagerUtil;

@Stateless
@LocalBean
public class CategoriaRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public CategoriaRepository() {
		em = EntityManagerUtil.geEntityManager();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}
