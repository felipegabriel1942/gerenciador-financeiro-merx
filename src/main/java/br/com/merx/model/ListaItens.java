package br.com.merx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_lista_item")
public class ListaItens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_registro")
	@Getter
	@Setter
	private Integer idRegistro;
	
	@Column(name = "fk_produto")
	@Getter
	@Setter
	private Integer fkProduto;
	
	@Column(name = "fk_lista")
	@Getter
	@Setter
	private Integer fkLista;
	
	@Getter
	@Setter
	private String produto;
	
	@Getter
	@Setter
	private String categoria;
	
	@Getter
	@Setter
	private Double valor;
	
	@Getter
	@Setter
	private Integer quantidade;
	
	@Column(name = "valor_total")
	@Getter
	@Setter
	private Double valorTotal;

	

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
