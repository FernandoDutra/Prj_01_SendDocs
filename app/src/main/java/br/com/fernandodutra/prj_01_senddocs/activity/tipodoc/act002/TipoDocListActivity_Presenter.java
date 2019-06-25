package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act002;


import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAO;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 12:21
 * Prj_01_SendDocs
 */
public class TipoDocListActivity_Presenter implements TipoDocListActivity_Contract.Presenter {

    private TipoDocListActivity_Contract.View view;
    private TipoDocDAO<TipoDoc> tipoDocDAO;

    public TipoDocListActivity_Presenter(TipoDocListActivity_Contract.View view, TipoDocDAO<TipoDoc> tipoDocDAO) {
        this.view = view;
        this.tipoDocDAO = tipoDocDAO;
    }

    @Override
    public ArrayList<HMAux> buscarTipoDoc() {
        try {
            return this.tipoDocDAO.findListTipoDoc();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HMAux> buscarTipoDoc(HMAux filter, HMAux order) {
        try {
            return this.tipoDocDAO.findListTipoDoc(filter,order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int excluirTipoDoc(long idTipoDoc) {
        try {
            this.tipoDocDAO.deleteTipoDoc(idTipoDoc);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
