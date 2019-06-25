package br.com.fernandodutra.prj_01_senddocs.dao.tipodoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class TipoDocDAOLiter extends DAO implements TipoDocDAO<TipoDoc> {

    public static final String TABELA = "tb_tipodoc";
    public static final String IDTIPODOC = "idtipodoc";
    public static final String NOME = "nome";
    public static final String OBSERVACAO = "observacao";

    public TipoDocDAOLiter(Context context) {
        super(context);
    }

    @Override
    public void insertTipoDoc(TipoDoc tipodoc) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDTIPODOC, tipodoc.getIdtipodoc());
            cv.put(NOME, tipodoc.getNome());
            cv.put(OBSERVACAO, tipodoc.getObservacao());

            db.insert(TABELA, null, cv);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void updateTipoDoc(TipoDoc tipodoc) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            String filter = " idtipodoc = ?";
            String[] arguments = {String.valueOf(tipodoc.getIdtipodoc())};
            //
            cv.put(NOME, tipodoc.getNome());
            cv.put(OBSERVACAO, tipodoc.getObservacao());

            db.update(TABELA, cv, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void deleteTipoDoc(long idTipoDoc) throws SQLException {
        openDataBase();
        try {
            String filter = " idtipodoc = ?";
            String[] arguments = {String.valueOf(idTipoDoc)};
            //
            db.delete(TABELA, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    @Override
    public TipoDoc findTipoDocByID(long idtipodoc) throws SQLException {
        openDataBase();
        //
        Cursor cursor = null;
        //
        TipoDoc cAux = null;
        //
        try {
            String sqlQuery = " select * from tb_tipodoc where idtipodoc = ? ";
            String[] argumentos = {String.valueOf(idtipodoc)};

            cursor = db.rawQuery(sqlQuery, argumentos);

            while (cursor.moveToNext()) {
                cAux = new TipoDoc();
                cAux.setIdtipodoc(cursor.getLong(cursor.getColumnIndex(IDTIPODOC)));
                cAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                cAux.setObservacao(cursor.getString(cursor.getColumnIndex(OBSERVACAO)));
            }

            cursor.close();

        } finally {
            closeDataBase();
        }
        return cAux;
    }

    @Override
    public ArrayList<HMAux> findListTipoDoc() throws SQLException {
        openDataBase();
        //
        ArrayList<HMAux> clientes = new ArrayList<>();
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select idtipodoc, nome from tb_tipodoc ";
            //
            cursor = db.rawQuery(sqlQuery, null);
            //
            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDTIPODOC, cursor.getString(cursor.getColumnIndex(IDTIPODOC)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                clientes.add(aux);
            }

            cursor.close();

        } finally {
            closeDataBase();
        }

        return clientes;
    }

    @Override
    public ArrayList<HMAux> findListTipoDoc(HMAux filter, HMAux order) throws SQLException {
        openDataBase();
        //
        ArrayList<HMAux> tipodocs = new ArrayList<>();
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder sqlQuery = new StringBuilder();

            sqlQuery.append(" select idtipodoc, nome from tb_tipodoc where idtipodoc is not null ");
            String[] argumentos = new String[filter.size()];
            int i = 0;

            for (HMAux.Entry<String, String> entrada : filter.entrySet()) {
                sqlQuery.append(" and (" + entrada.getKey() + " like ? ) ");
                argumentos[i] = "%"  + String.valueOf(entrada.getValue()) + "%";
                i++;
            }

            if (order.size() > 0) {
                sqlQuery.append(" order by ");

                i = 0;
                String aux = "";
                for (HMAux.Entry<String, String> entrada : order.entrySet()) {
                    i++;
                    aux = (i == order.size()) ? "" : ", ";
                    sqlQuery.append(entrada.getValue() + aux);
                }
            } else {
                sqlQuery.append(" order by nome ");
            }

            cursor = db.rawQuery(sqlQuery.toString(), argumentos);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDTIPODOC, cursor.getString(cursor.getColumnIndex(IDTIPODOC)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                tipodocs.add(aux);
            }

            cursor.close();

        } finally {
            closeDataBase();
        }

        return tipodocs;
    }

    @Override
    public long nextID() throws SQLException {
        long nextIdTipoDoc = -1l;
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select ifnull(max(idtipodoc)+1,1) as idtipodoc from tb_tipodoc; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextIdTipoDoc = cursor.getLong(cursor.getColumnIndex("idtipodoc"));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextIdTipoDoc;
    }

}
