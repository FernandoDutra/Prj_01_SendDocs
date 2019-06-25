package br.com.fernandodutra.prj_01_senddocs.activity.cliente.act001;

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
import br.com.fernandodutra.prj_01_senddocs.activity.cliente.act002.ClienteListActivity;
import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAOLiter;
import br.com.fernandodutra.prj_01_senddocs.model.cliente.Cliente;
import br.com.fernandodutra.prj_01_senddocs.utils.Mask;
import br.com.fernandodutra.prj_01_senddocs.utils.ToolBox;

public class ClienteActivity extends AppCompatActivity implements ClienteActivity_Contract.View {

    private Context context;

    private ClienteActivity_Contract.Presenter presenter;

    private Button bt_salvar;
    private Button bt_cancelar;

    private EditText et_idcliente;
    private EditText et_nome;
    private EditText et_cpfcnpj;
    private EditText et_rgie;
    private EditText et_dataAbertura;
    private EditText et_site;
    private EditText et_email;
    private EditText et_cep;
    private EditText et_endereco;
    private EditText et_numero;
    private EditText et_bairro;
    private EditText et_cidade;
    private EditText et_telefone;
    private EditText et_celular;

    private Spinner sp_estado;

    private Toolbar toolbar;

    private long idClienteAtual;
    private String mensagem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_activity);

        iniciarVariaveis();
        iniciarAcoes();
    }

    private void iniciarAcoes() {
        bt_salvar.setOnClickListener(onClickListenerSalvar());
        bt_cancelar.setOnClickListener(onClickListenerCancelar());

        et_telefone.addTextChangedListener(Mask.insert("(##)####-####", et_telefone));
        et_celular.addTextChangedListener(Mask.insert("(##)#####-####", et_celular));
    }

    private void iniciarVariaveis() {
        context = ClienteActivity.this;
        //
        idClienteAtual = recuperarParametro();
        //
        configuraToolbar();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        presenter = new ClienteActivity_Presenter(this, new ClienteDAOLiter(context));
        //
        bt_salvar = findViewById(R.id.activity_cliente_bt_salvar);
        bt_cancelar = findViewById(R.id.activity_cliente_bt_cancelar);
        //
        et_idcliente = findViewById(R.id.activity_cliente_et_idcliente);
        et_nome = findViewById(R.id.activity_cliente_et_nome);
        et_cpfcnpj = findViewById(R.id.activity_cliente_et_cpfcnpj);
        et_rgie = findViewById(R.id.activity_cliente_et_rgie);
        et_dataAbertura = findViewById(R.id.activity_cliente_et_dataabertura);
        et_site = findViewById(R.id.activity_cliente_et_site);
        et_email = findViewById(R.id.activity_cliente_et_email);
        et_cep = findViewById(R.id.activity_cliente_et_cep);
        et_endereco = findViewById(R.id.activity_cliente_et_end);
        et_numero = findViewById(R.id.activity_cliente_et_num);
        et_bairro = findViewById(R.id.activity_cliente_et_bairro);
        et_cidade = findViewById(R.id.activity_cliente_et_cidade);
        sp_estado = findViewById(R.id.activity_cliente_sp_estado);
        et_telefone = findViewById(R.id.activity_cliente_et_telefone);
        et_celular = findViewById(R.id.activity_cliente_et_celular);
        //
        et_idcliente.setEnabled(true);
        //
        if (idClienteAtual == -1L) {
            et_nome.requestFocus();
            bt_cancelar.setEnabled(false);
        } else {
            Cliente cliente = null;
            cliente = presenter.findClienteByID(idClienteAtual);
            //
            et_idcliente.setText(Long.toString(cliente.getIdcliente()));
            et_cpfcnpj.setText(cliente.getCpfcnpj());
            et_nome.setText(cliente.getNome());
            et_rgie.setText(cliente.getRgie());
            et_dataAbertura.setText(cliente.getStrDataabertura());
            et_site.setText(cliente.getSite());
            et_email.setText(cliente.getEmail());
            et_cep.setText(cliente.getCep());
            et_endereco.setText(cliente.getEndereco());
            et_numero.setText(cliente.getNumero());
            et_bairro.setText(cliente.getBairro());
            et_cidade.setText(cliente.getCidade());
            sp_estado.setSelection(ToolBox.getIndexByString(sp_estado, cliente.getEstado()) );
            //
            et_telefone.setText(cliente.getTelefone());
            et_celular.setText(cliente.getCelular());
            //
            et_nome.requestFocus();
        }
    }

    private View.OnClickListener onClickListenerCancelar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        };
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

    private void configuraToolbar() {
        toolbar = findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.cadastro_clientes);
        }
    }

    public void salvar() {
        Cliente cliente = new Cliente();
        //
        cliente.setNome(et_nome.getText().toString().trim());
        cliente.setCpfcnpj(et_cpfcnpj.getText().toString().trim());
        cliente.setRgie(et_rgie.getText().toString().trim());
        cliente.setStDataAbertura(et_dataAbertura.getText().toString().trim());
        cliente.setSite(et_site.getText().toString().trim());
        cliente.setEmail(et_email.getText().toString().trim());
        cliente.setCep(et_cep.getText().toString().trim());
        cliente.setEndereco(et_endereco.getText().toString().trim());
        cliente.setNumero(et_numero.getText().toString().trim());
        cliente.setBairro(et_bairro.getText().toString().trim());
        cliente.setCidade(et_cidade.getText().toString().trim());
        cliente.setEstado(sp_estado.getSelectedItem().toString());
        cliente.setTelefone(et_telefone.getText().toString().trim());
        cliente.setCelular(et_celular.getText().toString().trim());
        //
        if (idClienteAtual != -1L) {
            cliente.setIdcliente(idClienteAtual);
            //
            presenter.atualizar(cliente);
            //
            listCall();
        } else {
            idClienteAtual = presenter.nextID();
            cliente.setIdcliente(idClienteAtual);
            //
            presenter.salvar(cliente);
            //
            et_idcliente.setText(String.valueOf(cliente.getIdcliente()));
            bt_cancelar.setEnabled(true);
        }
    }

    public void cancelar() {
        confirmarSaida();
    }

    @Override
    public void onBackPressed() {
        confirmarSaida();
    }

    @Override
    public boolean validacao() {
        String nome = et_nome.getText().toString().trim();
        String cpfcnpj = et_cpfcnpj.getText().toString().trim();
        String rgie = et_rgie.getText().toString().trim();
        String dataAbertura = et_dataAbertura.getText().toString().trim();
        String cep = et_cep.getText().toString().trim();
        String endereco = et_endereco.getText().toString().trim();
        String numero = et_numero.getText().toString().trim();
        String bairro = et_bairro.getText().toString().trim();
        String cidade = et_cidade.getText().toString().trim();
        String estado = sp_estado.getSelectedItem().toString().trim();
        String telefone = et_telefone.getText().toString().trim();

        if (nome.isEmpty() || (nome.length() < 3)) {
            mensagem = getString(R.string.clienteactitivity_erro_nome_requerido);
            return false;
        }
        if (cpfcnpj.isEmpty()) {
            mensagem = getString(R.string.clienteactitivity_erro_nome_requerido);
            return false;
        } else if (ToolBox.validaCpfCnpj(cpfcnpj)) {
            mensagem = getString(R.string.clienteactitivity_erro_cpfcnpj_invalido);
            return false;
        }
        if (rgie.isEmpty() || (rgie.length() < 5)) {
            mensagem = getString(R.string.clienteactitivity_erro_rgie_requerido);
            return false;
        }
        if (cep.isEmpty() || (cep.length() < 5)) {
            mensagem = getString(R.string.clienteactitivity_erro_cep_requerido);
            return false;
        }
        if (endereco.isEmpty() || (endereco.length() < 5)) {
            mensagem = getString(R.string.clienteactitivity_erro_nome_requerido);
            return false;
        }
        if (numero.isEmpty()) {
            mensagem = getString(R.string.clienteactitivity_erro_numero_requerido);
            return false;
        }
        if (bairro.isEmpty() || (bairro.length() < 5)) {
            mensagem = getString(R.string.clienteactitivity_erro_bairro_requerido);
            return false;
        }
        if (cidade.isEmpty() || (cidade.length() < 5)) {
            mensagem = getString(R.string.clienteactitivity_erro_cidade_requerido);
            return false;
        }
        if (estado.isEmpty() || (estado.length() != 2)) {
            mensagem = getString(R.string.clienteactitivity_erro_estado_requerido);
            return false;
        }
        if (telefone.isEmpty() || (telefone.length() < 5)) {
            mensagem = getString(R.string.clienteactitivity_erro_telefone_requerido);
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
        Intent mIntent = new Intent(getBaseContext(), ClienteListActivity.class);
        startActivity(mIntent);
        //
        finish();
    }

    @Override
    public long recuperarParametro() {
        return getIntent().getLongExtra(ClienteDAOLiter.IDCLIENTE, 0);
    }
}
