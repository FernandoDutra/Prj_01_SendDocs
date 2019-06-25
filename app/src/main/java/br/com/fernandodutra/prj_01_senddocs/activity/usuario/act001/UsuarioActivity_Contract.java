package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act001;

import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:24
 * Prj_01_SendDocs
 */
public interface UsuarioActivity_Contract {

    interface View {
        boolean validacao();

        void confirmarSaida();

        void listCall();

        long recuperarParametro();
    }

    interface Presenter {
        long nextID();

        Usuario findUsuarioByID(long idUsuario);

        void salvar(Usuario usuario);

        void atualizar(Usuario usuario);

        void apagar(long idUsuario);
    }

}
