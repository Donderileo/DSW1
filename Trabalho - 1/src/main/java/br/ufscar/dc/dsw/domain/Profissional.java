package br.ufscar.dc.dsw.domain;


public class Profissional {
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String especialidade;
    private String curriculo;

    public Profissional(String cpf) {
        this.setCpf(cpf);
    }

    public Profissional(String cpf, String nome, String email, String senha, String especialidade, String curriculo) {
        this.setCpf(cpf);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setEspecialidade(especialidade);
        this.setCurriculo(curriculo);
    }

    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCpf() { return this.cpf; }

    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return this.nome; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return this.email; }

    public void setSenha(String senha) { this.senha = senha; }
    public String getSenha() { return this.senha; }

    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getEspecialidade() { return this.especialidade; }

    public void setCurriculo(String curriculo) { this.curriculo = curriculo; }
    public String getCurriculo() { return this.curriculo; }

}