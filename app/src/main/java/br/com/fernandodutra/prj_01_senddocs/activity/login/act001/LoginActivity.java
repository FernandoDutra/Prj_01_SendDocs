package br.com.fernandodutra.prj_01_senddocs.activity.login.act001;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.dao.login.LoginDAO;
import br.com.fernandodutra.prj_01_senddocs.dao.login.LoginDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAOLiter;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/04/2019
 * Time: 16:25
 * Prj_01_SendDocs
 */
public class  LoginActivity extends AppCompatActivity implements LoginActivity_Contract.View {


    private Context context;
    //
    private LoginActivity_Contract.Presenter presenter;
    //
    private EditText et_login;
    private EditText et_senha;
    //
    private Button bt_cancelar;
    private Button bt_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarVariaveis() {
        context = LoginActivity.this;
        //
        presenter = new LoginActivity_Presenter(this, new LoginDAOLiter(context));
        //
        et_login = (EditText) findViewById(R.id.activity_login_et_login);
        et_senha = (EditText) findViewById(R.id.activity_login_et_senha);
        //
        bt_cancelar = (Button) findViewById(R.id.activity_login_bt_cancelar);
        bt_login = (Button) findViewById(R.id.activity_login_bt_login);
    }

    private void iniciarAcoes() {
        bt_cancelar.setOnClickListener(onClickListenerCancelar());
        bt_login.setOnClickListener(onClickListenerLogin());
    }

    private View.OnClickListener onClickListenerLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.processarLogin(
                        et_login.getText().toString(),
                        et_senha.getText().toString()
                );
            }
        };
    }

    private View.OnClickListener onClickListenerCancelar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.processarLimpeza();
            }
        };
    }


    @Override
    public void exibirMensagem(int resourceTxT) {
        Toast.makeText(context, resourceTxT, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listCall(Bundle mBundle) {
//        Intent mIntent = new Intent(context, MenuActivity.class);
//        mIntent.putExtras(mBundle);
//        //
//        startActivity(mIntent);
//        //
//        finish();
    }

    @Override
    public void limparFormulario() {
        et_login.setText("");
        et_senha.setText("");
        //
        et_login.requestFocus();
    }
}
