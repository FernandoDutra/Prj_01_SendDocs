package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act002;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.activity.cliente.act001.ClienteActivity;
import br.com.fernandodutra.prj_01_senddocs.activity.cliente.act003.ClienteSeachActivity;
import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

public class ClienteListActivity extends AppCompatActivity implements ClienteListActivity_Contract.View {

    private Context context;
    //
    private ClienteDAOLiter clienteDAOLiter;
    //
    private ClienteListActivity_Contract.Presenter presenter;
    //
    private long idCliente = -1l;
    private String mensagem;
    //
    private ListView lst_clientes;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientelist_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.clientelist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clientelist_item_cadastrar:
                cadastrar();
                break;
            case R.id.clientelist_item_editar:
                editar();
                break;
            case R.id.clientelist_item_excluir:
                excluir();
                break;
            case R.id.clientelist_item_pesquisar:
                pesquisar();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void pesquisar() {
        Intent mIntent = new Intent(context, ClienteSeachActivity.class);
        //
        startActivityForResult(mIntent, Constants.FILTRO_PESQUISA_INTENT);
    }

    private void cadastrar() {
        idCliente = -1l;
        formCall(idCliente);
    }

    private void editar() {
        if (idCliente > -1l) {
            formCall(idCliente);
        } else {
            mensagem = getString(R.string.editar_cliente);
            ToolBox.exibirMensagem(context, mensagem);
        }
    }

    private void excluir() {
        if (idCliente > -1l) {

            ToolBox.alertMessage(
                    context,
                    "Confirmacao de Exclusão",
                    "Deseja realmente Excluir este Cliente?",
                    R.mipmap.ic_launcher,
                    true,
                    "Sim",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int resultado = presenter.excluirCliente(idCliente);
                            switch (resultado) {
                                case 1: {
                                    mensagem = "Cliente excluído com sucesso!";
                                    ToolBox.exibirMensagem(context, mensagem);
                                    carregaLista();
                                    break;
                                }
                                case 2: {
                                    mensagem = "Este Cliente possui movimentações e não pode ser excluído!";
                                    ToolBox.exibirMensagem(context, mensagem);
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    },
                    "Nao",
                    null
            );
        } else {
            mensagem = getString(R.string.editar_cliente);
            ToolBox.exibirMensagem(context, mensagem);
        }
    }

    private void iniciarAcoes() {
        lst_clientes.setOnItemClickListener(onItemClickListener_idcliente);
    }

    private void iniciarVariaveis() {
        context = ClienteListActivity.this;
        //
        lst_clientes = (ListView) findViewById(R.id.clientelist_activity_lv_clientes);
        //
        configuraToolbar();
        //
        ClienteDAOLiter clienteDAOLiter = new ClienteDAOLiter(context);
        //
        presenter = new ClienteListActivity_Presenter(this, clienteDAOLiter);
        //
        carregaLista();
    }

    private void carregaLista() {
        String[] De = {ClienteDAOLiter.NOME};
        int[] Para = {android.R.id.text1};
        //
        lst_clientes.setAdapter(new SimpleAdapter(
                context,
                presenter.buscarCliente(),
                android.R.layout.simple_list_item_1,
                De,
                Para
        ));
    }

    private void carregaLista(HMAux filter, HMAux order) {
        String[] De = {ClienteDAOLiter.NOME};
        int[] Para = {android.R.id.text1};
        //
        lst_clientes.setAdapter(new SimpleAdapter(
                context,
                presenter.buscarCliente(filter, order),
                android.R.layout.simple_list_item_1,
                De,
                Para
        ));
    }

    private void configuraToolbar() {
        toolbar = findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.cadastro_clientes);
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener_idcliente = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HMAux item = (HMAux) parent.getItemAtPosition(position);
            //
            idCliente = Long.parseLong(item.get(ClienteDAOLiter.IDCLIENTE));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case Constants.FILTRO_PESQUISA_INTENT:
                filtrarPesquisa(resultCode, data);
                break;
            default:
                // Log
                break;
        }
    }

    private void filtrarPesquisa(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            HashMap<String, String> hashMapFilter = (HashMap<String, String>) data.getSerializableExtra(Constants.FILTRO_PESQUISA_PARAM);
            HashMap<String, String> hashMapOrder = (HashMap<String, String>) data.getSerializableExtra(Constants.FILTRO_PESQUISA_ORDER);

            // Criar no Presenter o método de pegar a lista de Clientes via HMAux
            this.carregaLista(ToolBox.carregaHashMap(hashMapFilter), ToolBox.carregaHashMap(hashMapOrder));
        }
    }

    @Override
    public void formCall(long idcliente) {
        Intent mIntent = new Intent(context, ClienteActivity.class);
        mIntent.putExtra(ClienteDAOLiter.IDCLIENTE, idcliente);
        //
        startActivity(mIntent);
        //
        finish();
    }

}
