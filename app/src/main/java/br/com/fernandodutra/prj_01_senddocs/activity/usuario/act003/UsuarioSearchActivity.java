package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act003;

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
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAO;
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:23
 * Prj_01_SendDocs
 */
public class UsuarioSearchActivity extends AppCompatActivity implements UsuarioSearchActivity_Contract.View {

    private Context context;

    private UsuarioSearchActivity_Presenter presenter;

    private EditText et_nome;
    private EditText et_email;

    private Button bt_filtrar;

    private RadioGroup rg_ordenar;
    private RadioButton rb_nome;
    private RadioButton rb_email;

    private Toolbar toolbar;

    private HMAux filter;
    private HMAux order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuariosearch_activity);

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
            case R.id.usuariosearch_activity_rb_nome:
                ordernarPor = UsuarioDAOLiter.NOME;
                break;
            case R.id.usuariosearch_activity_rb_email:
                ordernarPor = UsuarioDAOLiter.EMAIL;
                break;
            default:
                ordernarPor = UsuarioDAOLiter.NOME;
                break;
        }

        HMAux hmAux = new HMAux();
        hmAux.put("nome", ordernarPor);

        return hmAux;
    }

    private HMAux pegaFiltros() {
        String nome = et_nome.getText().toString().trim();
        String email = et_email.getText().toString().trim();

        HMAux hmAux = new HMAux();
        hmAux.put("nome", nome);
        hmAux.put("email", email);

        return hmAux;
    }

    private void iniciarVariaveis() {
        context = UsuarioSearchActivity.this;
        //
        et_nome = findViewById(R.id.usuariosearch_activity_et_nome);
        et_email = findViewById(R.id.usuariosearch_activity_et_email);
        //
        bt_filtrar = findViewById(R.id.usuariosearch_activity_bt_filtrar);
        //
        rg_ordenar = (RadioGroup) findViewById(R.id.usuariosearch_activity_rg_ordenar);
        rb_nome = (RadioButton) findViewById(R.id.usuariosearch_activity_rb_nome);
        rb_email = (RadioButton) findViewById(R.id.usuariosearch_activity_rb_email);

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
