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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_lista")
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_lista")
	@Getter
	@Setter
	private Integer idLista;
	
	@Getter
	@Setter
	private String descricao;
	
	@Column(name= "data_cadastro", columnDefinition="TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date dataCadastro;
	
	@Column(name = "valor_total")
	@Getter
	@Setter
	private Double valorTotal;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLista == null) ? 0 : idLista.hashCode());
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
		Lista other = (Lista) obj;
		if (idLista == null) {
			if (other.idLista != null)
				return false;
		} else if (!idLista.equals(other.idLista))
			return false;
		return true;
	}
	
	
	
}
