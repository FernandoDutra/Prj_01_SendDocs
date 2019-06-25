package br.com.fernandodutra.prj_01_senddocs.activity.login.act001;

import android.os.Bundle;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.dao.login.LoginDAO;
import br.com.fernandodutra.prj_01_senddocs.dao.login.LoginDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.model.login.Login;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/04/2019
 * Time: 16:26
 * Prj_01_SendDocs
 */
public class LoginActivity_Presenter implements LoginActivity_Contract.Presenter {

    private LoginActivity_Contract.View view;
    private LoginDAO<Login> loginDAO;

    public LoginActivity_Presenter(LoginActivity_Contract.View view, LoginDAO<Login> loginDAO) {
        this.view = view;
        this.loginDAO = loginDAO;
    }

    public LoginActivity_Presenter(LoginActivity view, LoginDAOLiter loginDAOLiter) {
    }

    @Override
    public void processarLogin(String nome, String senha) {
        if (nome.trim().isEmpty()){
            view.exibirMensagem(R.string.loginactivity_erro_login_requerido);

            return;
        }
        //
        if (senha.trim().isEmpty()){
            view.exibirMensagem(R.string.loginactivity_erro_senha_requerido);

            return;
        }
        //
        Login login = loginDAO.findLogin(nome, senha);
        if (login != null){
            view.listCall(new Bundle());
        } else {
            view.exibirMensagem(R.string.loginactivity_erro_credencial_inválida);
        }
    }

    @Override
    public void processarLimpeza() {

    }
}
