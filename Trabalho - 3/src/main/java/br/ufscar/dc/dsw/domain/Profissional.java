package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "consultas",  "password" , "papel" })
@Entity
@Table(name = "Profissional")
public class Profissional extends Usuario {

    @NotBlank (message = "{NotBlank.profissional.especialidade}")
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String especialidade;
    
    @NotBlank (message = "{NotBlank.profissional.curriculo}")
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String curriculo;
    
	@OneToMany(mappedBy = "profissional")
	private List<Consulta> consultas;
	
    public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
    public String getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
