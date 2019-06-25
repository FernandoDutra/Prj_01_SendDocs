package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act002;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAO;
import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class ClienteListActivity_Presenter implements ClienteListActivity_Contract.Presenter {

    private ClienteListActivity_Contract.View view;
    private ClienteDAO<Cliente> clienteDAO;

    public ClienteListActivity_Presenter(ClienteListActivity_Contract.View view, ClienteDAO<Cliente> clienteDAO) {
        this.view = view;
        this.clienteDAO = clienteDAO;
    }

    @Override
    public ArrayList<HMAux> buscarCliente() {
        try {
            return this.clienteDAO.findListCliente();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HMAux> buscarCliente(HMAux filter, HMAux order) {
        try {
            return this.clienteDAO.findListCliente(filter, order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int excluirCliente(long idcliente) {
        try {
            this.clienteDAO.deleteCliente(idcliente);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
