package br.com.burgershack.android;

import br.com.burgershack.android.data.DataLocal;
import br.com.burgershack.android.data.DataWeb;

public class BurgerShackApp {

    public static final String DB_NAME = "BurgerShack";

    public static final String URL_PRINCIPAL = "https://www.google.com.br/";
    public static final String URL_CADASTRO = URL_PRINCIPAL + "cadastro.cshtml";
    public static final String URL_API = URL_PRINCIPAL + "api.cshtml";

    public static DataLocal DATA_LOCAL = new DataLocal();
    public static DataWeb DATA_WEB = new DataWeb();

}
