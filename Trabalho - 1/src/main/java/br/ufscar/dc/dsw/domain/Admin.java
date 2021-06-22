package br.ufscar.dc.dsw.domain;

public class Admin {

	private String nome;
	private String email;
	private String senha;
	private String papel;
	

	public Admin(String nome, String email, String senha, String papel) {	
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.papel = papel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	
}