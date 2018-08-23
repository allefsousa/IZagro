package br.com.developer.allefsousa.izagrocadastro.postUsuario;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

/**
 * Created by allef on 22/08/2018.
 */

public interface postUsuarioContract {

    interface view {

        void UsuarioAdicionado(Long id);

        void FalhaAoAdicionar();

        void Limpar();

        void nomeEmBranco();

        void sobrenomeEmBranco();

        void dataNascBranco();


        void emailEmBranco();
    }

    interface presenter {

        void postUsuario(Usuario usuario);
        void validaDadosUsuario(Usuario usuario);



    }
}
