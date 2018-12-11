package br.com.merx.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	@Getter
	@Setter
	private Integer idProduto;
	
	@Getter
	@Setter
	private String produto;
	
	@Getter
	@Setter
	private Double valor;
	
	@Column(name="data_cadastro", columnDefinition="TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date dataCadastro;
	
	@Column(name="fk_categoria")
	@Getter
	@Setter
	private Integer fkCategoria;
	
	@Transient
	@Getter
	@Setter
	private String nomeCategoria;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return produto;
	}

	
	
	
}
