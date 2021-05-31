package br.ufscar.dc.dsw.domain;
import java.sql.Timestamp;
import java.util.Date;
import br.ufscar.dc.dsw.domain.*;

public class ConsultaClient {
    private Cliente cliente;
    private Profissional profissional; 
    private Timestamp data;

    public ConsultaClient(Cliente cliente, Profissional profissional, Timestamp data) {
        this.cliente = cliente;
        this.profissional = profissional;
        this.data = data;
    }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Cliente getCliente() { return this.cliente; }

    public void setProfissional(Profissional profissional) { this.profissional = profissional; }
    public Profissional getProfissional() { return this.profissional; }

    public void setData(Timestamp data) { this.data = data; }
    public Timestamp getData() { return this.data; }

}