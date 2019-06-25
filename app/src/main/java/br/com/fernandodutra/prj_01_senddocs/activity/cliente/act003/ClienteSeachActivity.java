package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act003;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 15/03/2019
 * Time: 13:32
 * Prj_01_Protocolo
 */
public class ClienteSeachActivity extends AppCompatActivity implements ClienteSeachActivity_Contract.View {

    private Context context;

    private ClienteSeachActivity_Presenter presenter;

    private EditText et_cidade;
    private EditText et_nome;

    private Button bt_filtrar;

    private RadioGroup rg_ordenar;
    private RadioButton rb_nome;
    private RadioButton rb_cidade;

    private Toolbar toolbar;

    private HMAux filter;
    private HMAux order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientesearch_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        bt_filtrar.setOnClickListener(onClickListenerFiltrar());
    }

    private View.OnClickListener onClickListenerFiltrar() {
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
            case R.id.clientesearch_activity_rb_nome:
                ordernarPor = "nome";
                break;
            case R.id.clientesearch_activity_rb_cidade:
                ordernarPor = "cidade";
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
        String cidade = et_cidade.getText().toString().trim();

        HMAux hmAux = new HMAux();
        hmAux.put("nome", nome);
        hmAux.put("cidade", cidade);

        return hmAux;
    }

    private void iniciarVariaveis() {
        context = ClienteSeachActivity.this;
        //
        et_nome = findViewById(R.id.clientesearch_activity_et_nome);
        et_cidade = findViewById(R.id.clientesearch_activity_et_cidade);
        //
        bt_filtrar = findViewById(R.id.clientesearch_activity_bt_filtrar);
        //
        rg_ordenar = (RadioGroup) findViewById(R.id.clientesearch_activity_rg_ordenar);
        rb_nome = (RadioButton) findViewById(R.id.clientesearch_activity_rb_nome);
        rb_cidade = (RadioButton) findViewById(R.id.clientesearch_activity_rb_cidade);

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
