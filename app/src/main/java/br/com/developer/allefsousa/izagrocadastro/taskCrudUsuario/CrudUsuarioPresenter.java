package br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.data.source.local.DatabaseHelper;

/**
 * Created by allef on 22/08/2018.
 */

public class CrudUsuarioPresenter implements CrudUsuarioContract.presenter {

    private CrudUsuarioContract.view Mview;
    private Context context;
    DatabaseHelper db;

    public CrudUsuarioPresenter(CrudUsuarioContract.view mview, Context context) {
        Mview = mview;
        this.context = context;
        db = new DatabaseHelper(context);
    }



    @Override
    public void postUsuario(Usuario usuario) {
        Long id = db.postUsuario(usuario);
        if (id != 0){
            Mview.UsuarioAdicionado();
        }else {
            Mview.FalhaAoAdicionar();
        }


    }

    @Override
    public void putUsuario(Long id) {

    }

    @Override
    public void getUsuario(Long id) {

    }

    @Override
    public void getAllUsuario() {

    }

    @Override
    public void deleteUsuario(Long id) {

    }
}
