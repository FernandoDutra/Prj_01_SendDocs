package br.com.fernandodutra.prj_01_senddocs.dao.login;

import br.com.fernandodutra.prj_01_senddocs.model.login.Login;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/04/2019
 * Time: 17:07
 * Prj_01_SendDocs
 */
public interface LoginDAO<T> {

    Login findLogin(String login, String senha);

}
