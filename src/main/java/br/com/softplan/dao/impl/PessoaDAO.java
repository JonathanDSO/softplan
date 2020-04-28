package br.com.softplan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.softplan.domain.Pessoa;
import br.com.softplan.enums.SexoEnum;

public class PessoaDAO extends DAO {

	private static final String TABLE_PESSOA = "\"pessoa\"";
	private static final String FIELD_ID = "id";
	private static final String FIELD_NOME = "nome";
	private static final String FIELD_SEXO = "sexo";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_DATA_NASCIMENTO = "data_nascimento";
	private static final String FIELD_NATURALIDADE = "naturalidade";
	private static final String FIELD_NACIONALIDADE = "nacionalidade";
	private static final String FIELD_CPF = "cpf";

	public void cadastrar(Pessoa pessoa) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + TABLE_PESSOA + " (");
		sb.append(FIELD_NOME).append(",");
		sb.append(FIELD_SEXO).append(",");
		sb.append(FIELD_EMAIL).append(",");
		sb.append(FIELD_DATA_NASCIMENTO).append(",");
		sb.append(FIELD_NATURALIDADE).append(",");
		sb.append(FIELD_NACIONALIDADE).append(",");
		sb.append(FIELD_CPF).append(")");
		sb.append(" VALUES (?,?,?,?,?,?,?) ");
		try {
			open();
			setStmt(getCon().prepareStatement(sb.toString()));
			getStmt().setString(1, pessoa.getNome());
			getStmt().setString(2, pessoa.getSexo() != null ? pessoa.getSexo().getCodigo() : null);
			getStmt().setString(3, pessoa.getEmail());
			getStmt().setDate(4, new java.sql.Date(pessoa.getDataNascimento().getTime()));
			getStmt().setString(5, pessoa.getNaturalidade());
			getStmt().setString(6, pessoa.getNacionalidade());
			getStmt().setString(7, pessoa.getCpf());
			getStmt().execute();
		} finally {
			getStmt().close();
			close();
		}
	}

	public void alterarUsuario(Pessoa pessoa) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE " + TABLE_PESSOA + " set ");
		sb.append(FIELD_NOME).append(" = ?,");
		sb.append(FIELD_SEXO).append(" = ?,");
		sb.append(FIELD_EMAIL).append(" = ?,");
		sb.append(FIELD_DATA_NASCIMENTO).append(" = ?,");
		sb.append(FIELD_NATURALIDADE).append(" = ?,");
		sb.append(FIELD_NACIONALIDADE).append(" = ?,");
		sb.append(FIELD_CPF).append(" = ? ");
		sb.append("WHERE ").append(FIELD_ID).append(" = ?");
		try {
			open();
			setStmt(getCon().prepareStatement(sb.toString()));
			getStmt().setString(1, pessoa.getNome());
			getStmt().setString(2, pessoa.getSexo() != null ? pessoa.getSexo().getCodigo() : null);
			getStmt().setString(3, pessoa.getEmail());
			getStmt().setDate(4, new java.sql.Date(pessoa.getDataNascimento().getTime()));
			getStmt().setString(5, pessoa.getNaturalidade());
			getStmt().setString(6, pessoa.getNacionalidade());
			getStmt().setString(7, pessoa.getCpf());
			getStmt().setInt(8, pessoa.getId());
			getStmt().executeUpdate();
		} finally {
			getStmt().close();
			close();
		}
	}

	public void remover(Integer id) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM " + TABLE_PESSOA + " WHERE " + FIELD_ID + " = " + id);
		try {
			open();
			setStmt(getCon().prepareStatement(sb.toString()));
			getStmt().execute();
		} finally {
			getStmt().close();
			close();
		}
	}

	public Pessoa buscarPorID(Integer id) throws Exception {
		Pessoa pessoa = null;
		try {
			open();
			setStmt(getCon().prepareStatement("SELECT * FROM " + TABLE_PESSOA + " WHERE " + FIELD_ID + " = " + id));
			setRs(getStmt().executeQuery());
			if (getRs().next()) {
				montarObjetoPessoa(getRs());
			}
		} finally {
			getStmt().close();
			close();
		}
		return pessoa;
	}

	public Pessoa buscarPorCPF(String cpf) throws Exception {
		Pessoa pessoa = null;
		try {
			open();
			setStmt(getCon().prepareStatement("SELECT * FROM " + TABLE_PESSOA + " WHERE " + FIELD_CPF + " = " + cpf));
			setRs(getStmt().executeQuery());
			if (getRs().next()) {
				montarObjetoPessoa(getRs());
			}
		} finally {
			getStmt().close();
			close();
		}
		return pessoa;
	}

	private Pessoa montarObjetoPessoa(ResultSet resultSet) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(resultSet.getInt(FIELD_ID));
		pessoa.setCpf(resultSet.getString(FIELD_CPF));
		pessoa.setDataNascimento(resultSet.getDate(FIELD_DATA_NASCIMENTO));
		pessoa.setEmail(resultSet.getString(FIELD_EMAIL));
		pessoa.setNacionalidade(resultSet.getString(FIELD_NACIONALIDADE));
		pessoa.setNaturalidade(resultSet.getString(FIELD_NATURALIDADE));
		pessoa.setNome(resultSet.getString(FIELD_NOME));
		pessoa.setSexo(SexoEnum.geraCodigo(resultSet.getString(FIELD_SEXO)));
		return pessoa;
	}

	public List<Pessoa> buscarTodos() throws Exception {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			open();
			setStmt(getCon().prepareStatement("SELECT * FROM " + TABLE_PESSOA));
			setRs(getStmt().executeQuery());
			while (getRs().next()) {
				pessoas.add(montarObjetoPessoa(getRs()));
			}
		} finally {
			getStmt().close();
			close();
		}
		return pessoas;
	}

}