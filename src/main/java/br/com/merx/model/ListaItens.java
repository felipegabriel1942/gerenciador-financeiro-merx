package br.com.merx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_registro_lista_produto_categoria")
public class ListaItens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_registro")
	private Integer idRegistro;
	
	@Column(name = "fk_produto")
	private Integer fkProduto;
	
	@Column(name = "fk_lista")
	private Integer fkLista;
	
	private String produto;
	
	private String categoria;
	
	private Double valor;

	

	public Integer getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Integer getFkProduto() {
		return fkProduto;
	}

	public void setFkProduto(Integer fkProduto) {
		this.fkProduto = fkProduto;
	}

	public Integer getFkLista() {
		return fkLista;
	}

	public void setFkLista(Integer fkLista) {
		this.fkLista = fkLista;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRegistro == null) ? 0 : idRegistro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaItens other = (ListaItens) obj;
		if (idRegistro == null) {
			if (other.idRegistro != null)
				return false;
		} else if (!idRegistro.equals(other.idRegistro))
			return false;
		return true;
	}


	
	
}
