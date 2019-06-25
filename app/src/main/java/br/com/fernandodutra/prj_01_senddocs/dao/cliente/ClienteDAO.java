package br.com.fernandodutra.prj_01_senddocs.dao.cliente;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;


public interface ClienteDAO<T> {

    void insertCliente(Cliente cliente) throws SQLException;

    void updateCliente(Cliente cliente) throws SQLException;

    void deleteCliente(long idcliente) throws SQLException;

    Cliente findClienteByID(long idcliente) throws SQLException;

    ArrayList<HMAux> findListCliente() throws SQLException;

    ArrayList<HMAux> findListCliente(HMAux filter, HMAux order) throws SQLException;

    long nextID() throws SQLException;

}
