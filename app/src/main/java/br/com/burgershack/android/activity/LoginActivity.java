package br.com.burgershack.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.data.local.LocalData;
import br.com.burgershack.android.data.web.WebDataRequest;
import br.com.burgershack.android.util.Mask;
import br.com.burgershack.android.util.Util;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText edtCPF = (EditText) findViewById(R.id.edtLoginCpf);
        Mask.insert(Mask.MaskType.CPF, edtCPF);
    }

    public void loginLogin(View v) {

        EditText edtCpf = (EditText) findViewById(R.id.edtLoginCpf);
        EditText edtPassword = (EditText) findViewById(R.id.edtLoginPassword);

        if (edtCpf.getText().toString().isEmpty()) {
            edtCpf.setError("É necessário preeencher este campo");
        } else if (edtCpf.getText().toString().length() != 14) {
            edtCpf.setError("É necessário informar um CPF.");
        }

        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("É necessário preeencher este campo");
        }

        if (edtCpf.getError() == null && edtPassword.getError() == null) {
            WebDataRequest loginRequest = new WebDataRequest(this) {
                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);
                    BurgerShackApp.DATA_LOCAL.clienteLimpar();
                    BurgerShackApp.DATA_LOCAL.reservasLimpar();

                    if (result.startsWith("1")) {
                        result = result.substring(1);
                        String[] data = result.split("&");
                        BurgerShackApp.DATA_LOCAL.clienteInserir(LocalData.CLIENTE_COD, data[1]);
                        BurgerShackApp.DATA_LOCAL.clienteInserir(LocalData.CLIENTE_NOME, data[2]);
                        BurgerShackApp.DATA_LOCAL.clienteInserir(LocalData.CLIENTE_CPF, data[3]);
                        BurgerShackApp.DATA_LOCAL.clienteInserir(LocalData.CLIENTE_EMAIL, data[4]);
                        BurgerShackApp.DATA_LOCAL.clienteInserir(LocalData.CLIENTE_CELULAR, data[5]);
                        BurgerShackApp.DATA_LOCAL.clienteInserir(LocalData.CLIENTE_CADASTRO, data[6]);

                        Toast.makeText(LoginActivity.this, data[0], Toast.LENGTH_LONG).show();

                        Intent it = new Intent(LoginActivity.this, AccountActivity.class);
                        startActivity(it);

                        finish();

                        BurgerShackApp.DATA_WEB.downloadReservas(LoginActivity.this);
                    } else {
                        result = result.substring(1);
                        result = result.replace(".", ".\n");
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
            };
            loginRequest.execute(BurgerShackApp.URL_API + "?action=login" +
                    "&cpf=" + edtCpf.getText().toString() +
                    "&senha=" + Base64.encodeToString(edtPassword.getText().toString().getBytes(), Base64.DEFAULT));
        }
    }

    public void loginRegister(View v) {
        Util.openWebPage(this, BurgerShackApp.URL_CADASTRO);
    }

    public void loginBack(View v) {
        finish();
    }

}
