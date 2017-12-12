package nf.iteris.com.br.iterisapp.dao.user_registration_dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by re034850 on 12/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "info.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABELA_NOME = "user";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_CPF = "cpf";
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_PASSWORD = "password";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_CPF + " TEXT, " +
                    COLUNA_NAME + " TEXT, " +
                    COLUNA_PASSWORD + " TEXT " + ")";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_NOME);
        onCreate(sqLiteDatabase);
    }
}
