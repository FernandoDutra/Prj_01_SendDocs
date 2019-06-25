package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act002;

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

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act001.TipoDocActivity;
import br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act003.TipoDocSeachActivity;
import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 11/04/2019
 * Time: 22:01
 * Prj_01_SendDocs
 */
public class TipoDocListActivity extends AppCompatActivity implements TipoDocListActivity_Contract.View {

    private Context context;
    //
    private TipoDocDAOLiter tipoDocDAOLiter;
    //
    private TipoDocListActivity_Contract.Presenter presenter;
    //
    private long idTipoDoc = -1l;
    private String mensagem;
    //
    private ListView lst_tipoDoc;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipodoclist_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        lst_tipoDoc.setOnItemClickListener(onItemClickListener_idTipoDoc);
    }

    private AdapterView.OnItemClickListener onItemClickListener_idTipoDoc = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HMAux item = (HMAux) parent.getItemAtPosition(position);
            //
            idTipoDoc = Long.parseLong(item.get(TipoDocDAOLiter.IDTIPODOC));
        }
    };

    private void iniciarVariaveis() {
        context = TipoDocListActivity.this;
        //
        lst_tipoDoc = (ListView) findViewById(R.id.tipodoclist_activity_lv_tipodoc);
        //
        configuraToolbar();
        //
        TipoDocDAOLiter tipoDocDAOLiter = new TipoDocDAOLiter(context);
        //
        presenter = new TipoDocListActivity_Presenter(this,tipoDocDAOLiter);
        //
        carregaLista();
    }

    private void carregaLista() {
        String[] De = {TipoDocDAOLiter.NOME};
        int[] Para = {android.R.id.text1};
        //
        lst_tipoDoc.setAdapter(new SimpleAdapter(
                context,
                presenter.buscarTipoDoc(),
                android.R.layout.simple_list_item_1,
                De,
                Para
        ));
    }

    private void carregaLista(HMAux filter, HMAux order) {
        String[] De = {TipoDocDAOLiter.NOME};
        int[] Para = {android.R.id.text1};
        //
        lst_tipoDoc.setAdapter(new SimpleAdapter(
                context,
                presenter.buscarTipoDoc(filter, order),
                android.R.layout.simple_list_item_1,
                De,
                Para
        ));
    }

    private void configuraToolbar() {
        toolbar = findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.cadastro_tipodoc);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tipodoclist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tipodoclist_item_cadastrar:
                cadastrar();
                break;
            case R.id.tipodoclist_item_editar:
                editar();
                break;
            case R.id.tipodoclist_item_excluir:
                excluir();
                break;
            case R.id.tipodoclist_item_pesquisar:
                pesquisar();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void pesquisar() {
        Intent mIntent = new Intent(context, TipoDocSeachActivity.class);
        //
        startActivityForResult(mIntent, Constants.FILTRO_PESQUISA_INTENT);
    }

    private void excluir() {
        if (idTipoDoc > -1l) {

            ToolBox.alertMessage(
                    context,
                    "Confirmacao de Exclusão",
                    "Deseja realmente Excluir este Tipo de Documento?",
                    R.mipmap.ic_launcher,
                    true,
                    "Sim",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int resultado = presenter.excluirTipoDoc(idTipoDoc);
                            switch (resultado) {
                                case 1: {
                                    mensagem = "Tipo de Documento excluído com sucesso!";
                                    ToolBox.exibirMensagem(context, mensagem);
                                    carregaLista();
                                    break;
                                }
                                case 2: {
                                    mensagem = "Este Tipo de Documento possui movimentações e não pode ser excluído!";
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
            mensagem = getString(R.string.editar_tipodoc);
            ToolBox.exibirMensagem(context, mensagem);
        }
    }

    private void editar() {
        if (idTipoDoc > -1l) {
            formCall(idTipoDoc);
        } else {
            mensagem = getString(R.string.editar_tipodoc);
            ToolBox.exibirMensagem(context, mensagem);
        }
    }

    private void cadastrar() {
        idTipoDoc = -1l;
        formCall(idTipoDoc);
    }

    @Override
    public void formCall(long idTipoDoc) {
        Intent mIntent = new Intent(context, TipoDocActivity.class);
        mIntent.putExtra(TipoDocDAOLiter.IDTIPODOC, idTipoDoc);
        //
        startActivity(mIntent);
        //
        finish();
    }
}
