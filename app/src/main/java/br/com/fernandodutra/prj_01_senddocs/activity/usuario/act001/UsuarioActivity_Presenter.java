package br.com.fernandodutra.prj_01_senddocs.activity.usuario.act001;

import java.sql.SQLException;

import br.com.fernandodutra.prj_01_senddocs.dao.usuario.UsuarioDAO;
import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:24
 * Prj_01_SendDocs
 */
public class UsuarioActivity_Presenter implements UsuarioActivity_Contract.Presenter {

    private UsuarioActivity_Contract.View view;
    private UsuarioDAO<Usuario> usuarioDAO;

    public UsuarioActivity_Presenter(UsuarioActivity_Contract.View view, UsuarioDAO<Usuario> usuarioDAO) {
        this.view = view;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public long nextID() {
        try {
            return this.usuarioDAO.nextID();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    @Override
    public Usuario findUsuarioByID(long idUsuario) {
        try {
            return this.usuarioDAO.findUsuarioById(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void salvar(Usuario usuario) {
        try {
            this.usuarioDAO.insertUsuario(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        try {
            this.usuarioDAO.updateUsuario(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(long idUsuario) {
        try {
            this.usuarioDAO.deleteUsuario(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
