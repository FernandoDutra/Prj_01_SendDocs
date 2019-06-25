package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act001;

import java.sql.SQLException;

import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAO;
import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;

public class ClienteActivity_Presenter implements ClienteActivity_Contract.Presenter {

    private ClienteActivity_Contract.View view;
    private ClienteDAO<Cliente> clienteDAO;

    public ClienteActivity_Presenter(ClienteActivity_Contract.View view, ClienteDAO<Cliente> clienteDAO) {
        this.view = view;
        this.clienteDAO = clienteDAO;
    }

    @Override
    public long nextID() {
        try {
            return clienteDAO.nextID();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    @Override
    public Cliente findClienteByID(long idcliente) {
        try {
            return clienteDAO.findClienteByID(idcliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void salvar(Cliente cliente) {
        try {
            clienteDAO.insertCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            clienteDAO.updateCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(long idcliente) {
        try {
            clienteDAO.deleteCliente(idcliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
