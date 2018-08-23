package br.com.developer.allefsousa.izagrocadastro.data.source.local;

import java.util.List;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

/**
 * Created by allef on 23/08/2018.
 *
 * Operaçõs que seram feitas no banco de dados
 */

public interface OperacoesInterface {

    long postUsuario(Usuario User);

    Usuario getUsuario(long id);


    List<Usuario> getAllUsuarios();


    int putUsuario(Usuario user);

    void deleteUsuario(Usuario user);

}
