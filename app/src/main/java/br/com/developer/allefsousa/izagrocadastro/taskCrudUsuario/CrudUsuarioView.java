package br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.utils.Mask;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CrudUsuarioView extends AppCompatActivity implements CrudUsuarioContract.view {
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

    Usuario usuario;

    private CrudUsuarioContract.presenter Mpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Mpresenter = new CrudUsuarioPresenter(this,this);
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
            Mpresenter.postUsuario(usuario);
        });


    }

    @Override
    public void usuarioInvalidoVazio() {

    }

    @Override
    public void usuarioAdicionado() {

    }

    @Override
    public void usuarioAtualizado() {

    }

    @Override
    public void usuarioDeletado() {

    }

    @Override
    public void UsuarioAdicionado(Long id) {
        Toast.makeText(this, "Usuario Adicionado !!!! " +id, Toast.LENGTH_LONG).show();
    }

    @Override
    public void FalhaAoAdicionar() {
        Toast.makeText(this, "Falhooooou", Toast.LENGTH_LONG).show();
    }

    @Override
    public void Limpar() {
        edtNome.getText().clear();
        edtSobrenome.getText().clear();
        edtDataNasc.getText().clear();
        edtEmail.getText().clear();
    }
}
