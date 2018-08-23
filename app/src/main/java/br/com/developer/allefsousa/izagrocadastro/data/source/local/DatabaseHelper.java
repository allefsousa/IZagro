package br.com.developer.allefsousa.izagrocadastro.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public long postUsuario(Usuario User) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Usuario.COLUMN_NOME, User.getNome());
        values.put(Usuario.COLUMN_SOBRENOME, User.getSobrenome());
        values.put(Usuario.COLUMN_EMAIL, User.getEmail());
        values.put(Usuario.COLUMN_DATANASC, User.getDataNasc());


        long id = db.insert(Usuario.TABLE_NAME, null, values);


        db.close();

        return id;
    }

    public Usuario getUsuario(long id) {

      // abrindo a conexao com o bd
        SQLiteDatabase db = this.getReadableDatabase();

        // cursor paar receber os dados
        Cursor cursor = db.query(Usuario.TABLE_NAME,
                new String[]{Usuario.COLUMN_ID, Usuario.COLUMN_NOME, Usuario.COLUMN_SOBRENOME,Usuario.COLUMN_EMAIL,Usuario.COLUMN_DATANASC},
                Usuario.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        Usuario usuario = new Usuario(
                cursor.getInt(cursor.getColumnIndex(Usuario.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_NOME)),
                cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_SOBRENOME)),
                cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_DATANASC)));


        cursor.close();

        return usuario;
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> notes = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + Usuario.TABLE_NAME + " ORDER BY " +
                Usuario.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Usuario note = new Usuario();
                note.setId(cursor.getInt(cursor.getColumnIndex(Usuario.COLUMN_ID)));
                note.setNome(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_NOME)));
                note.setSobrenome(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_SOBRENOME)));
                note.setEmail(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_EMAIL)));
                note.setDataNasc(cursor.getString(cursor.getColumnIndex(Usuario.COLUMN_DATANASC)));

                notes.add(note);
            } while (cursor.moveToNext());
        }


        db.close();


        return notes;
    }


    public int putUsuario(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Usuario.COLUMN_NOME, user.getNome());
        values.put(Usuario.COLUMN_SOBRENOME, user.getSobrenome());
        values.put(Usuario.COLUMN_EMAIL, user.getEmail());
        values.put(Usuario.COLUMN_DATANASC, user.getDataNasc());


        return db.update(Usuario.TABLE_NAME, values, Usuario.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    public void deleteUsuario(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Usuario.TABLE_NAME, Usuario.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        db.close();
    }

}
