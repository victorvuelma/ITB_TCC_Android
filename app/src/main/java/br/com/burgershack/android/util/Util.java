package br.com.burgershack.android.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class Util {

    /*
     * From Android Developers
     * https://developer.android.com/guide/components/intents-common#ViewUrl
     */
    public static void openWebPage(Activity activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

}
