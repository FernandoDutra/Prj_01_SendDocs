package br.com.fernandodutra.prj_01_senddocs.dao.cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class ClienteDAOLiter extends DAO implements ClienteDAO<Cliente> {

    public static final String TABELA = "tb_cliente";
    public static final String IDCLIENTE = "idcliente";
    public static final String NOME = "nome";
    public static final String CPFCNPJ = "cpfcnpj";
    public static final String RGIE = "rgie";
    public static final String DATAABERTURA = "dataabertura";
    public static final String SITE = "site";
    public static final String EMAIL = "email";
    public static final String CEP = "cep";
    public static final String ENDERECO = "endereco";
    public static final String NUMERO = "numero";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";
    public static final String TELEFONE = "telefone";
    public static final String CELULAR = "celular";

    public ClienteDAOLiter(Context context) {
        super(context);
    }

    @Override
    public void insertCliente(Cliente cliente) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDCLIENTE, cliente.getIdcliente());
            cv.put(NOME, cliente.getNome());
            cv.put(CPFCNPJ, cliente.getCpfcnpj());
            cv.put(RGIE, cliente.getRgie());
            cv.put(DATAABERTURA, cliente.getStrDataabertura());
            cv.put(TELEFONE, cliente.getTelefone());
            cv.put(CELULAR, cliente.getCelular());
            cv.put(EMAIL, cliente.getEmail());
            cv.put(SITE, cliente.getSite());
            cv.put(CEP, cliente.getCep());
            cv.put(ENDERECO, cliente.getEndereco());
            cv.put(NUMERO, cliente.getNumero());
            cv.put(BAIRRO, cliente.getBairro());
            cv.put(CIDADE, cliente.getCidade());
            cv.put(ESTADO, cliente.getEstado());

            db.insert(TABELA, null, cv);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void updateCliente(Cliente cliente) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            String filter = " idcliente = ?";
            String[] arguments = {String.valueOf(cliente.getIdcliente())};
            //
            cv.put(NOME, cliente.getNome());
            cv.put(CPFCNPJ, cliente.getCpfcnpj());
            cv.put(RGIE, cliente.getRgie());
            cv.put(DATAABERTURA, cliente.getStrDataabertura());
            cv.put(TELEFONE, cliente.getTelefone());
            cv.put(CELULAR, cliente.getCelular());
            cv.put(EMAIL, cliente.getEmail());
            cv.put(SITE, cliente.getSite());
            cv.put(CEP, cliente.getCep());
            cv.put(ENDERECO, cliente.getEndereco());
            cv.put(NUMERO, cliente.getNumero());
            cv.put(BAIRRO, cliente.getBairro());
            cv.put(CIDADE, cliente.getCidade());
            cv.put(ESTADO, cliente.getEstado());

            db.update(TABELA, cv, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void deleteCliente(long idcliente) throws SQLException {
        openDataBase();
        try {
            String filter = " idcliente = ?";
            String[] arguments = {String.valueOf(idcliente)};
            //
            db.delete(TABELA, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    @Override
    public Cliente findClienteByID(long idcliente) throws SQLException {
        openDataBase();
        //
        Cursor cursor = null;
        //
        Cliente cAux = null;
        //
        try {
            String sqlQuery = " select * from tb_cliente where idcliente = ? ";
            String[] argumentos = {String.valueOf(idcliente)};

            cursor = db.rawQuery(sqlQuery, argumentos);

            while (cursor.moveToNext()) {
                cAux = new Cliente();
                cAux.setIdcliente(cursor.getLong(cursor.getColumnIndex(IDCLIENTE)));
                cAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                cAux.setCpfcnpj(cursor.getString(cursor.getColumnIndex(CPFCNPJ)));
                cAux.setRgie(cursor.getString(cursor.getColumnIndex(RGIE)));
                cAux.setStDataAbertura(cursor.getString(cursor.getColumnIndex(DATAABERTURA)));
                cAux.setSite(cursor.getString(cursor.getColumnIndex(SITE)));
                cAux.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                cAux.setCep(cursor.getString(cursor.getColumnIndex(CEP)));
                cAux.setEndereco(cursor.getString(cursor.getColumnIndex(ENDERECO)));
                cAux.setNumero(cursor.getString(cursor.getColumnIndex(NUMERO)));
                cAux.setBairro(cursor.getString(cursor.getColumnIndex(BAIRRO)));
                cAux.setCidade(cursor.getString(cursor.getColumnIndex(CIDADE)));
                cAux.setEstado(cursor.getString(cursor.getColumnIndex(ESTADO)));
                cAux.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
                cAux.setCelular(cursor.getString(cursor.getColumnIndex(CELULAR)));
            }

            cursor.close();

        } finally {
            closeDataBase();
        }
        return cAux;
    }

    @Override
    public ArrayList<HMAux> findListCliente() throws SQLException {
        openDataBase();
        //
        ArrayList<HMAux> clientes = new ArrayList<>();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select idcliente, nome from tb_cliente ";
            //
            cursor = db.rawQuery(sqlQuery, null);
            //
            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDCLIENTE, cursor.getString(cursor.getColumnIndex(IDCLIENTE)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                clientes.add(aux);
            }
            //
            cursor.close();
            //
        } finally {
            closeDataBase();
        }
        return clientes;
    }

    @Override
    public ArrayList<HMAux> findListCliente(HMAux filter, HMAux order) throws SQLException {
        openDataBase();
        //
        ArrayList<HMAux> clientes = new ArrayList<>();
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder sqlQuery = new StringBuilder();

            sqlQuery.append(" select idcliente, nome from tb_cliente where idcliente is not null ");
            String[] argumentos = new String[filter.size()];
            int i = 0;

            for (HMAux.Entry<String, String> entrada : filter.entrySet()) {
                sqlQuery.append(" and (" + entrada.getKey() + " like ? ) ");
                argumentos[i] = "%" + String.valueOf(entrada.getValue()) + "%";
                i++;
            }

            if (order.size() > 0) {
                sqlQuery.append(" order by ");

                i = 0;
                String aux = "";
                for (HMAux.Entry<String, String> entrada : order.entrySet()) {
                    i++;
                    aux = (i == order.size()) ? "" : ", ";
                    sqlQuery.append(entrada.getValue() + aux);
                }
            } else {
                sqlQuery.append(" order by nome ");
            }

            cursor = db.rawQuery(sqlQuery.toString(), argumentos);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDCLIENTE, cursor.getString(cursor.getColumnIndex(IDCLIENTE)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                clientes.add(aux);
            }

            cursor.close();

        } finally {
            closeDataBase();
        }

        return clientes;
    }

    @Override
    public long nextID() throws SQLException {
        long nextIdCliente = -1L;
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select ifnull(max(idcliente)+1,1) as idcliente from tb_cliente; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextIdCliente = cursor.getLong(cursor.getColumnIndex("userId"));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextIdCliente;
    }
}
