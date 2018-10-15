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
import br.com.burgershack.android.object.Booking;
import br.com.burgershack.android.object.Product;

public class BookingsAdapter extends ArrayAdapter<Booking> {


    public BookingsAdapter(@NonNull Context context, @NonNull List<Booking> products) {
        super(context, -1, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.bookings_row_layout, null);

        TextView rowDate = (TextView) rowView.findViewById(R.id.txtBookingRowDate);
        TextView rowPeople = (TextView) rowView.findViewById(R.id.txtBookingRowPeople);
        TextView rowInformation = (TextView) rowView.findViewById(R.id.txtProductRowValue);

        Booking booking = getItem(position);

        StringBuilder peopleBuilder = new StringBuilder();
        peopleBuilder.append(booking.getPessoas());
        peopleBuilder.append(" ");
        peopleBuilder.append((booking.getPessoas() > 1 ? parent.getResources().getString(R.string.people) : parent.getResources().getString(R.string.person)));


        rowDate.setText(BurgerShackApp.DATE_FORMAT.format(booking.getData()));
        rowPeople.setText(peopleBuilder.toString());
        rowInformation.setText(booking.getInformacoes());

        return rowView;
    }
}
