package br.com.softplan.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.softplan.controller.Criptografia;
import br.com.softplan.dao.impl.PessoaDAO;
import br.com.softplan.domain.view.UsuarioLogado;
import br.com.softplan.utils.Mensagens;
import br.com.softplan.utils.Pages;
import br.com.softplan.utils.SessionContext;
import lombok.Data;

@Data
@ManagedBean(name = "loginBean")
@ViewScoped
@URLMappings(mappings = { @URLMapping(id = "login", pattern = "/login", viewId = "login.xhtml") })
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -467725084182618588L;

	private static final String BEM_VINDO = "Bem-vindo";
	private static final String CREDENCIAIS_INVALIDAS = "Credenciais inválidas";

	private String login;
	private String senha;

	@PostConstruct
	public void init() {
	}

	public void log_in() {
		PessoaDAO dao = new PessoaDAO();
		try {
			UsuarioLogado usuarioLogado = null; //TODO
			if (usuarioLogado != null) {
				SessionContext.getInstance().setUsuarioLogado(usuarioLogado);
				Mensagens.info(BEM_VINDO, true);
				Pages.redirecionarPagina(Pages.PESSOA);
			} else {
				Mensagens.warn(CREDENCIAIS_INVALIDAS);
			}
		} catch (Exception e) {
			Mensagens.error(null, e);
		}

	}

	public void log_out() {
		try {
			SessionContext.getInstance().encerrarSessao();
			Pages.redirecionarPagina(Pages.LOGIN);
		} catch (Exception e) {
			Mensagens.error(null, e);
		}
	}

}