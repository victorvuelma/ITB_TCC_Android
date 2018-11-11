package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.object.Product;
import br.com.burgershack.android.adapter.ProductsAdapter;

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

            int type = it.getExtras().getInt("type");
            List<Product> products = BurgerShackApp.DATA_LOCAL.obterProdutos(type);
            products.add(new Product(1, "TESTE", "", "TESTE DE DESCRIÇÃO", 10.50, 1));
            showProducts(products);
        } catch (NullPointerException ex) {
            finish();
        }
    }

    public void productsBack(View v) {
        finish();
    }

    public void showProducts(List<Product> products) {
        ListView productsList = (ListView) findViewById(R.id.ltvProducts);

        ProductsAdapter productsAdapter = new ProductsAdapter(this, products);
        productsList.setAdapter(productsAdapter);
    }


}
