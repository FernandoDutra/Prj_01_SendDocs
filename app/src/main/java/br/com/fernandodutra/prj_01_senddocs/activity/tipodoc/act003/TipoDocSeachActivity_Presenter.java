package br.com.fernandodutra.prj_01_senddocs.activity.tipodoc.act003;

import br.com.fernandodutra.prj_01_senddocs.dao.tipodoc.TipoDocDAO;
import br.com.fernandodutra.prj_01_senddocs.model.tipodoc.TipoDoc;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 12:49
 * Prj_01_SendDocs
 */
public class TipoDocSeachActivity_Presenter implements TipoDocSeachActivity_Contract.Presenter {

    private TipoDocSeachActivity_Contract.View view;
    private TipoDocDAO<TipoDoc> tipoDocDAO;

    public TipoDocSeachActivity_Presenter(TipoDocSeachActivity_Contract.View view, TipoDocDAO<TipoDoc> tipoDocDAO) {
        this.view = view;
        this.tipoDocDAO = tipoDocDAO;
    }


}
