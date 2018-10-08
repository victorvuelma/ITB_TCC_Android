package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import br.com.burgershack.android.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void loginLogin(View v){
        Log.v("ah meu deus", "opa");
        Intent it = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(it);
    }

    public void loginRegister(View v){

    }
}
