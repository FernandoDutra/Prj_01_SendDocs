package br.com.fernandodutra.prj_01_senddocs.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.fernandodutra.prj_01_senddocs.utils.Constants;

/**
 * Created by nalmir on 09/02/2019.
 */

public class DTEditText extends android.support.v7.widget.AppCompatEditText implements View.OnClickListener {

    private Context context;

    private SimpleDateFormat dateFormat;

    public DTEditText(Context context) {
        super(context);
        //
        inicializar(context, null);
    }

    public DTEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        inicializar(context, attrs);
    }

    public DTEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
        inicializar(context, attrs);
    }

    private void inicializar(Context context, AttributeSet attrs) {
        this.context = context;
        //
        dateFormat = new SimpleDateFormat(Constants.FR_DT_DC);
        //
        setInputType(InputType.TYPE_NULL);
        //
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String conteudo = getText().toString();
        //
        Calendar mDate = Calendar.getInstance(); // data e hora desse instante
        //
        try {
            mDate.setTime(dateFormat.parse(conteudo));
        } catch (Exception e) {
        }
        //
        int mAno = mDate.get(Calendar.YEAR);
        int mMes = mDate.get(Calendar.MONTH);
        int mDia = mDate.get(Calendar.DAY_OF_MONTH);
        //
        DatePickerDialog mDatePicker = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cDataCapturada = Calendar.getInstance();
                        //
                        cDataCapturada.set(year, month, dayOfMonth);
                        //
                        setText(dateFormat.format(cDataCapturada.getTime()));
                    }
                },
                mAno,
                mMes,
                mDia

        );
        //
        mDatePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar", (DialogInterface.OnClickListener) null);
        mDatePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", mDatePicker);
        //
        mDatePicker.show();
    }
}
