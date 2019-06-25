package br.com.fernandodutra.prj_01_senddocs.model.departamento;

public class Departamento {

    private long idDepartamento;
    private String nome;
    private String responsavel;
    private String telefone;
    private String celular;
    private String observacao;

    public Departamento() {
        this.setIdDepartamento(-1L);
        this.setNome("");
        this.setResponsavel("");
        this.setTelefone("");
        this.setCelular("");
        this.setObservacao("");
    }

    public Departamento(long idDepartamento, String nome, String responsavel, String telefone, String celular, String observacao) {
        this.idDepartamento = idDepartamento;
        this.nome = nome;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.celular = celular;
        this.observacao = observacao;
    }

    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


}
