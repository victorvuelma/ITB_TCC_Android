package br.com.burgershack.android.data.web;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import br.com.burgershack.android.R;
import br.com.burgershack.android.util.Util;

public class WebDataRequest extends AsyncTask<String, String, String> {

    private ProgressDialog _pDialog;

    private Context _context;

    public WebDataRequest(Context context) {
        this._context = context;
    }

    @Override
    protected void onPreExecute() {
        _pDialog = new ProgressDialog(_context);
        _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        _pDialog.setMessage(_context.getText(R.string.dialog_connect));
        _pDialog.setIndeterminate(false);
        _pDialog.setCancelable(false);
        _pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return Util.getURLContent(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        _pDialog.dismiss();
    }
}
