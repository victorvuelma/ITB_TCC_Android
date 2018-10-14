package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.object.Product;
import br.com.burgershack.android.util.ProductsAdapter;

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

            int tipo = it.getExtras().getInt("tipo");
            List<Product> products = BurgerShackApp.DATA_LOCAL.getProducts(tipo);
            showProducts(products);
        } catch (NullPointerException ex){
            finish();
        }
    }

    public void productMenu(View v) {
        finish();
    }

    public void showProducts(List<Product> products){
        ListView productsList = (ListView) findViewById(R.id.ltvProducts);

        ProductsAdapter productsAdapter = new ProductsAdapter(this, products);
        productsList.setAdapter(productsAdapter);
    }


}
