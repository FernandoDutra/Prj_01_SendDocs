package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act001;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.activity.usuario.act002.UsuarioListActivity;
import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:23
 * Prj_01_SendDocs
 */
public class UsuarioActivity extends AppCompatActivity implements UsuarioActivity_Contract.View {

    private Context context;
    //
    private Button bt_salvar;
    private Button bt_cancelar;
    //
    private EditText et_idUsuario;
    private EditText et_nome;
    private EditText et_email;
    private EditText et_senha;
    private EditText et_senhaCofirmar;
    private Spinner sp_nivelAcesso;
    //
    private UsuarioDAOLiter usuarioDAOLiter;
    //
    private UsuarioActivity_Presenter presenter;
    //
    private Toolbar toolbar;
    //
    private long idUsuarioAtual;
    private String mensagem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        bt_salvar.setOnClickListener(setOnClickListenerSalvar());
        bt_cancelar.setOnClickListener(setOnClickListenerCancelar());
    }

    private View.OnClickListener setOnClickListenerCancelar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        };
    }

    private View.OnClickListener setOnClickListenerSalvar() {
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

    private void salvar() {
        Usuario usuario = new Usuario();
        //
        usuario.setNome(et_nome.getText().toString().trim());
        usuario.setEmail(et_email.getText().toString().trim());
        usuario.setSenha(et_senha.getText().toString().trim());
        usuario.setNivelacesso(sp_nivelAcesso.getSelectedItemPosition());
        //
        if (idUsuarioAtual != -1L) {
            usuario.setIdusuario(idUsuarioAtual);
            //
            presenter.atualizar(usuario);
            //
            listCall();
        } else {
            idUsuarioAtual = presenter.nextID();
            //
            usuario.setIdusuario(idUsuarioAtual);
            //
            presenter.salvar(usuario);
            //
            et_idUsuario.setText(Long.toString(idUsuarioAtual));
            bt_cancelar.setEnabled(true);
        }
    }

    private void iniciarVariaveis() {
        context = UsuarioActivity.this;
        //
        idUsuarioAtual = recuperarParametro();
        //
        configuraToolbar();
        //
        presenter = new UsuarioActivity_Presenter(this, new UsuarioDAOLiter(context));
        //
        bt_salvar = findViewById(R.id.activity_usuario_bt_salvar);
        bt_cancelar = findViewById(R.id.activity_usuario_bt_cancelar);
        //
        et_idUsuario = findViewById(R.id.activity_usuario_et_idusuario);
        et_nome = findViewById(R.id.activity_usuario_et_nome);
        et_email = findViewById(R.id.activity_usuario_et_email);
        et_senha = findViewById(R.id.activity_usuario_et_senha);
        et_senhaCofirmar = findViewById(R.id.activity_usuario_et_senhaconfirmar);
        //
        sp_nivelAcesso = findViewById(R.id.activity_usuario_sp_nivelacesso);
        //
        if (idUsuarioAtual == -1L) {
            et_nome.requestFocus();
            bt_cancelar.setEnabled(false);
        } else {
            Usuario usuario = null;
            //
            usuario = presenter.findUsuarioByID(idUsuarioAtual);
            //
            et_idUsuario.setText(Long.toString(usuario.getIdusuario()));
            et_nome.setText(usuario.getNome().trim());
            et_email.setText(usuario.getEmail().trim());
            et_senha.setText(usuario.getSenha().trim());
            et_senhaCofirmar.setText(usuario.getSenha().trim());
            //
            sp_nivelAcesso.setSelection((int) usuario.getNivelacesso());
            //
            et_nome.requestFocus();
        }
    }

    private void configuraToolbar() {
        toolbar = findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.cadastro_usuarios);
        }
    }

    @Override
    public boolean validacao() {
        String nome = et_nome.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String senha = et_senha.getText().toString().trim();
        String senhaConfirmar = et_senhaCofirmar.getText().toString().trim();

        if (nome.isEmpty() || (nome.length() < 3)) {
            mensagem = getString(R.string.usuarioactitivity_erro_nome_requerido);
            return false;
        }
        if (email.isEmpty() || (email.length() < 3)) {
            mensagem = getString(R.string.usuarioactitivity_erro_email_requerido);
            return false;
        }
        if (!senha.equals(senhaConfirmar)) {
            mensagem = getString(R.string.usuarioactitivity_erro_senha_diferente);
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
        Intent mIntent = new Intent(getBaseContext(), UsuarioListActivity.class);
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public long recuperarParametro() {
        return getIntent().getLongExtra(UsuarioDAOLiter.IDUSUARIO, 0);
    }
}
