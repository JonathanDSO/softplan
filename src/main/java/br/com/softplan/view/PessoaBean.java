package br.com.softplan.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.softplan.domain.Pessoa;
import br.com.softplan.enums.SexoEnum;
import br.com.softplan.service.PessoaService;
import br.com.softplan.utils.Mensagens;
import lombok.Data;

@Data
@ManagedBean(name = "pessoaBean")
@ViewScoped
@URLMappings(mappings = { @URLMapping(id = "pessoa", pattern = "/pessoa", viewId = "pessoa.xhtml") })
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = -4571126044686555398L;

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Pessoa pessoa = new Pessoa();
	private SexoEnum[] sexos = SexoEnum.values();
	private boolean editar;
	private boolean formularioAberto;

	private PessoaService pessoaService = PessoaService.getInstance();

	@PostConstruct
	public void init() {
		listarPessoas();
	}

	public void editarCadastro() {
		editar = true;
		abrirFormulario();
	}

	public void novoCadastro() {
		editar = false;
		pessoa = new Pessoa();
		abrirFormulario();
	}

	public void abrirFormulario() {
		formularioAberto = true;
	}

	public void fecharFormulario() {
		formularioAberto = false;
	}

	public void verificarCPF(AjaxBehaviorEvent ajaxBehaviorEvent) {
		if (pessoaService.cpfJaCadastrado(pessoa.getCpf())) {

		}
	}

	public void salvar() {
		validarFormulario();
		if (editar) {
			alterarUsuario();
			listarPessoas();
		} else {
			cadastrar();
		}
		pessoa = new Pessoa();
		fecharFormulario();
	}

	public void cadastrar() {
		pessoaService.cadastrar(pessoa);
	}

	public void alterarUsuario() {
		pessoaService.alterarUsuario(pessoa);
	}

	public void remover() {
		pessoaService.remover(pessoa.getId());
		listarPessoas();
	}

	public void buscarPorID() {
		pessoa = pessoaService.buscarPorID(pessoa.getId());
	}

	public void listarPessoas() {
		pessoas.addAll(pessoaService.buscarTodos());
	}

	private void validarFormulario() {
		if (!pessoaService.validarCPF(pessoa.getEmail())) {
			Mensagens.warn("E-mail inválido");
		}
		if (!pessoaService.validarCPF(pessoa.getCpf())) {
			Mensagens.warn("CPF inválido");
		}
	}

}
