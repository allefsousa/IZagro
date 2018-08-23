package br.com.developer.allefsousa.izagrocadastro.postUsuario;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.utils.Mask;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class postUsuarioView extends AppCompatActivity implements postUsuarioContract.view {
    // Button
    @BindView(R.id.butAdicionar)
    Button btnAdicionar;

    // TextInputLayout
    @BindView(R.id.textinpNome)
    TextInputLayout layoutNome;
    @BindView(R.id.textinpSobrenome)
    TextInputLayout layoutSobrenome;
    @BindView(R.id.textinpEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.textinpDataNasc)
    TextInputLayout layoutDatanasc;

    // TextInputEditext
    @BindView(R.id.editNome)
    EditText edtNome;
    @BindView(R.id.editSobrenome)
    EditText edtSobrenome;
    @BindView(R.id.editEmail)
    EditText edtEmail;
    @BindView(R.id.editDatanasc)
    EditText edtDataNasc;

    @BindString(R.string.app_name)
    String appName;

    private Usuario usuario;

    private postUsuarioContract.presenter Mpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postusuario);
        getSupportActionBar().setTitle("Adicionar Cliente");
        ButterKnife.bind(this);
        Mpresenter = new postUsuarioPresenter(this,this);
        usuario = new Usuario();
        edtDataNasc.addTextChangedListener(Mask.insert("##/##/####", edtDataNasc));

        /**
         * Populando objeto para ser persistido posteriormente...
         */
        btnAdicionar.setOnClickListener(View ->{
            usuario.setNome(edtNome.getText().toString());
            usuario.setSobrenome(edtSobrenome.getText().toString());
            usuario.setEmail(edtEmail.getText().toString());
            usuario.setDataNasc(edtDataNasc.getText().toString());

            // enviando objeto para o presenter
            Mpresenter.validaDadosUsuario(usuario);
        });


    }


    @Override
    public void UsuarioAdicionado(Long id) {

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(appName)
                .setContentText("Cadastro Efetuado !")
                .show();
    }

    @Override
    public void FalhaAoAdicionar() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(appName)
                .setContentText("Falha ao Efetuar Cadastro !")
                .show();
    }

    @Override
    public void Limpar() {
        edtNome.getText().clear();
        edtSobrenome.getText().clear();
        edtDataNasc.getText().clear();
        edtEmail.getText().clear();
        layoutEmail.setError("");
        layoutDatanasc.setError("");
        layoutSobrenome.setError("");
        layoutNome.setError("");
    }

    @Override
    public void nomeEmBranco() {
        layoutNome.setError("Nome em Branco !!");
    }

    @Override
    public void sobrenomeEmBranco() {
        layoutSobrenome.setError("Sobrenome em Branco !!");
    }

    @Override
    public void dataNascBranco() {
        layoutDatanasc.setError("Data de Nascimento em Branco !!");
    }


    @Override
    public void emailEmBranco() {
        layoutEmail.setError("Email em Branco !!");
    }
}
