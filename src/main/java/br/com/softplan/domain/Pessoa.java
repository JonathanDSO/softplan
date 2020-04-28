package br.com.softplan.domain;

import java.io.Serializable;
import java.util.Date;

import br.com.softplan.enums.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private SexoEnum sexo;
	private String email;
	private Date dataNascimento;
	private String naturalidade;
	private String nacionalidade;
	private String cpf;

	public Pessoa(Integer id) {
		this.id = id;
	}

}
