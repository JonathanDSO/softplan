package br.com.softplan.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softplan.utils.Pages;
import lombok.Data;

@Data
@ManagedBean(name = "templateBean")
@SessionScoped
public class TemplateBean implements Serializable {

	private static final long serialVersionUID = 8496180883438198704L;

	@PostConstruct
	public void init() {
	}

	public void redirecionaPessoa() {
		Pages.redirecionarPagina(Pages.PESSOA);
	}

}
