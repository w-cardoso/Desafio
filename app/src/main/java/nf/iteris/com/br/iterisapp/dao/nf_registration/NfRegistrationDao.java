package nf.iteris.com.br.iterisapp.dao.nf_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
