package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act002;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:24
 * Prj_01_SendDocs
 */
public interface UsuarioListActivity_Contract {

    interface View {
        void formCall(long idUsuario);
        void pesquisar();
        void cadastrar();
        void editar();
        void excluir();
        //
        void carregaLista();
        void carregaLista(HMAux filter, HMAux order);
    }

    interface Presenter {
        ArrayList<HMAux> buscarUsuario();

        ArrayList<HMAux> buscarUsuario(HMAux filter, HMAux order);

        int excluirUsuario(long idUsuario);
    }

}
