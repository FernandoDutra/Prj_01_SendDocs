package br.com.fernandodutra.prj_01_senddocs.dao.protocoloitens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.model.protocoloitens.ProtocoloItens;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class ProtocoloItensDAO extends DAO {

    public static final String TABELA = "tb_protocoloitens";
    public static final String IDPROTOCOLO = "idprotocolo";
    public static final String IDPROTOCOLOITENS = "idprotocoloitens";
    public static final String IDTIPODOC = "idtipodoc";
    public static final String NOMEPROTOCOLO = "nomeprotocolo";
    public static final String OBSERVACAO = "observacao";

    public ProtocoloItensDAO(Context context) {
        super(context);
    }

    public void insertProtocoloItens(ProtocoloItens protocoloItens) {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDPROTOCOLO, protocoloItens.getIdprotocolo());
            cv.put(IDPROTOCOLOITENS, protocoloItens.getIdprotocoloitens());
            cv.put(IDTIPODOC, protocoloItens.getIdtipodoc());
            cv.put(NOMEPROTOCOLO, protocoloItens.getNomeprotocolo());
            cv.put(OBSERVACAO, protocoloItens.getNomeprotocolo());
            //
            db.insert(TABELA, null, cv);
        } finally {
            closeDataBase();
        }
    }

    public void updateProtocoloItens(ProtocoloItens protocoloItens) {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDTIPODOC, protocoloItens.getIdtipodoc());
            cv.put(NOMEPROTOCOLO, protocoloItens.getNomeprotocolo());
            cv.put(OBSERVACAO, protocoloItens.getNomeprotocolo());
            //
            String filter = " idprotocoloitens = ?";
            String[] arguments = {String.valueOf(protocoloItens.getIdprotocoloitens())};
            //
            db.update(TABELA, cv, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    public void deleteProtocoloItens(ProtocoloItens protocoloItens) {
        openDataBase();
        try {
            String filter = " idprotocoloitens = ? ";
            String[] arguments = {String.valueOf(protocoloItens.getIdprotocoloitens())};
            //
            db.delete(TABELA, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    public void findProtocoloItensByID(long idProtocoloItens) {
        openDataBase();
        //
        Cursor cursor = null;
        //
        ProtocoloItens cAux = null;
        //
        try {
            String sqlQuery = " select * from tb_protocoloitens where idprotocoloitens = ? ";
            String[] arguments = {String.valueOf(idProtocoloItens)};
            //
            cursor = db.rawQuery(sqlQuery, arguments);
            //
            while (cursor.moveToNext()) {
                cAux = new ProtocoloItens();
                //
                cAux.setIdprotocolo(cursor.getLong(cursor.getColumnIndex(IDPROTOCOLO)));
                cAux.setIdprotocoloitens(cursor.getLong(cursor.getColumnIndex(IDPROTOCOLOITENS)));
                cAux.setObservacao(cursor.getString(cursor.getColumnIndex(OBSERVACAO)));
                cAux.setNomeprotocolo(cursor.getString(cursor.getColumnIndex(NOMEPROTOCOLO)));
                cAux.setIdtipodoc(cursor.getLong(cursor.getColumnIndex(IDTIPODOC)));
            }
            //
            cursor.close();
        } finally {
            closeDataBase();
        }
    }

    public ArrayList<HMAux> findListProtocoloItens(long idprotocoloitens) {
        openDataBase();
        //
        Cursor cursor = null;
        //
        ArrayList<HMAux> protocoloItens = new ArrayList<>();
        //
        try {
            String sqlQuery = "select * from tb_protocoloitens where idprotocoloitens = ? ";
            String[] arguments = {String.valueOf(idprotocoloitens)};
            //
            cursor = db.rawQuery(sqlQuery, arguments);
            //
            while (cursor.moveToNext()) {
                HMAux hmAux = new HMAux();
                hmAux.put(IDPROTOCOLO, cursor.getString(cursor.getColumnIndex(IDPROTOCOLO)));
                hmAux.put(IDPROTOCOLOITENS, cursor.getString(cursor.getColumnIndex(IDPROTOCOLOITENS)));
                hmAux.put(IDTIPODOC, cursor.getString(cursor.getColumnIndex(IDTIPODOC)));
                hmAux.put(NOMEPROTOCOLO, cursor.getString(cursor.getColumnIndex(NOMEPROTOCOLO)));
                hmAux.put(OBSERVACAO, cursor.getString(cursor.getColumnIndex(OBSERVACAO)));
                //
                protocoloItens.add(hmAux);
            }
            //
            cursor.close();
        } finally {
            closeDataBase();
        }

        return protocoloItens;
    }

    public long nextID() {
        long nextIdProtocoloItens = -1L;
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select ifnull(max(idprotocoloitens)+1,1) as idprotocoloitens from tb_protocoloitens; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextIdProtocoloItens = cursor.getLong(cursor.getColumnIndex("idprotocoloitens"));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextIdProtocoloItens;
    }
}
