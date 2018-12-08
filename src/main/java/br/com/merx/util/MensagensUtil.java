package br.com.merx.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagensUtil {
	
	public static void mensagemGenerica(String titulo, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(titulo,mensagem));
	}
}
