package br.com.developer.allefsousa.izagrocadastro.operacaoUsuario.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.data.source.local.DatabaseHelper;

/**
 * Created by allef on 22/08/2018.
 * Classe adaptadore e responsavel por exibir os dados na tela
 */

public class opUsuariosAdapter extends RecyclerView.Adapter<opUsuariosAdapter.ViewHolder> {
     private Context context;
     private List<Usuario> usuariosList = new ArrayList<>();
     private DatabaseHelper db;

    /**
     * construtor do adapter
     * @param context
     * @param usuarios
     */
    public opUsuariosAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuariosList= usuarios;
        this.db = new DatabaseHelper(context);
    }

    /**
     * referenciando o layout que sera utilizado.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public opUsuariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detalhesusuaio, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull opUsuariosAdapter.ViewHolder holder, int position) {
        Usuario usuario = usuariosList.get(position);
        holder.atualizaUi(usuario);

    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }
    public void delete(int position){
        usuariosList.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        public TextView tSobrenome;
        public TextView tEmail;
        public TextView tDataNasc;

        public ViewHolder(View v) {
            super(v);
            tNome = v.findViewById(R.id.tnome);
            tSobrenome = v.findViewById(R.id.tsobrenome);
            tEmail = v.findViewById(R.id.temail);
            tDataNasc = v.findViewById(R.id.tdatanasc);
        }

        // metodo rersponsavel por atualizar a Ui com os valores da lista
        public void atualizaUi(Usuario usuario) {
            tNome.setText(usuario.getNome());
            tSobrenome.setText(usuario.getSobrenome());
            tEmail.setText(usuario.getEmail());
            tDataNasc.setText(usuario.getDataNasc());

        }
    }
}
