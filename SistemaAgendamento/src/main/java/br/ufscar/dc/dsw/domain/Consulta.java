package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Consulta {
    private String cpfCliente;
    private String cpfProfissional; 
    private Date data;

    public Consulta(String cpfCliente, String cpfProfissional, Date data) {
        this.cpfCliente = cpfCliente;
        this.cpfProfissional = cpfProfissional;
        this.data = data;
    }

    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }
    public String getCpfCliente() { return this.cpfCliente; }

    public void setCpfProfissional(String cpfProfissional) { this.cpfProfissional = cpfProfissional; }
    public String getCpfProfissional() { return this.cpfProfissional; }

    public void setData(Date data) { this.data = data; }
    public Date getData() { return this.data; }

}