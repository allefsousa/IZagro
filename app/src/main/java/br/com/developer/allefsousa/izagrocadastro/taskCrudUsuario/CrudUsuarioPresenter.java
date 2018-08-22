package br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario;

import android.content.Context;
import android.text.TextUtils;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.data.source.local.DatabaseHelper;

/**
 * Created by allef on 22/08/2018.
 */

public class CrudUsuarioPresenter implements CrudUsuarioContract.presenter {

    private DatabaseHelper db;
    private CrudUsuarioContract.view Mview;
    private Context context;

    public CrudUsuarioPresenter(CrudUsuarioContract.view mview, Context context) {
        Mview = mview;
        this.context = context;
        db = new DatabaseHelper(context);
    }


    @Override
    public void postUsuario(Usuario usuario) {


        Long id = db.postUsuario(usuario);
        if (id != 0) {
            Mview.UsuarioAdicionado(id);
            Mview.Limpar();
        } else {
            Mview.FalhaAoAdicionar();
        }


    }

    @Override
    public void validaDadosUsuario(Usuario usuario) {

        if (TextUtils.isEmpty(usuario.getNome())) {
            Mview.nomeEmBranco();

        }
        if (TextUtils.isEmpty(usuario.getSobrenome())){
            Mview.sobrenomeEmBranco();
        }
        if (TextUtils.isEmpty(usuario.getDataNasc())){
            Mview.dataNascBranco();

        }
        if (TextUtils.isEmpty(usuario.getEmail())){
            Mview.emailEmBranco();
        }
        if (!TextUtils.isEmpty(usuario.getNome()) && !TextUtils.isEmpty(usuario.getSobrenome())&& !TextUtils.isEmpty(usuario.getDataNasc()) && !TextUtils.isEmpty(usuario.getEmail())){
            postUsuario(usuario);
        }


    }

}
