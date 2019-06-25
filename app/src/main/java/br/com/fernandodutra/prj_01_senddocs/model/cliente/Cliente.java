package br.com.fernandodutra.prj_01_senddocs.model.cliente;

import java.util.ArrayList;
import java.util.Date;

import br.com.fernandodutra.prj_01_senddocs.model.departamento.Departamento;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

public class Cliente {

    private long idcliente;
    private String nome;
    private String cpfcnpj;
    private String rgie;
    private Date dataAbertura;
    private String site;
    private String email;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private ArrayList<Departamento> departamentos;

    public Cliente() {
        this.setNome("");
        this.setCpfcnpj("");
        this.setRgie("");
        this.setDataAbertura(null);
        this.setSite("");
        this.setEmail("");
        this.setCep("");
        this.setEndereco("");
        this.setNumero("");
        this.setBairro("");
        this.setCidade("");
        this.setEstado("");
        this.setTelefone("");
        this.setCelular("");
    }

    public Cliente(long idcliente, String nome, String cpfcnpj, String rgie, Date dataAbertura, String site, String email, String cep, String endereco, String numero, String bairro, String cidade, String estado, String telefone, String celular) {
        this.setNome(nome);
        this.setCpfcnpj(cpfcnpj);
        this.setRgie(rgie);
        this.setDataAbertura(dataAbertura);
        this.setSite(site);
        this.setEmail(email);
        this.setCep(cep);
        this.setEndereco(endereco);
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.setTelefone(telefone);
        this.setCelular(celular);
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getRgie() {
        return rgie;
    }

    public void setRgie(String rgie) {
        this.rgie = rgie;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public String getStrDataabertura() {
        return ToolBox.converteDateToStr(this.getDataAbertura());
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setStDataAbertura(String strDataAbertura) {
        this.dataAbertura = ToolBox.converteStrToDate(strDataAbertura);
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(ArrayList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
