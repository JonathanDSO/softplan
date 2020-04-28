package br.com.softplan.service;

import java.util.List;

import br.com.softplan.dao.impl.PessoaDAO;
import br.com.softplan.domain.Pessoa;
import br.com.softplan.utils.ValidadorCPF;
import br.com.softplan.utils.ValidadorEmail;

public class PessoaService {

	public static PessoaService instance;

	public static PessoaService getInstance() {
		if (instance == null) {
			instance = new PessoaService();
		}
		return instance;
	}

	public boolean validarEmail(String email) {
		return ValidadorEmail.isEmail(email);
	}

	public boolean validarCPF(String cpf) {
		return ValidadorCPF.isCPF(removerPontosETraco(cpf));
	}

	public boolean cpfJaCadastrado(String cpf) {
		try {
			return new PessoaDAO().buscarPorCPF(cpf) != null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void cadastrar(Pessoa pessoa) {
		try {
			pessoa.setCpf(removerPontosETraco(pessoa.getCpf()));
			new PessoaDAO().cadastrar(pessoa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterarUsuario(Pessoa pessoa) {
		try {
			pessoa.setCpf(removerPontosETraco(pessoa.getCpf()));
			new PessoaDAO().alterarUsuario(pessoa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remover(Integer id) {
		try {
			new PessoaDAO().remover(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Pessoa buscarPorID(Integer id) {
		try {
			return new PessoaDAO().buscarPorID(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Pessoa> buscarTodos() {
		try {
			return new PessoaDAO().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String removerPontosETraco(String texto) {
		return texto.replace(".", "").replace("-", "");
	}

}
