package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act003;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.activity.cliente.act001.ClienteActivity_Contract;
import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAO;
import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra'
 * User: Fernando Dutra
 * Date: 15/03/2019'
 * Time: 13:33
 * Prj_01_Protocolo
 */
public class ClienteSeachActivity_Presenter  implements ClienteSeachActivity_Contract.Presenter {

    private ClienteSeachActivity_Contract.View view;
    private ClienteDAO<Cliente> clienteDAO;

    public ClienteSeachActivity_Presenter(ClienteSeachActivity_Contract.View view, ClienteDAO<Cliente> clienteDAO) {
        this.view = view;
        this.clienteDAO = clienteDAO;
    }

}
