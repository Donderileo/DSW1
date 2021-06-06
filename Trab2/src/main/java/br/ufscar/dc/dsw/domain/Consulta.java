package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Locacao")
public class Consulta {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "locadora_id")
	private Profissional locadora;

    @NotBlank
	@Size(min = 8, max = 12)
	@Column(nullable = false, unique = false, length = 60)
	private String dataReserva;
    
	@Column(nullable = false, unique = false)
	private int horaReserva;
	
    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public Profissional getLocadora() {
		return locadora;
	}

	public void setLocadora(Profissional locadora) {
		this.locadora = locadora;
	}

	public String getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(String dataReserva) {
		this.dataReserva = dataReserva;
	}
	
	public int getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(int horaReserva) {
		this.horaReserva = horaReserva;
	}
}
