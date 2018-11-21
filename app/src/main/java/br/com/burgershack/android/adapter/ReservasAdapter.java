package br.com.burgershack.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.object.Reserva;

public class ReservasAdapter extends ArrayAdapter<Reserva> {

    public ReservasAdapter(@NonNull Context context, @NonNull List<Reserva> products) {
        super(context, -1, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.bookings_row_layout, null);

        TextView rowDate = (TextView) rowView.findViewById(R.id.txtBookingRowDate);
        TextView rowPeople = (TextView) rowView.findViewById(R.id.txtBookingRowPeople);
        TextView rowInformation = (TextView) rowView.findViewById(R.id.txtBookingRowInformation);
        TextView rowStatus = (TextView) rowView.findViewById(R.id.txtBookingRowStatus);

        Reserva reserva = getItem(position);

        StringBuilder peopleBuilder = new StringBuilder();
        peopleBuilder.append(reserva.getLugares());
        peopleBuilder.append(" ");
        peopleBuilder.append((reserva.getLugares() > 1 ? parent.getResources().getString(R.string.people) : parent.getResources().getString(R.string.person)));

        rowDate.setText(BurgerShackApp.DATE_FORMAT.format(reserva.getData()));
        rowPeople.setText(peopleBuilder.toString());
        rowInformation.setText(reserva.getInformacoes());
        rowStatus.setText(reserva.getSituacao().toString());

        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getCodigo();
    }

}
