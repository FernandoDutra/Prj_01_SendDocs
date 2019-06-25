package br.com.fernandodutra.prj_01_senddocs.activity.login.act001;

import android.os.Bundle;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/04/2019
 * Time: 16:26
 * Prj_01_SendDocs
 */
public interface LoginActivity_Contract {

    interface View {
        void exibirMensagem(int resourceTxT);

        void listCall(Bundle mBundle);

        void limparFormulario();
    }

    interface Presenter {
        void processarLogin(String nome, String senha);

        void processarLimpeza();
    }
}
