package br.com.burgershack.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.adapter.ProdutosAdapter;
import br.com.burgershack.android.adapter.ReservasAdapter;
import br.com.burgershack.android.object.Produto;
import br.com.burgershack.android.object.Reserva;

public class BookingsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings_activity);

        List<Reserva> reservas = BurgerShackApp.DATA_LOCAL.reservasObter();
        reservasExibir(reservas);

    }

    public void bookingsBack(View v) {
        finish();
    }

    public void reservasExibir(List<Reserva> reservas) {
        ListView reservasList = (ListView) findViewById(R.id.ltvBookings);

        ReservasAdapter reservasAdapter = new ReservasAdapter(this, reservas);
        reservasList.setAdapter(reservasAdapter);
    }

}
