package br.com.fernandodutra.prj_01_senddocs.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import br.com.fernandodutra.prj_01_senddocs.R;

public class ToolBox {


    public static final String retiraPontos(String valor) {
        return "";
    }

    public static final boolean validaCpfCnpj(String cpfCnpj) {
        return false;
    }

    public static String converteDateToStr(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        String strData = sdf.format(data);
        return strData;
    }

    public static Date converteStrToDate(String strData) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        Date data = null;
        try {
            data = sdf.parse(strData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void alertMessage(Context context, String titulo, String mensagem, int icon, boolean tipo, String textoPositivo, DialogInterface.OnClickListener ActionPositivo, String textoNegativo, DialogInterface.OnClickListener ActionNegativo) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle(titulo);
        alerta.setMessage(mensagem);
        if (icon > 0) {
            alerta.setIcon(icon);
        } else {
            alerta.setIcon(R.mipmap.ic_launcher);
        }
        alerta.setCancelable(false);

        alerta.setPositiveButton(textoPositivo, ActionPositivo);

        if (tipo) {
            alerta.setNegativeButton(textoNegativo, ActionNegativo);
        }
        //
        alerta.show();
    }

    public static void exibirMensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

    public static HMAux carregaHashMap(HashMap<String, String> hashMap) {
        HMAux hmAux = new HMAux();

        for (HMAux.Entry<String, String> hashMapAux : hashMap.entrySet()) {
            hmAux.put(hashMapAux.getKey(), hashMapAux.getValue());
        }
        return hmAux;
    }

    public static int getIndexByString(Spinner spinner, String string) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
