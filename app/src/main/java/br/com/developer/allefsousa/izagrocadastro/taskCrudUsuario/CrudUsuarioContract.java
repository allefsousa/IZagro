package br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

/**
 * Created by allef on 22/08/2018.
 */

public interface CrudUsuarioContract {

    interface view {

        void usuarioInvalidoVazio();

        void usuarioAdicionado();

        void usuarioAtualizado();

        void usuarioDeletado();

        void UsuarioAdicionado(Long id);

        void FalhaAoAdicionar();

        void Limpar();
    }

    interface presenter {

        void postUsuario(Usuario usuario);

        void putUsuario(Long id);

        void getUsuario(Long id);

        void getAllUsuario();

        void deleteUsuario(Long id);


    }
}
