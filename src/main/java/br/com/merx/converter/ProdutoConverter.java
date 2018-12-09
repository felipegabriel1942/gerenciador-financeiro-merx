package br.com.merx.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.merx.model.Produto;
import br.com.merx.util.EntityManagerUtil;

public class ProdutoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ("".equals(value)) {
			return null;
		}

		return EntityManagerUtil.geEntityManager().find(Produto.class, Integer.parseInt(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Produto obj = (Produto) value;

		if (obj.getIdProduto() == null) {
			return null;
		}

		return obj.getIdProduto().toString();
	}

}
