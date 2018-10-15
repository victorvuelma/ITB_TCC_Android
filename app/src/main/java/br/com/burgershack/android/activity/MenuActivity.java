package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        BurgerShackApp.DATA_LOCAL.setup(this);
        BurgerShackApp.DATA_WEB.download();

    }

    public void menuHamburgers(View v) {
        Intent it = new Intent(getBaseContext(), ProductsActivity.class);
        it.putExtra("title", R.string.product_hamburgers);
        it.putExtra("type", 1);
        startActivity(it);
    }

    public void menuDrinks(View v) {
        Intent it = new Intent(getBaseContext(), ProductsActivity.class);
        it.putExtra("title", R.string.product_drinks);
        it.putExtra("type", 3);
        startActivity(it);
    }

    public void menuAccompaniments(View v){
        Intent it = new Intent(getBaseContext(), ProductsActivity.class);
        it.putExtra("title", R.string.product_accompaniments);
        it.putExtra("type", 2);
        startActivity(it);
    }

    public void menuDesserts(View v) {
        Intent it = new Intent(getBaseContext(), ProductsActivity.class);
        it.putExtra("title", R.string.product_desserts);
        it.putExtra("type", 4);
        startActivity(it);
    }

    public void menuAccount(View v) {
        Intent it = new Intent(getBaseContext(), AccountActivity.class);
        startActivity(it);
    }

}
