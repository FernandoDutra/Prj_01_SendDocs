package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act001;

import java.sql.SQLException;

import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAO;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 10/04/2019
 * Time: 21:13
 * Prj_01_SendDocs
 */
public class TipoDocActivity_Presenter implements TipoDocActivity_Contract.Presenter {

    private TipoDocActivity_Contract.View view;
    private TipoDocDAO<TipoDoc> tipoDocDAO;

    public TipoDocActivity_Presenter(TipoDocActivity_Contract.View view, TipoDocDAO<TipoDoc> tipoDocDAO) {
        this.view = view;
        this.tipoDocDAO = tipoDocDAO;
    }

    @Override
    public long nextID() {
        try {
            return tipoDocDAO.nextID();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    @Override
    public TipoDoc findTipoDocByID(long idtipodoc) {
        try {
            return tipoDocDAO.findTipoDocByID(idtipodoc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void salvar(TipoDoc tipoDoc) {
        try {
            tipoDocDAO.insertTipoDoc(tipoDoc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(TipoDoc tipoDoc) {
        try {
            tipoDocDAO.updateTipoDoc(tipoDoc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(long idTipoDoc) {
        try {
            tipoDocDAO.deleteTipoDoc(idTipoDoc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
