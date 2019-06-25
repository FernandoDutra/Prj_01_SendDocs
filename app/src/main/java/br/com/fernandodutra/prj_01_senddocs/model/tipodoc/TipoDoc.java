package br.com.fernandodutra.prj_01_senddocs.model.tipodoc;

public class TipoDoc {

    private long idtipodoc;
    private String nome;
    private String observacao;

    public TipoDoc() {
        this.setIdtipodoc(-1);
        this.setNome("");
        this.setObservacao("");
    }

    public TipoDoc(long idtipodoc, String nome, String observacao) {
        this.setIdtipodoc(idtipodoc);
        this.setNome(nome);
        this.setObservacao(observacao);
    }

    public long getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(long idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
