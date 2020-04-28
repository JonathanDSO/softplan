package br.com.softplan.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagens {

	public static String CADASTRADO_COM_SUCESSO = "Cadastrado com sucesso.";
	public static String ALTERADO_COM_SUCESSO = "Alterado com sucesso.";

	public static void info(String informacao, boolean isExibirEmOutraPagina) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info.", informacao));
		context.getExternalContext().getFlash().setKeepMessages(isExibirEmOutraPagina);
	}

	public static void warn(String alerta) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta!", alerta));
	}

	public static void error(String erro, Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Entre em contato com o administrador."));
		e.printStackTrace();
	}

	public static void fatal() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Erro no sistema"));
	}

}
