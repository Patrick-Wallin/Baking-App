package com.example.android.bakingapp.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.example.android.bakingapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by piwal on 5/13/2017.
 */

public class NetworkUtils {
    private Context mContext;

    public NetworkUtils(Context context) {
        mContext = context;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void showAlertMessageAboutNoInternetConnection() {
        new AlertDialog.Builder(mContext)
                .setTitle(mContext.getString(R.string.no_internet_connection_title))
                .setMessage(mContext.getString(R.string.no_internet_connection_message))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public URL buildUrl() {
        //https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
        //http://go.udacity.com/android-baking-app-json
        Uri builtUri = Uri
                .parse(mContext.getString(R.string.baking_json_url))
                .buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
