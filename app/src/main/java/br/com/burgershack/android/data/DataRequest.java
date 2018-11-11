package br.com.burgershack.android.data;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.IOException;

import br.com.burgershack.android.util.Util;

public class DataRequest extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            return Util.getURLContent(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
