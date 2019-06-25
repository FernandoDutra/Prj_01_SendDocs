package br.com.fernandodutra.prj_01_senddocs.dao.login;

import android.content.Context;
import android.database.Cursor;

import br.com.fernandodutra.prj_01_senddocs.dao.dao.DAO;
import br.com.fernandodutra.prj_01_senddocs.model.login.Login;
import br.com.fernandodutra.prj_01_senddocs.model.usuario.Usuario;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/04/2019
 * Time: 17:07
 * Prj_01_SendDocs
 */
public class LoginDAOLiter extends DAO implements LoginDAO<Usuario> {

    public static final String TABELA = "tb_usuario";
    public static final String IDUSUARIO = "idusuario";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String NIVELACESSO = "nivelacesso";

    public LoginDAOLiter(Context context) {
        super(context);
    }

    @Override
    public Login findLogin(String login, String senha) {
        openDataBase();
        //
        Cursor cursor = null;
        //
        Login uAux = null;
        //
        try {
            String sqlQuery = " select email,senha from tb_usuario ";
            String filter = " email = ?";
            String[] arguments = {String.valueOf(login)};
            //
            cursor = db.rawQuery(sqlQuery, null);
            //
            while (cursor.moveToNext()) {
                if (!senha.equalsIgnoreCase(cursor.getString(cursor.getColumnIndex(SENHA)))) {
                    uAux = new Login();
                    uAux.setIdusuario(cursor.getLong(cursor.getColumnIndex(IDUSUARIO)));
                    uAux.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
                    uAux.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                    uAux.setSenha(cursor.getString(cursor.getColumnIndex(SENHA)));
                    uAux.setNivelacesso(cursor.getInt(cursor.getColumnIndex(NIVELACESSO)));
                }
            }
            //
            cursor.close();
            //
        } finally {
            closeDataBase();
        }
        return uAux;
    }
}
