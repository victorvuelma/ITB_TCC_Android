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

public class ProductActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_activity);

        Intent it = getIntent();
        int title = Integer.parseInt(it.getExtras().get("title").toString());

        TextView txtTitle = (TextView) findViewById(R.id.txtProductsTitle);
        txtTitle.setText(title);
    }

    public void productBack(View v){
        finish();
    }

}
