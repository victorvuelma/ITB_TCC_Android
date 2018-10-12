package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.com.burgershack.android.R;

public class ProductsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_activity);

        try {
            Intent it = getIntent();
            int title = it.getExtras().getInt("title");

            TextView txtTitle = (TextView) findViewById(R.id.txtProductsTitle);
            txtTitle.setText(title);
        } catch (NullPointerException ex){
            finish();
        }
    }

    public void productMenu(View v) {
        finish();
    }

}
