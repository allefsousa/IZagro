package br.com.developer.allefsousa.izagrocadastro.taskCrudUsuario;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

/**
 * Created by allef on 22/08/2018.
 */

public class CrudUsuarioAdapter extends RecyclerView.Adapter<CrudUsuarioAdapter.ViewHolder> {
    private Context context;
    private List<Usuario> usuarioList;

    public CrudUsuarioAdapter(Context context, List<Usuario> usuarioList) {
        this.context = context;
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public CrudUsuarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CrudUsuarioAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
