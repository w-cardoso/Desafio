package nf.iteris.com.br.iterisapp.dao.nf_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.model.NfRegistration;

/**
 * Created by cardo on 12/12/2017.
 */

public class NfRegistrationDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nf.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABELA_NOME = "notafiscal";
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

    public NfRegistrationDao(Context context) {
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

    public void addNotaFiscal(NfRegistration nf) {
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

    public List<NfRegistration> getAllUser() {

        String[] columns = {
                COLUNA_NUMBER,
                COLUNA_DESCRIPTION,
                COLUNA_DATEBILLING,
                COLUNA_DATEPAYMENT,
                COLUNA_STATUS
        };

        String sortOrder =
                COLUNA_NUMBER + " ASC";
        List<NfRegistration> nfList = new ArrayList<NfRegistration>();
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
                NfRegistration nf = new NfRegistration();

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

    public NfRegistration pegarDados(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_NOME, new String[]{COLUNA_NUMBER,
                        COLUNA_DESCRIPTION,
                        COLUNA_DATEBILLING,
                        COLUNA_DATEPAYMENT,
                        COLUNA_STATUS}, COLUNA_NUMBER + "=?",
                new String[]{id}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            String number = cursor.getString(0);
            String description = cursor.getString(1);
            String dateBilling = cursor.getString(2);
            String datePayment = cursor.getString(3);
            String status = cursor.getString(4);

            return new NfRegistration(number, description, dateBilling, datePayment, status);
        }
        return null;
    }

    public void deleteNf(String nf) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_NOME, COLUNA_NUMBER + " = ?",
                new String[]{nf});
        db.close();
    }
}
