package br.com.fernandodutra.prj_01_senddocs.model.login;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/04/2019
 * Time: 17:08
 * Prj_01_SendDocs
 */
public class Login {

    private long idusuario;
    private String nome;
    private String email;
    private String senha;
    private int nivelacesso;

    public Login() {
        this.setIdusuario(-1);
        this.setNome("");
        this.setEmail("");
        this.setSenha("");
        this.setNivelacesso(-1);
    }

    public Login(long idusuario, String nome, String email, String senha, int nivelacesso) {
        this.setIdusuario(idusuario);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setNivelacesso(nivelacesso);
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivelacesso() {
        return nivelacesso;
    }

    public void setNivelacesso(int nivelacesso) {
        this.nivelacesso = nivelacesso;
    }
}
