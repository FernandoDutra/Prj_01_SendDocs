package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act002;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.SimpleAdapter;

import java.util.HashMap;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.activity.usuario.act001.UsuarioActivity;
import br.com.fernandodutra.prj_01_senddocs.activity.usuario.act003.UsuarioSearchActivity;
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;


/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:23
 * Prj_01_SendDocs
 */
public class UsuarioListActivity extends AppCompatActivity implements UsuarioListActivity_Contract.View {


    private Context context;
    //
    private UsuarioDAOLiter usuarioDAOLiter;
    //
    private UsuarioListActivity_Contract.Presenter presenter;
    //
    private long idUsuario = -1l;
    private String mensagem;
    //
    private ListView lv_usuarios;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuariolist_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        lv_usuarios.setOnItemClickListener(onItemClickListener_idUsuario);
    }

    private void configuraToolbar() {
        toolbar = findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.cadastro_usuarios);
        }
    }

    private void iniciarVariaveis() {
        context = UsuarioListActivity.this;
        //
        lv_usuarios = (ListView) findViewById(R.id.usuariolist_activity_lv_usuarios);
        //
        configuraToolbar();
        //
        UsuarioDAOLiter usuarioDAOLiter = new UsuarioDAOLiter(context);
        //
        presenter = new UsuarioListActivity_Presenter(this, usuarioDAOLiter);
        //
        carregaLista();
    }

    private AdapterView.OnItemClickListener onItemClickListener_idUsuario = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HMAux item = (HMAux) parent.getItemAtPosition(position);
            //
            idUsuario = Long.parseLong(item.get(UsuarioDAOLiter.IDUSUARIO));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.usuariolist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.usuariolist_item_cadastrar:
                cadastrar();
                break;
            case R.id.usuariolist_item_editar:
                editar();
                break;
            case R.id.usuariolist_item_excluir:
                excluir();
                break;
            case R.id.usuariolist_item_pesquisar:
                pesquisar();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void formCall(long idUsuario) {
        Intent mIntent = new Intent(context, UsuarioActivity.class);
        mIntent.putExtra(UsuarioDAOLiter.IDUSUARIO, idUsuario);
        //
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public void pesquisar() {
        Intent intent = new Intent(context, UsuarioSearchActivity.class);
        //
        startActivityForResult(intent, Constants.FILTRO_PESQUISA_INTENT);
    }

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

            // Criar no Presenter o método de pegar a lista de Usuários via HMAux
            this.carregaLista(ToolBox.carregaHashMap(hashMapFilter), ToolBox.carregaHashMap(hashMapOrder));
        }
    }

    @Override
    public void cadastrar() {
        idUsuario = -1;
        formCall(idUsuario);
    }

    @Override
    public void editar() {
        if (idUsuario > -1l) {
            formCall(idUsuario);
        } else {
            mensagem = getString(R.string.editar_usuario);
            ToolBox.exibirMensagem(context, mensagem);
        }
    }

    @Override
    public void excluir() {
        if (idUsuario > -1l) {
            ToolBox.alertMessage(
                    context,
                    "Confirmacao de Exclusão",
                    "Deseja realmente Excluir este Usuário?",
                    R.mipmap.ic_launcher,
                    true,
                    "Sim",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int resultado = presenter.excluirUsuario(idUsuario);
                            switch (resultado) {
                                case 1: {
                                    mensagem = "Usuário excluído com sucesso!";
                                    ToolBox.exibirMensagem(context, mensagem);
                                    carregaLista();
                                    break;
                                }
                                case 2: {
                                    mensagem = "Este Usuário possui movimentações e não pode ser excluído!";
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
            mensagem = getString(R.string.editar_usuario);
            ToolBox.exibirMensagem(context, mensagem);
        }
    }

    @Override
    public void carregaLista() {
        String[] De = {UsuarioDAOLiter.NOME};
        int[] Para = {android.R.id.text1};
        //
        lv_usuarios.setAdapter(new SimpleAdapter(
                context,
                presenter.buscarUsuario(),
                android.R.layout.simple_list_item_1,
                De,
                Para
        ));
    }

    @Override
    public void carregaLista(HMAux filter, HMAux order) {
        String[] De = {UsuarioDAOLiter.NOME};
        int[] Para = {android.R.id.text1};
        //
        lv_usuarios.setAdapter(new SimpleAdapter(
                context,
                presenter.buscarUsuario(filter, order),
                android.R.layout.simple_list_item_1,
                De,
                Para
        ));
    }
}
