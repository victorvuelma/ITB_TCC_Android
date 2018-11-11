package br.com.burgershack.android.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Util {

    /*
     * From Android Developers
     * https://developer.android.com/guide/components/intents-common#ViewUrl
     */
    public static void openWebPage(Activity activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

    public static String getURLContent(String url) throws IOException {
        StringBuilder responseBuilder = new StringBuilder();
            URL connURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();
            Scanner scanner = new Scanner(connURL.openStream());
            while (scanner.hasNext()) {
                responseBuilder.append(scanner.next().replace("%20", " "));
            }
            scanner.close();
            connection.disconnect();
        return responseBuilder.toString();
    }

}
