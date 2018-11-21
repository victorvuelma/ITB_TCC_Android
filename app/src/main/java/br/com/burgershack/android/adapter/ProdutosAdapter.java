package br.com.burgershack.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.object.Produto;
import br.com.burgershack.android.util.Util;

public class ProdutosAdapter extends ArrayAdapter<Produto> {

    public ProdutosAdapter(@NonNull Context context, @NonNull List<Produto> produtos) {
        super(context, -1, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.products_row_layout, null);

        ImageView imgFoto = (ImageView) rowView.findViewById(R.id.imgProductRowPhoto);
        TextView txtNome = (TextView) rowView.findViewById(R.id.txtProductRowName);
        TextView txtDescricao = (TextView) rowView.findViewById(R.id.txtProductRowDescription);
        TextView txtValor = (TextView) rowView.findViewById(R.id.txtProductRowValue);

        Produto produto = getItem(position);

        imgFoto.setImageDrawable(Util.blobToDrawble(produto.getImagem()));
        txtNome.setText(produto.getNome());
        txtDescricao.setText(produto.getDescricao());
        txtValor.setText(BurgerShackApp.CURRENCY_FORMAT.format(produto.getValor()));

        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getCodigo();
    }

}
