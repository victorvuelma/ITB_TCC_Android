package br.com.burgershack.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.data.local.LocalData;
import br.com.burgershack.android.data.web.WebDataRequest;
import br.com.burgershack.android.util.Mask;

public class BookingNewActivity extends Activity {

    private Calendar bookingCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_new_activity);

        EditText edtCpf = (EditText) findViewById(R.id.edtBookingNewCpf);
        Mask.insert(Mask.MaskType.CPF, edtCpf);

        EditText edtDate = (EditText) findViewById(R.id.edtBookingNewDate);
        Mask.insert(Mask.MaskType.DATE, edtDate);

        EditText edtHour = (EditText) findViewById(R.id.edtBookingNewHour);
        Mask.insert(Mask.MaskType.HOUR, edtHour);

        bookingCalendar = Calendar.getInstance();
        bookingCalendar.add(Calendar.DAY_OF_MONTH, 1);
        bookingCalendar.set(Calendar.HOUR_OF_DAY, 20);
        bookingCalendar.set(Calendar.MINUTE, 0);

        updateDate();
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
                    if (result != null && !result.isEmpty()) {
                        if (result.startsWith("1")) {
                            Toast.makeText(BookingNewActivity.this, result.substring(1), Toast.LENGTH_LONG).show();

                            //DEPOIS DE FAZER A RESERVA...
                            BurgerShackApp.DATA_WEB.downloadReservas(BookingNewActivity.this);
                        } else {
                            result = result.substring(1);
                            result = result.replace(".", ".\n");
                            AlertDialog.Builder builder = new AlertDialog.Builder(BookingNewActivity.this);
                            builder.setMessage(result);
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();
                        }
                    }
                }
            };
            loginRequest.execute(BurgerShackApp.URL_API + "?action=novareserva"
                    + "&cliente=" + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_COD)
                    + "&cpf=" + edtCpf.getText().toString()
                    + "&data=" + edtDate.getText().toString()
                    + "&hora=" + edtHour.getText().toString()
                    + "&lugares=" + edtPeople.getText().toString()
                    + "&info=" + Base64.encodeToString(edtInfo.getText().toString().getBytes(), Base64.DEFAULT));
        }
    }

    public void bookingNewBack(View v) {
        finish();
    }

    public void bookingDate(View v) {
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                bookingCalendar.set(Calendar.YEAR, year);
                bookingCalendar.set(Calendar.MONTH, month);
                bookingCalendar.set(Calendar.DAY_OF_MONTH, day);

                updateDate();
            }
        }, bookingCalendar.get(Calendar.YEAR), bookingCalendar.get(Calendar.MONTH), bookingCalendar.get(Calendar.DAY_OF_MONTH));
datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePicker.show();
    }

    public void bookingHour(View v) {
        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                bookingCalendar.set(Calendar.HOUR_OF_DAY, hour);
                bookingCalendar.set(Calendar.MINUTE, minute);

               updateDate();
            }
        }, bookingCalendar.get(Calendar.HOUR_OF_DAY), bookingCalendar.get(Calendar.MINUTE), true);
        timePicker.show();
    }

    private void updateDate(){
        EditText edtHour = (EditText) findViewById(R.id.edtBookingNewHour);
        edtHour.setText(BurgerShackApp.TIME_FORMAT.format(bookingCalendar.getTime() ));

        EditText edtDate = (EditText) findViewById(R.id.edtBookingNewDate);
        edtDate.setText(BurgerShackApp.DATE_FORMAT.format(bookingCalendar.getTime()));
    }


}
