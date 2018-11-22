package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.adapter.ProdutosAdapter;
import br.com.burgershack.android.object.Produto;

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
            List<Produto> produtos = BurgerShackApp.DATA_LOCAL.produtosObter(type);
            produtosExibir(produtos);
        } catch (NullPointerException ex) {
            finish();
        }
    }

    public void productsBack(View v) {
        finish();
    }

    public void produtosExibir(List<Produto> produtos) {
        ListView productsList = (ListView) findViewById(R.id.ltvProducts);

        ProdutosAdapter produtosAdapter = new ProdutosAdapter(this, produtos);
        productsList.setAdapter(produtosAdapter);

        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productClick(parent, view, position, id);
            }
        });
    }

    public void productClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(getBaseContext(), ProductActivity.class);
        it.putExtra("codigo", (int) id);
        startActivity(it);
    }

}
