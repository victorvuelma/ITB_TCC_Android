package br.com.burgershack.android.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Util {

    /*
     * From Android Developers
     * https://developer.android.com/guide/components/intents-common#ViewUrl
     */
    public static void openWebPage(Activity activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

    public static String getURLContent(String url){
        StringBuilder responseBuilder = new StringBuilder();
        try {
            URL connURL = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connURL.openConnection().getInputStream()));
            String line = null;
            while((line = reader.readLine()) != null){
                responseBuilder.append(line);
            }
            reader.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

}
