package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act003;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 12:48
 * Prj_01_SendDocs
 */
public class TipoDocSeachActivity extends AppCompatActivity implements TipoDocSeachActivity_Contract.View {

    private Context context;

    private TipoDocSeachActivity_Presenter presenter;

    private EditText et_nome;
    private EditText et_observacoes;

    private Button bt_filtrar;

    private RadioGroup rg_ordenar;
    private RadioButton rb_nome;
    private RadioButton rb_observacoes;

    private Toolbar toolbar;

    private HMAux filter;
    private HMAux order;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipodocsearch_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        bt_filtrar.setOnClickListener(setOnClickListenerFiltrar());
    }

    private View.OnClickListener setOnClickListenerFiltrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = pegaFiltros();
                order = pegaOrdenacao();
                listCall();
            }
        };
    }

    private HMAux pegaOrdenacao() {
        String ordernarPor = "nome";

        switch (rg_ordenar.getCheckedRadioButtonId()) {
            case R.id.tipodocsearch_activity_rb_nome:
                ordernarPor = "nome";
                break;
            case R.id.tipodocsearch_activity_rb_observacoes:
                ordernarPor = "observacoes";
                break;
            default:
                ordernarPor = "nome";
                break;
        }

        HMAux hmAux = new HMAux();
        hmAux.put("nome", ordernarPor);

        return hmAux;
    }

    private HMAux pegaFiltros() {
        String nome = et_nome.getText().toString().trim();
        String observacoes = et_observacoes.getText().toString().trim();

        HMAux hmAux = new HMAux();
        hmAux.put("nome", nome);
        hmAux.put("observacoes", observacoes);

        return hmAux;
    }

    private void iniciarVariaveis() {
        context = TipoDocSeachActivity.this;
        //
        presenter = new TipoDocSeachActivity_Presenter(this, new TipoDocDAOLiter(context));
        //
        et_nome = findViewById(R.id.tipodocsearch_activity_et_nome);
        et_observacoes = findViewById(R.id.tipodocsearch_activity_et_observacoes);
        //
        bt_filtrar = findViewById(R.id.tipodocsearch_activity_bt_filtrar);
        //
        rg_ordenar = (RadioGroup) findViewById(R.id.tipodocsearch_activity_rg_ordenar);
        rb_nome = (RadioButton) findViewById(R.id.tipodocsearch_activity_rb_nome);
        rb_observacoes = (RadioButton) findViewById(R.id.tipodocsearch_activity_rb_observacoes);

        rb_nome.setChecked(true);
    }

    @Override
    public void listCall() {
        Intent mIntent = new Intent();
        mIntent.putExtra(Constants.FILTRO_PESQUISA_PARAM, (Serializable) filter);
        mIntent.putExtra(Constants.FILTRO_PESQUISA_ORDER, (Serializable) order);
        //
        setResult(RESULT_OK, mIntent);
        //
        finish();
    }
}
