package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act001;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act002.TipoDocListActivity;
import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 10/04/2019
 * Time: 21:08
 * Prj_01_SendDocs
 */
public class TipoDocActivity extends AppCompatActivity implements TipoDocActivity_Contract.View {

    private Context context;

    private TipoDocActivity_Contract.Presenter presenter;

    private EditText et_idTipoDoc;
    private EditText et_nome;
    private EditText et_observacoes;

    private Button bt_salvar;
    private Button bt_cancelar;

    private long idTipoDocAtual;
    private String mensagem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipodoc_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        bt_salvar.setOnClickListener(onClickListenerSalvar());
        bt_cancelar.setOnClickListener(onClickListenerCancelar());
    }

    private View.OnClickListener onClickListenerSalvar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validacao()) {
                    salvar();
                } else {
                    ToolBox.exibirMensagem(context, mensagem);
                }
            }
        };
    }

    private void cancelar() {
        confirmarSaida();
    }

    private View.OnClickListener onClickListenerCancelar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        };
    }

    public boolean validacao() {
        String nome = et_nome.getText().toString().trim();

        if (nome.isEmpty() || (nome.length() < 3)) {
            mensagem = getString(R.string.tipodocsearchactivity_nome_requerido);
            return false;
        }

        return true;
    }

    @Override
    public void confirmarSaida() {
        ToolBox.alertMessage(
                context,
                "Confirmacao de Saida",
                "Deseja realmente Sair?",
                R.mipmap.ic_launcher,
                true,
                "Sim",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listCall();
                    }
                },
                "Nao",
                null
        );
    }

    @Override
    public void listCall() {
        Intent mIntent = new Intent(getBaseContext(), TipoDocListActivity.class);
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public long recuperarParametro() {
        return getIntent().getLongExtra(TipoDocDAOLiter.IDTIPODOC, 0);
    }

    private void salvar() {
        TipoDoc tipoDoc = new TipoDoc();
        //
        tipoDoc.setNome(et_nome.getText().toString().trim());
        tipoDoc.setObservacao(et_observacoes.getText().toString().trim());

        if (idTipoDocAtual != -1L) {
            tipoDoc.setIdtipodoc(idTipoDocAtual);
            //
            presenter.atualizar(tipoDoc);
            //
            listCall();
        } else {
            idTipoDocAtual = presenter.nextID();
            tipoDoc.setIdtipodoc(idTipoDocAtual);
            //
            presenter.salvar(tipoDoc);
            //
            et_idTipoDoc.setText(String.valueOf(tipoDoc.getIdtipodoc()));
            bt_cancelar.setEnabled(true);
        }
    }

    private void iniciarVariaveis() {
        context = TipoDocActivity.this;
        //
        idTipoDocAtual = recuperarParametro();
        //
        presenter = new TipoDocActivity_Presenter(this, new TipoDocDAOLiter(context));
        //
        et_idTipoDoc = findViewById(R.id.activity_tipodoc_et_idtipodoc);
        et_nome = findViewById(R.id.activity_tipodoc_et_nome);
        et_observacoes = findViewById(R.id.activity_tipodoc_et_observacoes);
        //
        bt_salvar = findViewById(R.id.activity_tipodoc_bt_salvar);
        bt_cancelar = findViewById(R.id.activity_tipodoc_bt_cancelar);
        //
        et_idTipoDoc.setEnabled(true);
        //
        if (idTipoDocAtual == -1L) {
            et_nome.requestFocus();
            bt_cancelar.setEnabled(false);
        } else {
            TipoDoc tipoDoc = null;
            tipoDoc = presenter.findTipoDocByID(idTipoDocAtual);
            //
            et_idTipoDoc.setText(Long.toString(tipoDoc.getIdtipodoc()));
            et_nome.setText(tipoDoc.getNome().toString());
            et_observacoes.setText(tipoDoc.getObservacao());
            //
            et_nome.requestFocus();
        }
    }


}
