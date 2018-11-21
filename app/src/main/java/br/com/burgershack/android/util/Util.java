package br.com.burgershack.android.util;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.IOException;
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

    public static Drawable blobToDrawble(byte[] imagem) {
        Bitmap decodedByte = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        return new BitmapDrawable(Resources.getSystem(), decodedByte);
    }

}
