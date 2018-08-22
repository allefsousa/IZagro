package br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

public class MainActivity extends AppCompatActivity implements CrudUsuarioContract.view {

    private CrudUsuarioContract.presenter Mpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mpresenter = new CrudUsuarioPresenter(this,this);
        Usuario usuario = new Usuario("Allef","sousa","Allefsousa_1@hotmail.com","26011994");
        Mpresenter.postUsuario(usuario);

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
    public void UsuarioAdicionado() {
        Toast.makeText(this, "Usuario Adicionado !!!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void FalhaAoAdicionar() {
        Toast.makeText(this, "Falhooooou", Toast.LENGTH_LONG).show();
    }
}
