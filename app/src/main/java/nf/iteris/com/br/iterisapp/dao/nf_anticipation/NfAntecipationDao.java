package nf.iteris.com.br.iterisapp.dao.nf_anticipation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.model.NfAntecipation;

/**
 * Created by re034850 on 14/12/2017.
 */

public class NfAntecipationDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nfantecipation.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABELA_NOME = "notafiscalantecipation";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NUMBER = "number";
    public static final String COLUNA_DESCRIPTION = "description";
    public static final String COLUNA_DATEBILLING = "dateBilling";
    public static final String COLUNA_DATEPAYMENT = "datePayment";
    public static final String COLUNA_STATUS = "status";


    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_NUMBER + " TEXT, " +
                    COLUNA_DESCRIPTION + " TEXT, " +
                    COLUNA_DATEBILLING + " TEXT, " +
                    COLUNA_DATEPAYMENT + " TEXT, " +
                    COLUNA_STATUS + " TEXT " + ")";

    public NfAntecipationDao(Context context) {
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

    public void addNotaFiscalAntecipation(NfAntecipation nf) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_NUMBER, nf.getNumber());
        cv.put(COLUNA_DESCRIPTION, nf.getDescription());
        cv.put(COLUNA_DATEBILLING, nf.getDateBilling());
        cv.put(COLUNA_DATEPAYMENT, nf.getDatePayment());
        cv.put(COLUNA_STATUS, nf.getStatus());
        db.insert(TABELA_NOME, null, cv);
        db.close();
    }

    public List<NfAntecipation> getAllUser() {

        String[] columns = {
                COLUNA_NUMBER,
                COLUNA_DESCRIPTION,
                COLUNA_DATEBILLING,
                COLUNA_DATEPAYMENT,
                COLUNA_STATUS
        };

        String sortOrder =
                COLUNA_NUMBER + " ASC";
        List<NfAntecipation> nfList = new ArrayList<NfAntecipation>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_NOME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        if (cursor.moveToFirst()) {
            do {
                NfAntecipation nf = new NfAntecipation();

                nf.setNumber(cursor.getString(cursor.getColumnIndex(COLUNA_NUMBER)));
                nf.setDescription(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRIPTION)));
                nf.setDateBilling(cursor.getString(cursor.getColumnIndex(COLUNA_DATEBILLING)));
                nf.setDatePayment(cursor.getString(cursor.getColumnIndex(COLUNA_DATEPAYMENT)));
                nf.setStatus(cursor.getString(cursor.getColumnIndex(COLUNA_STATUS)));


                nfList.add(nf);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        return nfList;
    }

    public NfAntecipation pegarDados(String nf) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_NOME, new String[]{COLUNA_NUMBER,
                        COLUNA_DESCRIPTION,
                        COLUNA_DATEBILLING,
                        COLUNA_DATEPAYMENT,
                        COLUNA_STATUS}, COLUNA_NUMBER + "=?",
                new String[]{nf}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            String number = cursor.getString(0);
            String description = cursor.getString(1);
            String dateBilling = cursor.getString(2);
            String datePayment = cursor.getString(3);
            String status = "Antecipado";

            return new NfAntecipation(number, description, dateBilling, datePayment, status);
        }
        return null;
    }

    public void deleteNf(String nf) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABELA_NOME, COLUNA_NUMBER + " = ?",
                new String[]{nf});
        db.close();
    }
}
