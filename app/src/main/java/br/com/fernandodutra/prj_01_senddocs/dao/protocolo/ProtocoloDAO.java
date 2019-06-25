package br.com.fernandodutra.prj_01_senddocs.dao.protocolo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.model.protocolo.Protocolo;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class ProtocoloDAO extends DAO {

    private Context context;

    public static final String TABELA = "tb_protocolo";
    public static final String IDPROTOCOLO = "idprotocolo";
    public static final String DATAEMISSAO = "dataemissao";
    public static final String DATAPREVISAO = "dataprevisao";
    public static final String TOTALPROT = "totalprot";
    public static final String CODORIGEM = "codorigem";
    public static final String NOMEORIGEM = "nomeorigem";
    public static final String ENDORIGEM = "endorigem";
    public static final String EMAILORIGEM = "emailorigem";
    public static final String CODDESTINO = "coddestino";
    public static final String NOMEDESTINO = "nomedestino";
    public static final String ENDDESTINO = "enddestino";
    public static final String EMAILDESTINO = "emaildestino";
    public static final String CODENTREGADOR = "codentregador";
    public static final String NOMEENTREGADOR = "nomeentregador";
    public static final String ENTREGUE = "entregue";
    public static final String DATAENTREGA = "dataentrega";
    public static final String RGRECEBEDOR = "rgrecebedor";
    public static final String ASSINATURA = "assinatura";
    public static final String FOTO = "foto";

    public ProtocoloDAO(Context context) {
        super(context);
        context = context;
    }

    public void insertProtocolo(Protocolo protocolo) {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDPROTOCOLO, protocolo.getIdprotocolo());
            cv.put(DATAEMISSAO, protocolo.getStrDataemissao());
            cv.put(DATAPREVISAO, protocolo.getStrDataprevisao());
            cv.put(TOTALPROT, protocolo.getTotalprot());
            cv.put(CODORIGEM, protocolo.getCodorigem());
            cv.put(NOMEORIGEM, protocolo.getNomeorigem());
            cv.put(ENDORIGEM, protocolo.getEndorigem());
            cv.put(EMAILORIGEM, protocolo.getEmailorigem());
            cv.put(CODDESTINO, protocolo.getCoddestino());
            cv.put(NOMEDESTINO, protocolo.getNomedestino());
            cv.put(ENDDESTINO, protocolo.getEnddestino());
            cv.put(EMAILDESTINO, protocolo.getEmaildestino());
            cv.put(CODENTREGADOR, protocolo.getCodentregador());
            cv.put(NOMEENTREGADOR, protocolo.getNomeentregador());
            cv.put(ENTREGUE, protocolo.getEntregue());
            cv.put(DATAENTREGA, protocolo.getStrDataentrega());
            cv.put(RGRECEBEDOR, protocolo.getRgrecebedor());
            //cv.put(ASSINATURA, protocolo.getAssinatura());
            //cv.put(FOTO, protocolo.getFoto());

            db.insert(TABELA, null, cv);
        } finally {
            closeDataBase();
        }
    }

    public void updateProtocolo(Protocolo protocolo) {
        openDataBase();
        //
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(DATAEMISSAO, protocolo.getStrDataemissao());
            cv.put(DATAPREVISAO, protocolo.getStrDataprevisao());
            cv.put(TOTALPROT, protocolo.getTotalprot());
            cv.put(CODORIGEM, protocolo.getCodorigem());
            cv.put(NOMEORIGEM, protocolo.getNomeorigem());
            cv.put(ENDORIGEM, protocolo.getEndorigem());
            cv.put(EMAILORIGEM, protocolo.getEmailorigem());
            cv.put(CODDESTINO, protocolo.getCoddestino());
            cv.put(NOMEDESTINO, protocolo.getNomedestino());
            cv.put(ENDDESTINO, protocolo.getEnddestino());
            cv.put(EMAILDESTINO, protocolo.getEmaildestino());
            cv.put(CODENTREGADOR, protocolo.getCodentregador());
            cv.put(NOMEENTREGADOR, protocolo.getNomeentregador());
            cv.put(ENTREGUE, protocolo.getEntregue());
            cv.put(DATAENTREGA, protocolo.getStrDataentrega());
            cv.put(RGRECEBEDOR, protocolo.getRgrecebedor());
            //cv.put(ASSINATURA, protocolo.getAssinatura());
            //cv.put(FOTO, protocolo.getFoto());

            String filter = " idprotocolo = ?";
            String[] arguments = {String.valueOf(protocolo.getIdprotocolo())};

            db.update(TABELA, cv, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    public void deleteProtocolo(Protocolo protocolo) {
        openDataBase();
        try {
            String filter = " idprotocolo = ?";
            String[] arguments = {String.valueOf(protocolo.getIdprotocolo())};
            //
            db.delete(TABELA, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    public void findProtocoloById(long idprotocolo) {
        openDataBase();
        //
        Cursor cursor = null;
        //
        Protocolo cAux = null;
        //
        try {
            String sqlQuery = " select * from tb_protocolo where idprotocolo = ?";
            String[] arguments = {String.valueOf(idprotocolo)};
            //
            cursor = db.rawQuery(sqlQuery, arguments);
            //
            while (cursor.moveToNext()) {
                cAux = new Protocolo();
                cAux.setIdprotocolo(cursor.getLong(cursor.getColumnIndex(IDPROTOCOLO)));
                cAux.setStrDataemissao(cursor.getString(cursor.getColumnIndex(DATAEMISSAO)));
                cAux.setStrDataprevisao(cursor.getString(cursor.getColumnIndex(DATAPREVISAO)));
                cAux.setTotalprot(cursor.getInt(cursor.getColumnIndex(TOTALPROT)));
                cAux.setCodorigem(cursor.getInt(cursor.getColumnIndex(CODORIGEM)));
                cAux.setNomeorigem(cursor.getString(cursor.getColumnIndex(NOMEORIGEM)));
                cAux.setEndorigem(cursor.getString(cursor.getColumnIndex(ENDORIGEM)));
                cAux.setEmailorigem(cursor.getString(cursor.getColumnIndex(EMAILORIGEM)));
                cAux.setNomedestino(cursor.getString(cursor.getColumnIndex(NOMEDESTINO)));
                cAux.setEnddestino(cursor.getString(cursor.getColumnIndex(ENDDESTINO)));
                cAux.setEmaildestino(cursor.getString(cursor.getColumnIndex(EMAILDESTINO)));
                cAux.setCodentregador(cursor.getInt(cursor.getColumnIndex(CODENTREGADOR)));
                cAux.setNomeentregador(cursor.getString(cursor.getColumnIndex(NOMEENTREGADOR)));
                cAux.setEntregue(cursor.getString(cursor.getColumnIndex(ENTREGUE)));
                cAux.setStrDataentrega(cursor.getString(cursor.getColumnIndex(DATAENTREGA)));
                cAux.setRgrecebedor(cursor.getString(cursor.getColumnIndex(RGRECEBEDOR)));
            }
            //
            cursor.close();

        } finally {
            closeDataBase();
        }
    }

    public ArrayList<HMAux> findListProtocolo() {
        openDataBase();
        //
        Cursor cursor = null;
        //
        ArrayList<HMAux> protocolos = new ArrayList<>();
        //
        try {
            String sqlQuery = " select * from tb_protocolo where idprotocolo = ?";
            //
            cursor = db.rawQuery(sqlQuery, null);
            //
            while (cursor.moveToNext()) {
                HMAux cAux = new HMAux();
                //
                cAux.put(IDPROTOCOLO, cursor.getString(cursor.getColumnIndex(IDPROTOCOLO)));
                cAux.put(DATAEMISSAO, cursor.getString(cursor.getColumnIndex(DATAEMISSAO)));
                cAux.put(DATAPREVISAO, cursor.getString(cursor.getColumnIndex(DATAPREVISAO)));
                cAux.put(TOTALPROT, cursor.getString(cursor.getColumnIndex(TOTALPROT)));
                cAux.put(CODORIGEM, cursor.getString(cursor.getColumnIndex(CODORIGEM)));
                cAux.put(NOMEORIGEM, cursor.getString(cursor.getColumnIndex(NOMEORIGEM)));
                cAux.put(ENDORIGEM, cursor.getString(cursor.getColumnIndex(ENDORIGEM)));
                cAux.put(EMAILORIGEM, cursor.getString(cursor.getColumnIndex(EMAILORIGEM)));
                cAux.put(CODDESTINO, cursor.getString(cursor.getColumnIndex(CODDESTINO)));
                cAux.put(NOMEDESTINO, cursor.getString(cursor.getColumnIndex(NOMEDESTINO)));
                cAux.put(ENDDESTINO, cursor.getString(cursor.getColumnIndex(ENDDESTINO)));
                cAux.put(EMAILDESTINO, cursor.getString(cursor.getColumnIndex(EMAILDESTINO)));
                cAux.put(CODENTREGADOR, cursor.getString(cursor.getColumnIndex(CODENTREGADOR)));
                cAux.put(NOMEENTREGADOR, cursor.getString(cursor.getColumnIndex(NOMEENTREGADOR)));
                cAux.put(ENTREGUE, cursor.getString(cursor.getColumnIndex(ENTREGUE)));
                cAux.put(DATAENTREGA, cursor.getString(cursor.getColumnIndex(DATAENTREGA)));
                cAux.put(RGRECEBEDOR, cursor.getString(cursor.getColumnIndex(RGRECEBEDOR)));
                //
                protocolos.add(cAux);
            }
            //
            cursor.close();

        } finally {
            closeDataBase();
        }
        return protocolos;
    }

    public long nextID() {
        long nextIdProtocolo = -1L;
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select ifnull(max(idprotocolo)+1,1) as idprotocolo from tb_protocolo; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextIdProtocolo = cursor.getLong(cursor.getColumnIndex("idprotocolo"));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextIdProtocolo;
    }
}
