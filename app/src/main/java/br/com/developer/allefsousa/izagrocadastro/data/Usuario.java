package br.com.developer.allefsousa.izagrocadastro.data;

/**
 * Created by allef on 22/08/2018.
 */

/**
 * Classe de modelo do Objeto
 */
public class Usuario {

    //region Configuração Banco de dados
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_SOBRENOME = "sobrenome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DATANASC = "datanasc";
    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOME + " TEXT,"
                    + COLUMN_SOBRENOME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_DATANASC + " TEXT"
                    + ")";
    //endregion

    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String dataNasc;

    public Usuario() {
    }

    public Usuario(int id, String nome, String sobrenome, String email, String dataNasc) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public Usuario(String nome, String sobrenome, String email, String dataNasc) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNasc = dataNasc;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
}
