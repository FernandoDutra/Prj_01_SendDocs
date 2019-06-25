package br.com.fernandodutra.prj_01_senddocs.domain;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS [tb_cliente] \n" +
                    "  ( \n" +
                    "     idcliente    INT NOT NULL, \n" +
                    "     nome         TEXT, \n" +
                    "     cpfcnpj      TEXT, \n" +
                    "     rgie         TEXT, \n" +
                    "     dataabertura DATE, \n" +
                    "     site         TEXT, \n" +
                    "     email        TEXT, \n" +
                    "     cep          TEXT, \n" +
                    "     endereco     TEXT, \n" +
                    "     numero       TEXT, \n" +
                    "     bairro       TEXT, \n" +
                    "     cidade       TEXT, \n" +
                    "     estado       TEXT, \n" +
                    "     telefone     TEXT, \n" +
                    "     celular      TEXT, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([idcliente]) \n" +
                    "  ); ");
            //
            sb.append("CREATE TABLE IF NOT EXISTS [tb_departamento] \n" +
                    "  ( \n" +
                    "     iddepartamento INT NOT NULL, \n" +
                    "     nome           TEXT, \n" +
                    "     responsavel    TEXT, \n" +
                    "     telefone       TEXT, \n" +
                    "     celular        TEXT, \n" +
                    "     observacao     TEXT, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([iddepartamento]) \n" +
                    "  ); ");
            //
            sb.append("CREATE TABLE IF NOT EXISTS [tb_tipodoc] \n" +
                    "  ( \n" +
                    "     idtipodoc  INT NOT NULL, \n" +
                    "     nome       TEXT, \n" +
                    "     observacao TEXT, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([idtipodoc]) \n" +
                    "  ); ");
            //
            sb.append("CREATE TABLE IF NOT EXISTS [tb_usuario] \n" +
                    "  ( \n" +
                    "     idusuario   INT NOT NULL, \n" +
                    "     nome        TEXT, \n" +
                    "     email       TEXT, \n" +
                    "     senha       TEXT, \n" +
                    "     nivelacesso INT, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([idusuario]) \n" +
                    "  ); ");
            //
            sb.append("CREATE TABLE IF NOT EXISTS [tb_protocolo] \n" +
                    "  ( \n" +
                    "     idprotocolo    INT NOT NULL, \n" +
                    "     dataemissao    DATE, \n" +
                    "     dataprevisao   DATE, \n" +
                    "     totalprot      INT, \n" +
                    "     codorigem      INT, \n" +
                    "     nomeorigem     TEXT, \n" +
                    "     endorigem      TEXT, \n" +
                    "     emailorigem    TEXT, \n" +
                    "     coddestino     INT, \n" +
                    "     nomedestino    TEXT, \n" +
                    "     enddestino     TEXT, \n" +
                    "     emaildestino   TEXT, \n" +
                    "     codentregador  INT, \n" +
                    "     nomeentregador TEXT, \n" +
                    "     entregue       TEXT, \n" +
                    "     dataentrega    DATE, \n" +
                    "     rgrecebedor    TEXT, \n" +
                    "     assinatura     IMAGE, \n" +
                    "     foto           IMAGE, \n" +
                    "     email          TEXT, \n" +
                    "     senha          TEXT, \n" +
                    "     nivelacesso    INT, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([idprotocolo]) \n" +
                    "  ); ");
            //
            sb.append("CREATE TABLE IF NOT EXISTS [tb_protocoloitens] \n" +
                    "  ( \n" +
                    "     idprotocolo      INT NOT NULL, \n" +
                    "     idprotocoloitens INT NOT NULL, \n" +
                    "     idtipodoc        INT, \n" +
                    "     nomeprotocolo    TEXT, \n" +
                    "     observacao       TEXT, \n" +
                    "     CONSTRAINT [] PRIMARY KEY ([idprotocoloitens]) \n" +
                    "  ); ");

            String[] sqlComands = sb.toString().split(";");

            for (int i = 0; i < sqlComands.length - 1; i++) {
                db.execSQL(sqlComands[i].toLowerCase());
            }

        } catch (SQLException e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("");

            String[] sqlComands = sb.toString().toLowerCase().split(";");

            for (int i = 0; i < sqlComands.length - 1; i++) {
                db.execSQL(sqlComands[i].toLowerCase());
            }

        } catch (SQLException e) {

        }
    }
}
