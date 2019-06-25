package br.com.fernandodutra.prj_01_senddocs.dao.departamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.model.departamento.Departamento;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class DepartamentoDAO extends DAO {

    public static final String TABELA = "tb_departamento";
    public static final String IDDEPARTAMENTO = "iddepartamento";
    public static final String NOME = "nome";
    public static final String RESPONSAVEL = "responsavel";
    public static final String TELEFONE = "telefone";
    public static final String CELULAR = "celular";
    public static final String OBSERVACAO = "observacao";

    public DepartamentoDAO(Context context) {
        super(context);
    }

    public void insertDepartamento(Departamento departamento) {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDDEPARTAMENTO, departamento.getIdDepartamento());
            cv.put(NOME, departamento.getNome());
            cv.put(RESPONSAVEL, departamento.getResponsavel());
            cv.put(TELEFONE, departamento.getTelefone());
            cv.put(CELULAR, departamento.getCelular());
            cv.put(OBSERVACAO, departamento.getObservacao());

            db.insert(TABELA, null, cv);
        } finally {
            closeDataBase();
        }
    }

    public void updateDepartamento(Departamento departamento) {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            String filter = " iddepartamento = ?";
            String[] arguments = {String.valueOf(departamento.getIdDepartamento())};
            //
            cv.put(NOME, departamento.getNome());
            cv.put(RESPONSAVEL, departamento.getResponsavel());
            cv.put(TELEFONE, departamento.getTelefone());
            cv.put(CELULAR, departamento.getCelular());
            cv.put(OBSERVACAO, departamento.getObservacao());
            //
            db.update(TABELA, cv, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    public void deleteDepartamento(Departamento departamento) {
        openDataBase();
        try {
            String filter = " iddepartamento = ?";
            String[] arguments = {String.valueOf(departamento.getIdDepartamento())};
            //
            db.delete(TABELA, filter, arguments);

        } finally {
            closeDataBase();
        }
    }

    public void findDepartamentoByID(long idDepartamento) {
        openDataBase();
        //
        Cursor cursor = null;
        //
        Departamento dAux = null;
        //
        try {
            String sqlQuery = " select * from tb_departamento where iddepartamento = ? ";
            String[] arguments = {String.valueOf(idDepartamento)};
            //
            cursor = db.rawQuery(sqlQuery, arguments);
            //
            while (cursor.moveToNext()){
                dAux = new Departamento();
                dAux.setIdDepartamento(cursor.getLong(cursor.getColumnIndex(IDDEPARTAMENTO)));
                dAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                dAux.setTelefone(cursor.getString(cursor.getColumnIndex(TELEFONE)));
                dAux.setCelular(cursor.getString(cursor.getColumnIndex(CELULAR)));
                dAux.setObservacao(cursor.getString(cursor.getColumnIndex(OBSERVACAO)));
                dAux.setResponsavel(cursor.getString(cursor.getColumnIndex(RESPONSAVEL)));
            }

            cursor.close();

        } finally {
            closeDataBase();
        }
    }

    public ArrayList<HMAux> findListDepartamento() {
        openDataBase();
        //
        ArrayList<HMAux> departamentos = new ArrayList<>();
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select iddepartamento, nome from tb_departamento ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDDEPARTAMENTO, cursor.getString(cursor.getColumnIndex(IDDEPARTAMENTO)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                departamentos.add(aux);
            }

            cursor.close();

        } finally {
            closeDataBase();
        }
        return null;
    }

    public long nextID() {
        long nextIdDepartamento = -1l;
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select ifnull(max(iddepartamento)+1,1) as iddepartamento from tb_departamento; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextIdDepartamento = cursor.getLong(cursor.getColumnIndex("iddepartamento"));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextIdDepartamento;
    }

}
