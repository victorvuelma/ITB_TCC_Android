package br.com.burgershack.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.data.local.LocalData;
import br.com.burgershack.android.data.web.WebDataRequest;
import br.com.burgershack.android.util.Mask;

public class BookingNewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_new_activity);

        EditText edtCpf = (EditText) findViewById(R.id.edtBookingNewCpf);
        Mask.insert(Mask.MaskType.CPF, edtCpf);
    }

    public void bookingNewCreate(View v) {
        EditText edtCpf = (EditText) findViewById(R.id.edtBookingNewCpf);
        EditText edtDate = (EditText) findViewById(R.id.edtBookingNewDate);
        EditText edtHour = (EditText) findViewById(R.id.edtBookingNewHour);
        EditText edtPeople = (EditText) findViewById(R.id.edtBookingNewPeople);
        EditText edtInfo = (EditText) findViewById(R.id.edtBookingNewInfo);

        if (edtCpf.getText().toString().isEmpty()) {
            edtCpf.setError("É necessário preeencher este campo");
        } else if (edtCpf.getText().toString().length() != 14) {
            edtCpf.setError("É necessário informar um CPF.");
        }

        if (edtDate.getText().toString().isEmpty()) {
            edtDate.setError("É necessário preeencher este campo");
        }

        if (edtHour.getText().toString().isEmpty()) {
            edtHour.setError("É necessário preeencher este campo");
        }

        if (edtPeople.getText().toString().isEmpty()) {
            edtPeople.setError("É necessário preeencher este campo");
        }

        if (edtCpf.getError() == null && edtDate.getError() == null && edtHour.getError() == null &&
                edtPeople.getError() == null && edtInfo.getError() == null) {


            WebDataRequest loginRequest = new WebDataRequest(this) {
                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);
                    if (result.startsWith("1")) {
                        Toast.makeText(BookingNewActivity.this, result.substring(1), Toast.LENGTH_LONG).show();

                        //DEPOIS DE FAZER A RESERVA...
                        BurgerShackApp.DATA_WEB.downloadReservas(BookingNewActivity.this);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BookingNewActivity.this);
                        builder.setMessage(result.substring(1));
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                }
            };
            loginRequest.execute(BurgerShackApp.URL_API + "?action=novareserva"
                    + "&cliente=" + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_COD)
                    + "&cpf=" + edtCpf.getText().toString()
                    + "&data=" + edtDate.getText().toString()
                    + "&hora=" + edtHour.getText().toString()
                    + "&lugares=" + edtPeople.getText().toString()
                    + "&info=" + edtInfo.getText().toString());
        }
    }

    public void bookingNewBack(View v) {
        finish();
    }

}
