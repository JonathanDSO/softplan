package br.com.softplan.utils;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class Pages {

	public static final String LOGIN = "login";
	public static final String PESSOA = "pessoa";

	public static void redirecionarPagina(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
