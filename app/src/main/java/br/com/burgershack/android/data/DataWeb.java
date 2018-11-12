package br.com.burgershack.android.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.activity.MenuActivity;

public class DataWeb {

    private Activity activity;

    public DataWeb(Activity activity) {
        this.activity = activity;
    }

    public void download() {
        DataRequest produtosRequest = new DataRequest() {
            @Override
            protected void onPostExecute(String s) {
                if (s != null) {
                    if (!s.isEmpty()) {
                        BurgerShackApp.DATA_LOCAL.produtosLimpar();
                        for (String produto : s.split("(\\$)")) {
                            Toast.makeText(activity.getBaseContext(), produto, Toast.LENGTH_LONG).show();
                            String[] produtoDados = produto.split("&");
                            int codigo = Integer.parseInt(produtoDados[0]);
                            String nome = produtoDados[1];
                            String descricao = produtoDados[2];
                            double valor = Double.parseDouble(produtoDados[3].replace(',', '.'));
                            int tipo = Integer.parseInt(produtoDados[4]);
                            Toast.makeText(activity.getBaseContext(), "Código: " + codigo + "\nNome: " + nome + "\nDescrição: " + descricao + "\n Valor: " + valor + "\n Tipo: " + tipo, Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(activity.getBaseContext(), R.string.error_download, Toast.LENGTH_LONG).show();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent menuIntent = new Intent(activity.getBaseContext(), MenuActivity.class);
                        activity.startActivity(menuIntent);

                        activity.finish();
                    }
                }, 3000);
            }
        };

        produtosRequest.execute(BurgerShackApp.URL_API + "?action=produtos");
    }

}
