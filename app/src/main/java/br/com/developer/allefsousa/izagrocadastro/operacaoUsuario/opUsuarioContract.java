package br.com.developer.allefsousa.izagrocadastro.operacaoUsuario;

import java.util.List;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

/**
 * Created by allef on 22/08/2018.
 *
 * Metodos que as classes View e Presenter Devem ter
 */

public interface opUsuarioContract {
    interface  view {

        void ConfigRecyclerView(List<Usuario> usuarioList);

        void showOperacoesDialog(Usuario usuario, int position);
    }
    interface presenter{

        void BuscarClientes();

        void deleteUsuario(Usuario usuario);

        void putUsuario(Usuario usuario1);


        void Longclick(int position);
    }
}
