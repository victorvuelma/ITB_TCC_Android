package br.com.burgershack.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        BurgerShackApp.init(this);
    }
}
