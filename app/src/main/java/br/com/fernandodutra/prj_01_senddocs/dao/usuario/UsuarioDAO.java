package br.com.fernandodutra.prj_01_senddocs.dao.usuario;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 14/04/2019
 * Time: 15:38
 * Prj_01_SendDocs
 */
public interface UsuarioDAO<T> {

    void insertUsuario(Usuario usuario) throws java.sql.SQLException;

    void updateUsuario(Usuario usuario) throws java.sql.SQLException;

    void deleteUsuario(long idUsuario) throws java.sql.SQLException;

    Usuario findUsuarioById(long idUsuario) throws java.sql.SQLException;

    ArrayList<HMAux> findListUsuario() throws java.sql.SQLException;

    ArrayList<HMAux> findListUsuario(HMAux filter, HMAux order) throws java.sql.SQLException;

    long nextID() throws java.sql.SQLException;

}
