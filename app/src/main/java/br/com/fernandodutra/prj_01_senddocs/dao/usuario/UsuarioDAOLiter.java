package br.com.fernandodutra.prj_01_senddocs.dao.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;
import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class UsuarioDAOLiter extends DAO implements UsuarioDAO<Usuario> {

    public static final String TABELA = "tb_usuario";
    public static final String IDUSUARIO = "idusuario";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String NIVELACESSO = "nivelacesso";

    public UsuarioDAOLiter(Context context) {
        super(context);
    }

    @Override
    public void insertUsuario(Usuario usuario) throws SQLException {
        openDataBase();
        try {
            ContentValues cv = new ContentValues();
            //
            cv.put(IDUSUARIO, usuario.getIdusuario());
            cv.put(NOME, usuario.getNome());
            cv.put(EMAIL, usuario.getEmail());
            cv.put(SENHA, usuario.getSenha());
            cv.put(NIVELACESSO, usuario.getNivelacesso());
            //
            db.insert(TABELA, null, cv);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) throws java.sql.SQLException {
        openDataBase();
        try {
            //
            String filter = " idusuario = ?";
            String[] arguments = {String.valueOf(usuario.getIdusuario())};
            //
            ContentValues cv = new ContentValues();
            //
            cv.put(NOME, usuario.getNome());
            cv.put(EMAIL, usuario.getEmail());
            cv.put(SENHA, usuario.getSenha());
            cv.put(NIVELACESSO, usuario.getNivelacesso());
            //
            db.update(TABELA, cv, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public void deleteUsuario(long idUsuario) throws java.sql.SQLException {
        openDataBase();
        try {
            //
            String filter = " idusuario = ?";
            String[] arguments = {String.valueOf(idUsuario)};
            //
            db.delete(TABELA, filter, arguments);
        } finally {
            closeDataBase();
        }
    }

    @Override
    public Usuario findUsuarioById(long idUsuario) throws java.sql.SQLException {
        openDataBase();
        //
        Cursor cursor = null;
        //
        Usuario uAux = null;
        try {
            String sqlQuery = " select * from tb_usuario where idusuario = ?";
            String[] arguments = {String.valueOf(idUsuario)};
            //
            cursor = db.rawQuery(sqlQuery, arguments);
            //
            while (cursor.moveToNext()) {
                uAux = new Usuario();
                uAux.setIdusuario(cursor.getLong(cursor.getColumnIndex(IDUSUARIO)));
                uAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                uAux.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                uAux.setSenha(cursor.getString(cursor.getColumnIndex(SENHA)));
                uAux.setNivelacesso(cursor.getInt(cursor.getColumnIndex(NIVELACESSO)));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return uAux;
    }

    @Override
    public ArrayList<HMAux> findListUsuario() throws java.sql.SQLException {
        openDataBase();
        //
        ArrayList<HMAux> usuarios = new ArrayList<>();
        //
        Cursor cursor = null;
        //
        try {
            String sqlQuery = " select idusuario,nome from tb_usuario ";
            //
            cursor = db.rawQuery(sqlQuery, null);
            //
            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDUSUARIO, cursor.getString(cursor.getColumnIndex(IDUSUARIO)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                usuarios.add(aux);
            }
            //
            cursor.close();
            //
        } finally {
            closeDataBase();
        }
        return usuarios;
    }

    @Override
    public ArrayList<HMAux> findListUsuario(HMAux filter, HMAux order) throws java.sql.SQLException {
        openDataBase();
        //
        ArrayList<HMAux> usuarios = new ArrayList<>();
        //
        openDataBase();
        //
        Cursor cursor = null;
        //
        try {
            StringBuilder sqlQuery = new StringBuilder();

            sqlQuery.append(" select idusuario, nome from tb_usuario where idusuario is not null ");
            String[] argumentos = new String[filter.size()];
            int i = 0;

            for (HMAux.Entry<String, String> entrada : filter.entrySet()) {
                sqlQuery.append(" and (" + entrada.getKey() + " like ? ) ");
                argumentos[i] = "%" + String.valueOf(entrada.getValue()) + "%";
                i++;
            }

            if (order.size() > 0) {
                sqlQuery.append(" order by ");

                i = 0;
                String aux = "";
                for (HMAux.Entry<String, String> entrada : order.entrySet()) {
                    i++;
                    aux = (i == order.size()) ? "" : ", ";
                    sqlQuery.append(entrada.getValue() + aux);
                }
            } else {
                sqlQuery.append(" order by nome ");
            }

            cursor = db.rawQuery(sqlQuery.toString(), argumentos);

            while (cursor.moveToNext()) {
                HMAux aux = new HMAux();
                //
                aux.put(IDUSUARIO, cursor.getString(cursor.getColumnIndex(IDUSUARIO)));
                //
                aux.put(NOME, cursor.getString(cursor.getColumnIndex(NOME)));
                //
                usuarios.add(aux);
            }

            cursor.close();

        } finally {
            closeDataBase();
        }

        return usuarios;
    }

    

    @Override
    public long nextID() throws java.sql.SQLException {
        long nextIdUsuario = -1;
        //
        openDataBase();
        //
        Cursor cursor = null;
        try {
            String sqlQuery = " select ifnull(max(idusuario)+1,1) as idusuario from tb_usuario; ";

            cursor = db.rawQuery(sqlQuery, null);

            while (cursor.moveToNext()) {
                nextIdUsuario = cursor.getLong(cursor.getColumnIndex(IDUSUARIO));
            }

            cursor.close();
        } finally {
            closeDataBase();
        }
        return nextIdUsuario;
    }

}
