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
import br.com.burgershack.android.object.Product;

public class ProductsAdapter extends ArrayAdapter<Product> {


    public ProductsAdapter(@NonNull Context context, @NonNull List<Product> products) {
        super(context, -1, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.products_row_layout, null);

        ImageView rowPhoto = (ImageView) rowView.findViewById(R.id.imgProductRowPhoto);
        TextView rowName = (TextView) rowView.findViewById(R.id.txtProductRowName);
        TextView rowDescription = (TextView) rowView.findViewById(R.id.txtProductRowDescription);
        TextView rowValue = (TextView) rowView.findViewById(R.id.txtProductRowValue);

        Product product = getItem(position);

        rowName.setText(product.getNome());
        rowDescription.setText(product.getDescricao());
        rowValue.setText(BurgerShackApp.CURRENCY_FORMAT.format(product.getValor()));

        return rowView;
    }
}
