package br.com.fernandodutra.prj_01_senddocs.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.model.departamento.Departamento;
import br.com.fernandodutra.prj_01_senddocs.dao.departamento.DepartamentoDAO;
import br.com.fernandodutra.prj_01_senddocs.model.protocolo.Protocolo;
import br.com.fernandodutra.prj_01_senddocs.dao.protocolo.ProtocoloDAO;
import br.com.fernandodutra.prj_01_senddocs.model.protocoloitens.ProtocoloItens;
import br.com.fernandodutra.prj_01_senddocs.dao.protocoloitens.ProtocoloItensDAO;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;
import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAO;
import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAO;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

    public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        testClienteDAO(context);
        testDepartamentoDAO(context);
        testTipoDocDAO(context);
        testUsuarioDAO(context);
        testProtocolo(context);
        testProtocoloItens(context);
    }

    private void testProtocoloItens(Context context) {
        ProtocoloItensDAO protocoloItensDAO = new ProtocoloItensDAO(context);
        try {
            long nextId = protocoloItensDAO.nextID();

            ProtocoloItens protocoloItens = new ProtocoloItens();
            protocoloItens.setIdprotocolo(nextId);
            protocoloItens.setIdprotocoloitens(1);
            protocoloItens.setIdtipodoc(1);
            protocoloItens.setNomeprotocolo("Protocolo");
            protocoloItens.setObservacao("Observações de Teste");
            //
            protocoloItensDAO.insertProtocoloItens(protocoloItens);
            //
            protocoloItensDAO.updateProtocoloItens(protocoloItens);
            //
            protocoloItensDAO.findProtocoloItensByID(nextId);
            //
            ArrayList<HMAux> listTipoDoc = protocoloItensDAO.findListProtocoloItens(nextId);
            //
            protocoloItensDAO.deleteProtocoloItens(protocoloItens);
            //
            Toast.makeText(context, "Métodos tb_cliente executados com sucesso!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Métodos tb_cliente com problemas!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void testProtocolo(Context context) {
        ProtocoloDAO protocoloDAO = new ProtocoloDAO(context);
        try {
            long nextId = protocoloDAO.nextID();

            Protocolo protocolo = new Protocolo();
            protocolo.setIdprotocolo(nextId);
            protocolo.setStrDataemissao("10/02/2019");
            protocolo.setStrDataprevisao("10/02/2019");
            protocolo.setTotalprot(10);
            protocolo.setCodorigem(1);
            protocolo.setNomeorigem("Fernando");
            protocolo.setEndorigem("Itu//SP");
            protocolo.setEmailorigem("fernandoalvesdutra@hotmail.com");
            protocolo.setNomedestino("Impacta");
            protocolo.setEnddestino("São Paulo/SP");
            protocolo.setEmaildestino("impacta@impacta.com.br");
            protocolo.setCodentregador(1);
            protocolo.setNomeentregador("João da Silva");
            protocolo.setEntregue("S");
            protocolo.setStrDataentrega("10/02/2019");
            protocolo.setRgrecebedor("40.714.846-2");
            //
            protocoloDAO.insertProtocolo(protocolo);
            //
            protocoloDAO.updateProtocolo(protocolo);
            //
            protocoloDAO.findProtocoloById(nextId);
            //
            ArrayList<HMAux> listTipoDoc = protocoloDAO.findListProtocolo();
            //
            protocoloDAO.deleteProtocolo(protocolo);
            //
            Toast.makeText(context, "Métodos tb_cliente executados com sucesso!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Métodos tb_cliente com problemas!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void testUsuarioDAO(Context context) {
        UsuarioDAOLiter usuarioDAO = new UsuarioDAOLiter(context);
        try {
            long nextId = usuarioDAO.nextID();

            Usuario usuario = new Usuario();
            usuario.setIdusuario(nextId);
            usuario.setNome("Fernando");
            usuario.setSenha("123");
            usuario.setNivelacesso(1);
            usuario.setEmail("fernandoalvesdutra@hotmail.com");
            //
            usuarioDAO.insertUsuario(usuario);
            //
            usuarioDAO.updateUsuario(usuario);
            //
            usuarioDAO.findUsuarioById(nextId);
            //
            ArrayList<HMAux> listTipoDoc = usuarioDAO.findListUsuario();
            //
            usuarioDAO.deleteUsuario(nextId);
            //
            Toast.makeText(context, "Métodos tb_cliente executados com sucesso!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Métodos tb_cliente com problemas!!!", Toast.LENGTH_SHORT).show();
        }

    }

    private void testTipoDocDAO(Context context) {
//        TipoDocDAO tipoDocDAO = new TipoDocDAO(context);
//        try {
//            long nextId = tipoDocDAO.nextID();
//
//            TipoDoc tipoDoc = new TipoDoc();
//            tipoDoc.setIdtipodoc(nextId);
//            tipoDoc.setNome("Desenvolvimento");
//            tipoDoc.setObservacao("Observações de Teste");
//            //
//            tipoDocDAO.insertTipoDoc(tipoDoc);
//            //
//            tipoDocDAO.updateTipoDoc(tipoDoc);
//            //
//            tipoDocDAO.findTipoDocByID(nextId);
//            //
//            ArrayList<HMAux> listTipoDoc = tipoDocDAO.findListTipoDoc();
//            //
//            tipoDocDAO.deleteTipoDoc(tipoDoc);
//            //
//            Toast.makeText(context, "Métodos tb_cliente executados com sucesso!!!", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Métodos tb_cliente com problemas!!!", Toast.LENGTH_SHORT).show();
//        }
    }

    private void testDepartamentoDAO(Context context) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO(context);
        try {
            long nextId = departamentoDAO.nextID();

            Departamento departamento = new Departamento();
            departamento.setIdDepartamento(nextId);
            departamento.setNome("Desenvolvimento");
            departamento.setResponsavel("Fernando");
            departamento.setTelefone("11 4023-5459");
            departamento.setCelular("11 99700-9927");
            departamento.setObservacao("Observações de Teste");
            //
            departamentoDAO.insertDepartamento(departamento);
            //
            departamentoDAO.updateDepartamento(departamento);
            //
            departamentoDAO.findDepartamentoByID(nextId);
            //
            ArrayList<HMAux> listDepartamento = departamentoDAO.findListDepartamento();
            //
            departamentoDAO.deleteDepartamento(departamento);
            //
            Toast.makeText(context, "Métodos tb_cliente executados com sucesso!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Métodos tb_cliente com problemas!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void testClienteDAO(Context context) {
        ClienteDAOLiter clienteDAOLiter = new ClienteDAOLiter(context);
        try {
            long nextId = clienteDAOLiter.nextID();

            Cliente cliente = new Cliente();
            cliente.setIdcliente(nextId);
            cliente.setNome("Fernando Robson Alves Dutra");
            cliente.setCpfcnpj("367.464.058-93");
            cliente.setRgie("40.714.846-2");
            cliente.setStDataAbertura("01/28/2017");
            cliente.setSite("www.fernandodutra.com.br");
            cliente.setEmail("fernando@dutra.com.br");
            cliente.setCep("13311-460");
            cliente.setEndereco("Rua Joaquim Dias");
            cliente.setNumero("79");
            cliente.setBairro("Mayard");
            cliente.setCidade("Itu");
            cliente.setEstado("SP");
            cliente.setTelefone("11 4023-5459");
            cliente.setCelular("11 99700-9927");

            clienteDAOLiter.insertCliente(cliente);
            //
            clienteDAOLiter.updateCliente(cliente);
            //
            clienteDAOLiter.findClienteByID(nextId);
            //
            ArrayList<HMAux> listCliente = clienteDAOLiter.findListCliente();
            //
            clienteDAOLiter.deleteCliente(nextId);
            //
            Toast.makeText(context, "Métodos tb_cliente executados com sucesso!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Métodos tb_cliente com problemas!!!", Toast.LENGTH_SHORT).show();
        }
    }

}
