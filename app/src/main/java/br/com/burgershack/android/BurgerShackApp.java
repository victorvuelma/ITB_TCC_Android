package br.com.burgershack.android;

import android.app.Activity;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.burgershack.android.data.DataLocal;
import br.com.burgershack.android.data.DataWeb;

public class BurgerShackApp {

    public static final String DB_NAME = "BurgerShack";

    public static final String URL_PRINCIPAL = "http://192.168.0.11:51061/";
    public static final String URL_CADASTRO = URL_PRINCIPAL + "cadastro.cshtml";
    public static final String URL_API = URL_PRINCIPAL + "api.cshtml";

    public static Locale LOCALE = new Locale("pt", "BR");
    public static NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(LOCALE);
    public static DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, LOCALE);

    public static DataLocal DATA_LOCAL;
    public static DataWeb DATA_WEB;

    public static void init(Activity activity) {
        DATA_LOCAL = new DataLocal(activity);
        DATA_WEB = new DataWeb(activity);

        DATA_LOCAL.setup();
        DATA_WEB.download();
    }

}
