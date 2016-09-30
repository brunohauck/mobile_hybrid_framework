package com.mentorandroid.cursoandroid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import com.mentorandroid.cursoandroid.MainActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by p060783 on 16/12/2015.
 */
public class Util {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable();
    }

    public static boolean pingURL_new(String url, int timeout) {
        //url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.
        /*
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            return false;
        }
        */
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return false;
            }else{
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean pingURL(String url, int timeout) {

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static interface MyInterface {
        public void myMethod(boolean result);
    }

    public static class NetworkUtil extends AsyncTask<String, Void, Boolean> {
        private MyInterface mListener;

        String _url;
        public NetworkUtil(String url, MyInterface mListener) {
            _url = url;
            this.mListener  = mListener;
        }


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            Log.i("DEBUG ->", " Entrou 01");
            if (pingURL(_url, 1000)) {
                Log.i("DEBUG ->", " Com internet");
                return true;
            } else {
                Log.i("DEBUG ->", "Sem internet");
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (mListener != null)
                mListener.myMethod(result);

        }

    }


    /*

    boolean reachable = InetAddress.getByName(hostname).isReachable();
    * HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
connection.setRequestMethod("HEAD");
int responseCode = connection.getResponseCode();
if (responseCode != 200) {
    // Not OK.
}

the best funciton

public static boolean pingURL(String url, int timeout) {
    url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

    try {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        return (200 <= responseCode && responseCode <= 399);
    } catch (IOException exception) {
        return false;
    }
}

    *
    * */


}
