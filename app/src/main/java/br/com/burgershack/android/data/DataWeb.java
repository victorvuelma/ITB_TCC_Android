package br.com.burgershack.android.data;

import android.app.Activity;
import android.widget.Toast;

import br.com.burgershack.android.BurgerShackApp;

public class DataWeb {

    private Activity activity;

    private String contentProducts;

    public DataWeb(Activity activity){
        this.activity = activity;
    }

    public void download(){
        DataRequest produtosRequest = new DataRequest(){
            @Override
            protected void onPostExecute(String s) {
                if(s != null && !s.isEmpty()){
                    BurgerShackApp.DATA_LOCAL.produtosLimpar();
                    for(String produto : s.split("$")){
                        String[] produtoDados = produto.split("&");
                        int codigo = Integer.parseInt(produtoDados[0]);
                        String nome = produtoDados[1];
                        Toast.makeText(activity.getBaseContext(), "Código: " + codigo + "\n" + "Nome: " + nome, Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        produtosRequest.execute(BurgerShackApp.URL_API + "?action=produtos");
    }

}
