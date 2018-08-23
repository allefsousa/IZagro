package br.com.developer.allefsousa.izagrocadastro.operacaoUsuario;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.data.source.local.DatabaseHelper;

/**
 * Created by allef on 22/08/2018.
 *
 * Classe reposnsavel por pensar e controlar todas as ações do View
 * neste caso Os presenters ou presentations seguem sendo a parte que contem as regras de negocio
 *
 */

public class opUsuarioPresenter implements opUsuarioContract.presenter {

    private DatabaseHelper helper ;
    private opUsuarioContract.view Mview;
    private Context context;
    private List<Usuario> usuarioList = new ArrayList<>();

    public opUsuarioPresenter(opUsuarioContract.view mview, Context context) {
        Mview = mview;
        this.context = context;
        helper = new DatabaseHelper(context);
    }

    @Override
    public void BuscarClientes() {
        usuarioList.clear();
        usuarioList.addAll(helper.getAllUsuarios());
       Mview.ConfigRecyclerView(usuarioList);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        helper.deleteUsuario(usuario);
    }

    @Override
    public void putUsuario(Usuario usuario1) {
        helper.putUsuario(usuario1);
    }

    @Override
    public void Longclick(int position) {
        Usuario usuario = usuarioList.get(position);
        Mview.showOperacoesDialog(usuario,position);
    }
}
