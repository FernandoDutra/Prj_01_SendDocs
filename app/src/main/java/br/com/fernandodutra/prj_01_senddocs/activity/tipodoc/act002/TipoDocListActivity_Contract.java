package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act002;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 12:21
 * Prj_01_SendDocs
 */
public interface TipoDocListActivity_Contract {

    interface View {
        void formCall(long idTipoDoc);
    }

    interface Presenter {
        ArrayList<HMAux> buscarTipoDoc();

        ArrayList<HMAux> buscarTipoDoc(HMAux filter, HMAux order);

        int excluirTipoDoc(long idTipoDoc);
    }


}
