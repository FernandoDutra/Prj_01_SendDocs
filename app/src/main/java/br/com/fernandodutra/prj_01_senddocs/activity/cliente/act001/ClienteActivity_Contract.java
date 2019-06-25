package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act001;

import android.os.Bundle;

import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;

public interface ClienteActivity_Contract {

    interface View {
        boolean validacao();

        void confirmarSaida();

        void listCall();

        long recuperarParametro();

    }

    interface Presenter {
        long nextID();

        Cliente findClienteByID(long idcliente);

        void salvar(Cliente cliente);

        void atualizar(Cliente cliente);

        void apagar(long idcliente);
    }

}
