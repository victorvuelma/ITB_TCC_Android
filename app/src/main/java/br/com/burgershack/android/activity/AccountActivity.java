package br.com.burgershack.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.data.local.LocalData;

public class AccountActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        if (BurgerShackApp.DATA_LOCAL.clienteLogado()) {
            String informacoes = "Nome: " + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_NOME);
            informacoes += "\nEmail: " + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_EMAIL);
            informacoes += "\nCPF: " + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_CPF);
            informacoes += "\nCelular: " + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_CELULAR);
            informacoes += "\nCadastro: " + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_CADASTRO);

            TextView txtDetails = (TextView) findViewById(R.id.txtAccountDetails);
            txtDetails.setText(informacoes);
        } else {
            finish();
        }
    }

    public void accountBack(View v) {
        finish();
    }

    public void accountBookings(View v) {
        Intent it = new Intent(getBaseContext(), BookingsActivity.class);
        startActivity(it);
    }

    public void accountNewBooking(View v) {
        Intent it = new Intent(getBaseContext(), BookingNewActivity.class);
        startActivity(it);
    }

    public void accountLogout(View v) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(R.string.account_logout);
        dialogBuilder.setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BurgerShackApp.DATA_LOCAL.clienteLimpar();
                BurgerShackApp.DATA_LOCAL.reservasLimpar();
                dialog.dismiss();
                Intent it = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(it);
                finish();
            }
        });
        dialogBuilder.create().show();
    }


}
