package br.ufscar.dc.dsw.domain;


public class Consulta {
    private String cpfCliente;
    private String cpfProfissional; 
    private String data;

    public Consulta(String cpfCliente, String cpfProfissional, String data) {
        this.setCpfCliente(cpfCliente);
        this.setCpfProfissional(cpfProfissional);
        this.setData(data);
    }

    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }
    public String getCpfCliente() { return this.cpfCliente; }

    public void setCpfProfissional(String cpfProfissional) { this.cpfProfissional = cpfProfissional; }
    public String getCpfProfissional() { return this.cpfProfissional; }

    public void setData(String data) { this.data = data; }
    public String getData() { return this.data; }

}