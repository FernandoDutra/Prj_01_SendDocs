package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act002;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAO;
import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:24
 * Prj_01_SendDocs
 */
public class UsuarioListActivity_Presenter implements UsuarioListActivity_Contract.Presenter {

    private UsuarioListActivity_Contract.View view;
    private UsuarioDAO<Usuario> usuarioDAO;

    public UsuarioListActivity_Presenter(UsuarioListActivity_Contract.View view, UsuarioDAO<Usuario> usuarioDAO) {
        this.view = view;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public ArrayList<HMAux> buscarUsuario() {
        try {
            return usuarioDAO.findListUsuario();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HMAux> buscarUsuario(HMAux filter, HMAux order) {
        try {
            return usuarioDAO.findListUsuario(filter, order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int excluirUsuario(long idUsuario) {
        try {
            usuarioDAO.deleteUsuario(idUsuario);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
