package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act001;

import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 10/04/2019
 * Time: 21:15
 * Prj_01_SendDocs
 */
public interface TipoDocActivity_Contract {

    interface View {
        boolean validacao();

        void confirmarSaida();

        void listCall();

        long recuperarParametro();
    }

    interface Presenter {
        long nextID();

        TipoDoc findTipoDocByID(long idtipodoc);

        void salvar(TipoDoc tipoDoc);

        void atualizar(TipoDoc tipoDoc);

        void apagar(long idTipoDoc);
    }


}
