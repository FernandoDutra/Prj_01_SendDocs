package br.com.fernandodutra.prj_01_senddocs.model.protocoloitens;

public class ProtocoloItens {

    private long idprotocolo;
    private long idprotocoloitens;
    private long idtipodoc;
    private String nomeprotocolo;
    private String observacao;

    public ProtocoloItens() {
        this.setIdprotocolo(-1);
        this.setIdprotocoloitens(-1);
        this.setIdtipodoc(-1);
        this.setNomeprotocolo("");
        this.setObservacao("");
    }

    public ProtocoloItens(long idprotocolo, long idprotocoloitens, long idtipodoc, String nomeprotocolo, String observacao) {
        this.setIdprotocolo(idprotocolo);
        this.setIdprotocoloitens(idprotocoloitens);
        this.setIdtipodoc(idtipodoc);
        this.setNomeprotocolo(nomeprotocolo);
        this.setObservacao(observacao);
    }

    public long getIdprotocolo() {
        return idprotocolo;
    }

    public void setIdprotocolo(long idprotocolo) {
        this.idprotocolo = idprotocolo;
    }

    public long getIdprotocoloitens() {
        return idprotocoloitens;
    }

    public void setIdprotocoloitens(long idprotocoloitens) {
        this.idprotocoloitens = idprotocoloitens;
    }

    public long getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(long idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getNomeprotocolo() {
        return nomeprotocolo;
    }

    public void setNomeprotocolo(String nomeprotocolo) {
        this.nomeprotocolo = nomeprotocolo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
