package br.com.developer.allefsousa.izagrocadastro.operacaoUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.data.Usuario;
import br.com.developer.allefsousa.izagrocadastro.operacaoUsuario.Adapters.DividerItemDecoration;
import br.com.developer.allefsousa.izagrocadastro.operacaoUsuario.Adapters.RecyclerTouchListener;
import br.com.developer.allefsousa.izagrocadastro.operacaoUsuario.Adapters.opUsuariosAdapter;
import br.com.developer.allefsousa.izagrocadastro.postUsuario.postUsuarioView;
import br.com.developer.allefsousa.izagrocadastro.utils.Mask;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class opUsuarioView extends AppCompatActivity implements opUsuarioContract.view {

    //region Variaveis Globais
    @BindString(R.string.app_name)
    String appName;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAddUsuario;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerViewUsuarios;
    private opUsuariosAdapter opUsuariosAdapter;
    private List<Usuario> usuarioList;
    private opUsuarioContract.presenter Mpresenter;
    //endregion Globais


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_usuario);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        Mpresenter = new opUsuarioPresenter(this, this);
        toolbar.setTitle(appName);


        fabAddUsuario.setOnClickListener(View -> startActivity(new Intent(opUsuarioView.this, postUsuarioView.class)));

        usuarioList = new ArrayList<>();
        Mpresenter.BuscarClientes();

        recyclerViewUsuarios.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewUsuarios, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                Mpresenter.Longclick(position);
            }
        }));

        /**
         * Rotina adicionada para tirar a visibilidade do floataction button quando o recycler view for
         * rodado
         */
        recyclerViewUsuarios.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scroll Down
                    if (fabAddUsuario.isShown()) {
                        fabAddUsuario.hide();
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (!fabAddUsuario.isShown()) {
                        fabAddUsuario.show();
                    }
                }
            }
        });

    }

    /**
     * Metodo que exibe o menu para remover e atualizar o cadastro
     * @param usuario
     * @param position
     */
    @Override
    public void showOperacoesDialog(final Usuario usuario, final int position) {
        CharSequence colors[] = new CharSequence[]{"Atualizar", "Deletar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione uma Opção");
        builder.setItems(colors, (dialog, which) -> {
            if (which == 0) {
                showUpdateUsuarioDialog(true, usuario);
            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(appName)
                        .setContentText("Tem certeza que gostaria de remover esse Registro ?")
                        .setConfirmText("Sim, Remover")
                        .setConfirmClickListener(sDialog -> {
                            sDialog.dismissWithAnimation();

                            Mpresenter.deleteUsuario(usuario);
                            opUsuariosAdapter.delete(position);
                            Mpresenter.BuscarClientes();
                            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText(appName)
                                    .setContentText("Cliente Removido.")
                                    .show();


                        })
                        .setCancelButton("Cancelar", sDialog -> sDialog.dismissWithAnimation())
                        .show();

            }
        });
        builder.show();
    }

    /**
     * Configurações Iniciais do recycler View
     * @param usuariosll
     */
    public void ConfigRecyclerView(List<Usuario> usuariosll) {

        opUsuariosAdapter = new opUsuariosAdapter(this, usuariosll);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsuarios.setLayoutManager(mLayoutManager);
        recyclerViewUsuarios.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsuarios.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerViewUsuarios.setAdapter(opUsuariosAdapter);
    }


    /**
     * Configuração do Dialog que sera exibido para atualizar os dados
     * @param shouldUpdate
     * @param usuario
     */
    private void showUpdateUsuarioDialog(final boolean shouldUpdate, final Usuario usuario) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.dialog_update, null);
        //region init Components View
        EditText edtDataNasc = view.findViewById(R.id.editUpDatanasc);
        EditText edtNome = view.findViewById(R.id.editUpNome);
        EditText edtSobrenome = view.findViewById(R.id.editUpSobrenome);
        EditText edtEmail = view.findViewById(R.id.editUpEmail);
        //endregion

        edtDataNasc.addTextChangedListener(Mask.insert("##/##/####", edtDataNasc));
        Usuario usuario1 = new Usuario();

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(opUsuarioView.this);
        alertDialogBuilderUserInput.setView(view);

        if (shouldUpdate && usuario != null) {
            usuario1.setId(usuario.getId());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Atualizar", (dialogBox, id) -> {
                })
                .setNegativeButton("cancelar",
                        (dialogBox, id) -> dialogBox.cancel());

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {

            usuario1.setNome(edtNome.getText().toString());
            usuario1.setSobrenome(edtSobrenome.getText().toString());
            usuario1.setEmail(edtEmail.getText().toString());
            usuario1.setDataNasc(edtDataNasc.getText().toString());

            if (!TextUtils.isEmpty(usuario1.getNome()) && !TextUtils.isEmpty(usuario1.getSobrenome()) && !TextUtils.isEmpty(usuario1.getEmail()) && !TextUtils.isEmpty(usuario1.getDataNasc())) {

                Mpresenter.putUsuario(usuario1);
                Mpresenter.BuscarClientes();
                alertDialog.dismiss();

                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText(appName)
                        .setContentText("Cliente Atualizado.")
                        .show();


            } else {
                Toast.makeText(this, " Impossivel Atualizar \n Existem Campos em Branco !", Toast.LENGTH_LONG).show();
            }


        });
    }



}
