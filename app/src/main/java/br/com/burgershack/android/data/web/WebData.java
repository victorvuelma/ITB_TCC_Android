package br.com.burgershack.android.data.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import br.com.burgershack.android.BurgerShackApp;
import br.com.burgershack.android.R;
import br.com.burgershack.android.activity.BookingNewActivity;
import br.com.burgershack.android.activity.BookingsActivity;
import br.com.burgershack.android.activity.MenuActivity;
import br.com.burgershack.android.activity.SplashActivity;
import br.com.burgershack.android.data.local.LocalData;

public class WebData {

    public void download(final Activity activity) {
        WebDataRequest produtosRequest = new WebDataRequest(activity) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null) {
                    if (!result.isEmpty()) {
                        BurgerShackApp.DATA_LOCAL.produtosLimpar();
                        for (String produto : result.split("(\\$)")) {
                            String[] produtoDados = produto.split("&");
                            int codigo = Integer.parseInt(produtoDados[0]);
                            String nome = produtoDados[1];
                            String descricao = produtoDados[2];
                            double valor = Double.parseDouble(produtoDados[3].replace(',', '.'));
                            int tipo = Integer.parseInt(produtoDados[4]);
                            byte[] imagem = Base64.decode(produtoDados[5], Base64.DEFAULT);
                            BurgerShackApp.DATA_LOCAL.produtosInserir(codigo, nome, descricao, valor, tipo, imagem);
                        }
                    }
                } else {
                    Toast.makeText(activity, R.string.error_download, Toast.LENGTH_LONG).show();
                }

                if (BurgerShackApp.DATA_LOCAL.clienteLogado()) {
                    downloadReservas(activity);
                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent menuIntent = new Intent(activity, MenuActivity.class);
                            activity.startActivity(menuIntent);

                            activity.finish();
                        }
                    }, 3000);
                }
            }
        };

        produtosRequest.execute(BurgerShackApp.URL_API + "?action=produtos");
    }

    public void downloadReservas(final Activity activity) {
        WebDataRequest reservasRequest = new WebDataRequest(activity) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null) {
                    if (!result.isEmpty()) {
                        BurgerShackApp.DATA_LOCAL.reservasLimpar();
                        for (String reserva : result.split("(\\$)")) {
                            String[] reservaData = reserva.split("&");
                            int codigo = Integer.parseInt(reservaData[0]);
                            long data = Long.parseLong(reservaData[1]);
                            int lugares = Integer.parseInt(reservaData[2]);
                            String informacoes = reservaData[3];
                            String situacao = reservaData[4];
                            BurgerShackApp.DATA_LOCAL.reservasInserir(codigo, data, lugares, informacoes, situacao);
                        }
                    }
                } else {
                    Toast.makeText(activity, R.string.error_download, Toast.LENGTH_LONG).show();
                }

                if (activity instanceof SplashActivity) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent menuIntent = new Intent(activity, MenuActivity.class);
                            activity.startActivity(menuIntent);

                            activity.finish();
                        }
                    }, 3000);
                } else if(activity instanceof BookingNewActivity){
                    Intent bookingsIntent = new Intent(activity, BookingsActivity.class);
                    activity.startActivity(bookingsIntent);

                    activity.finish();
                }
            }
        };

        reservasRequest.execute(BurgerShackApp.URL_API + "?action=reservas&cliente=" + BurgerShackApp.DATA_LOCAL.clienteObter(LocalData.CLIENTE_COD));
    }

}
