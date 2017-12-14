package nf.iteris.com.br.iterisapp.dao.user_registration_dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nf.iteris.com.br.iterisapp.model.UserRegistration;

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
    public static final String COLUNA_PROFILE = "profile";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_CPF + " TEXT, " +
                    COLUNA_NAME + " TEXT, " +
                    COLUNA_PASSWORD + " TEXT, " +
                    COLUNA_PROFILE + " TEXT " + ")";

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

    public void addUser(UserRegistration user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_CPF, user.getCpf());
        cv.put(COLUNA_NAME, user.getName());
        cv.put(COLUNA_PASSWORD, user.getPassword());
        cv.put(COLUNA_PROFILE, user.getProfile());
        db.insert(TABELA_NOME, null, cv);
        db.close();
    }

    public boolean checkUser(String cpf) {

        // array of columns to fetch
        String[] columns = {
                COLUNA_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUNA_CPF + " = ?";

        // selection argument
        String[] selectionArgs = {cpf};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABELA_NOME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String cpf, String password, String profile) {

        // array of columns to fetch
        String[] columns = {
                COLUNA_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUNA_CPF + " = ?" + " AND " + COLUNA_PASSWORD + " = ?" + " AND " + COLUNA_PROFILE + " = ?";

        // selection arguments
        String[] selectionArgs = {cpf, password, profile};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABELA_NOME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
