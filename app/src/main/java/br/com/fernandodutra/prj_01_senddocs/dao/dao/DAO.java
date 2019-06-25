package br.com.fernandodutra.prj_01_senddocs.dao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.fernandodutra.prj_01_senddocs.domain.SQLiteHelper;
import br.com.fernandodutra.prj_01_senddocs.utils.Constants;

public class DAO {

    private Context context;
    protected SQLiteDatabase db;

    public DAO(Context context) {
        this.context = context;
    }

    public void openDataBase() {
        SQLiteHelper vHelper = new SQLiteHelper(
                context,
                Constants.DATABASE,
                null,
                Constants.VERSION
        );

        this.db = vHelper.getWritableDatabase(); // solicitacao de banco validas.
    }

    public void closeDataBase() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
