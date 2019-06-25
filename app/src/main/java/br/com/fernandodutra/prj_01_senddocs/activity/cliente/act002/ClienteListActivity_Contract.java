package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act002;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public interface ClienteListActivity_Contract {

    interface View {
        void formCall(long idcliente);
    }

    interface Presenter {
        ArrayList<HMAux> buscarCliente();

        ArrayList<HMAux> buscarCliente(HMAux filter, HMAux order);

        int excluirCliente(long idcliente);
    }

}
