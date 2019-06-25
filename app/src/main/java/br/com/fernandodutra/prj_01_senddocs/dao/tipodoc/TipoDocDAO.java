package br.com.fernandodutra.prj_01_senddocs.dao.tipodoc;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 10/04/2019
 * Time: 22:18
 * Prj_01_SendDocs
 */
public interface TipoDocDAO<T> {

    void insertTipoDoc(TipoDoc tipodoc) throws SQLException;

    void updateTipoDoc(TipoDoc tipodoc) throws SQLException;

    void deleteTipoDoc(long idTipoDoc) throws SQLException;

    TipoDoc findTipoDocByID(long idtipodoc) throws SQLException;

    ArrayList<HMAux> findListTipoDoc() throws SQLException;

    ArrayList<HMAux> findListTipoDoc(HMAux filter, HMAux order) throws SQLException;

    long nextID() throws SQLException;

}
