package br.com.developer.allefsousa.izagrocadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario.CrudUsuarioView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExibeUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.fabAdd)
    FloatingActionButton fabAddUsuario;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_usuario);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        fabAddUsuario.setOnClickListener(View -> startActivity(new Intent(ExibeUsuarioActivity.this, CrudUsuarioView.class)));


    }

}
