package br.com.burgershack.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.object.Produto;
import br.com.burgershack.android.util.Util;

public class ProductActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);

        int codigo = getIntent().getExtras().getInt("codigo");
        Produto produto = BurgerShackApp.DATA_LOCAL.produtoObter(codigo);

        if (produto != null) {
            TextView txtNome = (TextView) findViewById(R.id.txtProductName);
            TextView txtValor = (TextView) findViewById(R.id.txtProductValue);
            TextView txtDescricao = (TextView) findViewById(R.id.txtProductDescription);
            ImageView imgProduto = (ImageView) findViewById(R.id.imgProductPhoto);

            txtNome.setText(produto.getNome());
            txtValor.setText(BurgerShackApp.CURRENCY_FORMAT.format(produto.getValor()));
            txtDescricao.setText(produto.getDescricao().replace(".", "\n"));
            imgProduto.setImageDrawable(Util.blobToDrawble(produto.getImagem()));
        }
    }

    public void productBack(View v) {
        finish();
    }

}
