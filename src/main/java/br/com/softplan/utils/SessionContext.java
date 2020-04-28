package br.com.softplan.utils;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.softplan.domain.view.UsuarioLogado;

@SessionScoped
public class SessionContext {

	private static SessionContext instance;

	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}
		return instance;
	}

	private SessionContext() {
	}

	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}

	public void encerrarSessao() {
		currentExternalContext().invalidateSession();
	}

	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}

	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}
	

	public UsuarioLogado getUsuarioLogado() {
		return (UsuarioLogado) getAttribute("usuarioLogado");
	}

	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		setAttribute("usuarioLogado", usuarioLogado);
	}
}
