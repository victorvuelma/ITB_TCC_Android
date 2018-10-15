package br.com.burgershack.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.burgershack.android.R;

public class AccountActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
    }

    public void accountBack(View v){
        finish();
    }

    public void accountBookings(View v){
        Intent it = new Intent(getBaseContext(), BookingsActivity.class);
        startActivity(it);
    }

    public void accountNewBooking(View v){
        Intent it = new Intent(getBaseContext(), BookingNewActivity.class);
        startActivity(it);
    }

    public void accountLogout(View v){
        finish();
        Intent it = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(it);
    }


}
