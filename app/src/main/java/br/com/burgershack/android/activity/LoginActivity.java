package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
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
        Intent it = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(it);
    }

    public void loginRegister(View v) {
        Util.openWebPage(this, BurgerShackApp.URL_CADASTRO);
    }

    public void loginBack(View v) {
        finish();
    }

}
