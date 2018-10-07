package br.com.burgershack.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.burgershack.android.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
    }
}
