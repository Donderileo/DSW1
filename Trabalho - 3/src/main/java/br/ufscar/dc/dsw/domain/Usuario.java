package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {
	
	@NotBlank(message = "{NotBlank.usuario.email}")
	@Column(nullable = false, length = 64)
    private String email;
    
	@NotBlank (message = "{NotBlank.usuario.password}")
    @Column(nullable = false, length = 64)
    private String password;
    
	@NotBlank (message = "{NotBlank.usuario.cpf}")
    @Column(nullable = false, length = 14)
    private String CPF;
    
	@NotBlank (message = "{NotBlank.usuario.name}")
    @Column(nullable = false, length = 60)
    private String name;
    
	
    @Column(nullable = false, length = 10)
    private String papel;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		this.CPF = cpf;
	}

	public String getPapel() {
		return papel;
	}
	
	public void setPapel(String papel) {
		this.papel = papel;
	}
}
