package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.domain.Pessoa; 
import br.ufscar.dc.dsw.domain.Consulta; 

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends Pessoa {

	@NotBlank
	@Size(min = 14, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String CPF;
	
	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

    @NotBlank
	@Size(min = 11, max = 15)
	@Column(nullable = false, unique = false, length = 60)
	private String telefone;

    @NotBlank
	@Size(min = 1, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String sexo;

    @NotBlank
	@Size(min = 8, max = 10)
	@Column(nullable = false, unique = false, length = 60)
	private String dataNasc;

	@OneToMany(mappedBy = "cliente")
	private List<Consulta> consultas;
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    public String getsexo() {
		return sexo;
	}

	public void setsexo(String sexo) {
		this.sexo = sexo;
	}

    public String getdataNasc() {
		return dataNasc;
	}

	public void setdataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
