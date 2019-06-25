package br.com.fernandodutra.prj_01_senddocs.model.protocolo;

import android.graphics.Bitmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

public class Protocolo {

    private long idprotocolo;
    private Date dataemissao;
    private Date dataprevisao;
    private int totalprot;
    private int codorigem;
    private String nomeorigem;
    private String endorigem;
    private String emailorigem;
    private int coddestino;
    private String nomedestino;
    private String enddestino;
    private String emaildestino;
    private int codentregador;
    private String nomeentregador;
    private String entregue;
    private Date dataentrega;
    private String rgrecebedor;
    private Bitmap assinatura;
    private Bitmap foto;
    private ArrayList<HMAux> protocoloitens;

    public Protocolo() {
        this.setIdprotocolo(idprotocolo);
        this.setDataemissao(dataemissao);
        this.setDataemissao(dataprevisao);
        this.setTotalprot(totalprot);
        this.setCodorigem(codorigem);
        this.setNomedestino(nomeorigem);
        this.setEndorigem(endorigem);
        this.setEmailorigem(emailorigem);
        this.setCoddestino(coddestino);
        this.setNomedestino(nomedestino);
        this.setEnddestino(enddestino);
        this.setEmaildestino(emaildestino);
        this.setCodentregador(codentregador);
        this.setNomeentregador(nomeentregador);
        this.setEntregue(entregue);
        this.setDataentrega(dataentrega);
        this.setRgrecebedor(rgrecebedor);
        this.setAssinatura(assinatura);
        this.setFoto(foto);
    }

    public Protocolo(long idprotocolo, Date dataemissao, Date dataprevisao, int totalprot, int codorigem, String nomeorigem, String endorigem, String emailorigem, int coddestino, String nomedestino, String enddestino, String emaildestino, int codentregador, String nomeentregador, String entregue, Date dataentrega, String rgrecebedor, Bitmap assinatura, Bitmap foto) {
        this.idprotocolo = idprotocolo;
        this.dataemissao = dataemissao;
        this.dataprevisao = dataprevisao;
        this.totalprot = totalprot;
        this.codorigem = codorigem;
        this.nomeorigem = nomeorigem;
        this.endorigem = endorigem;
        this.emailorigem = emailorigem;
        this.coddestino = coddestino;
        this.nomedestino = nomedestino;
        this.enddestino = enddestino;
        this.emaildestino = emaildestino;
        this.codentregador = codentregador;
        this.nomeentregador = nomeentregador;
        this.entregue = entregue;
        this.dataentrega = dataentrega;
        this.rgrecebedor = rgrecebedor;
        this.assinatura = assinatura;
        this.foto = foto;
    }

    public long getIdprotocolo() {
        return idprotocolo;
    }

    public void setIdprotocolo(long idprotocolo) {
        this.idprotocolo = idprotocolo;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

    public String getStrDataemissao() {
        return ToolBox.converteDateToStr(this.dataemissao);
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    public void setStrDataemissao(String strDataemissao) {
        this.dataemissao = ToolBox.converteStrToDate(strDataemissao);
    }

    public Date getDataprevisao() {
        return dataprevisao;
    }

    public String getStrDataprevisao() {
        return ToolBox.converteDateToStr(this.dataprevisao);
    }

    public void setDataprevisao(Date dataprevisao) {
        this.dataprevisao = dataprevisao;
    }

    public void setStrDataprevisao(String strDataPrevisao) {
        this.dataprevisao = ToolBox.converteStrToDate(strDataPrevisao);
    }

    public int getTotalprot() {
        return totalprot;
    }

    public void setTotalprot(int totalprot) {
        this.totalprot = totalprot;
    }

    public int getCodorigem() {
        return codorigem;
    }

    public void setCodorigem(int codorigem) {
        this.codorigem = codorigem;
    }

    public String getNomeorigem() {
        return nomeorigem;
    }

    public void setNomeorigem(String nomeorigem) {
        this.nomeorigem = nomeorigem;
    }

    public String getEndorigem() {
        return endorigem;
    }

    public void setEndorigem(String endorigem) {
        this.endorigem = endorigem;
    }

    public String getEmailorigem() {
        return emailorigem;
    }

    public void setEmailorigem(String emailorigem) {
        this.emailorigem = emailorigem;
    }

    public int getCoddestino() {
        return coddestino;
    }

    public void setCoddestino(int coddestino) {
        this.coddestino = coddestino;
    }

    public String getNomedestino() {
        return nomedestino;
    }

    public void setNomedestino(String nomedestino) {
        this.nomedestino = nomedestino;
    }

    public String getEnddestino() {
        return enddestino;
    }

    public void setEnddestino(String enddestino) {
        this.enddestino = enddestino;
    }

    public String getEmaildestino() {
        return emaildestino;
    }

    public void setEmaildestino(String emaildestino) {
        this.emaildestino = emaildestino;
    }

    public int getCodentregador() {
        return codentregador;
    }

    public void setCodentregador(int codentregador) {
        this.codentregador = codentregador;
    }

    public String getNomeentregador() {
        return nomeentregador;
    }

    public void setNomeentregador(String nomeentregador) {
        this.nomeentregador = nomeentregador;
    }

    public String getEntregue() {
        return entregue;
    }

    public void setEntregue(String entregue) {
        this.entregue = entregue;
    }

    public Date getDataentrega() {
        return dataentrega;
    }

    public String getStrDataentrega() {
        return ToolBox.converteDateToStr(this.dataentrega);
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }

    public void setStrDataentrega(String strDataentrega) {
        this.dataentrega = ToolBox.converteStrToDate(strDataentrega);
    }

    public String getRgrecebedor() {
        return rgrecebedor;
    }

    public void setRgrecebedor(String rgrecebedor) {
        this.rgrecebedor = rgrecebedor;
    }

    public Bitmap getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Bitmap assinatura) {
        this.assinatura = assinatura;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public ArrayList<HMAux> getProtocoloitens() {
        return protocoloitens;
    }

    public void setProtocoloitens(ArrayList<HMAux> protocoloitens) {
        this.protocoloitens = protocoloitens;
    }
}
