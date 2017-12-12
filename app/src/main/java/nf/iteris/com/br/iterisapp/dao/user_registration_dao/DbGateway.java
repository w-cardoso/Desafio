package nf.iteris.com.br.iterisapp.dao.user_registration_dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by re034850 on 12/10/2017.
 */

public class DbGateway {

    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context ctx){
        DbHelper divida = new DbHelper(ctx);
        db = divida.getWritableDatabase();
    }

    public static DbGateway getInstance(Context context){
        if(gw == null)
            gw = new DbGateway(context);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
