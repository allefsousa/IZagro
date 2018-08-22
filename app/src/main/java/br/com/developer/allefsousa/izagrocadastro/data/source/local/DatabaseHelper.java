package br.com.developer.allefsousa.izagrocadastro.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.developer.allefsousa.izagrocadastro.data.Usuario;

/**
 * Created by allef on 22/08/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Versão Banco
    private static final int DATABASE_VERSION = 1;

    // Nome Banco
    private static final String DATABASE_NAME = "izagro_db";

    // Construtor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Usuario.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // Apagando a tabela
        db.execSQL("DROP TABLE IF EXISTS " + Usuario.TABLE_NAME);

        // Create as tabelas
        onCreate(db);
    }
}
