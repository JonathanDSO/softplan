package br.com.softplan.enums;

import java.util.HashMap;
import java.util.Map;

public enum SexoEnum {

	MASCULINO("M", "Masculino"), FEMININO("F", "Feminino");

	private String codigo;
	private String descricao;

	private static final Map<String, SexoEnum> sexoPorCodigo = new HashMap<>();

	static {
		for (SexoEnum sexoEnum : SexoEnum.values()) {
			sexoPorCodigo.put(sexoEnum.getCodigo(), sexoEnum);
		}
	}

	SexoEnum(String codigo) {
		this.codigo = codigo;
	}

	private SexoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static SexoEnum geraCodigo(String cod) {
		if (cod.equals("M")) {
			return MASCULINO;
		}
		return FEMININO;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static SexoEnum getSexoPorCodigo(String codigo) {
		return sexoPorCodigo.get(codigo);
	}

}
