package com.mentorandroid.cursoandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mentorandroid.cursoandroid.util.Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class ConectionTestActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conection_test);


        ctx = this;

/*
        try {
            if(isReachable(url)) {
                Toast.makeText(this, "Funcionou", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Não funcionou", Toast.LENGTH_SHORT).show();

            }
        } catch (IOException e) {
            Toast.makeText(this, "----------> ERRO", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
*/

        //n.execute("Teste");
        Log.i("DEBUG ->"," Entrou 01");
        String url = "http://www.mentorandroid.com";

        new NetworkUtil().execute(url, "Teste");


    }



    public class NetworkUtil extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... arg0) {
            if (Util.isNetworkAvailable(ctx)) {
                // your get/post related code..like HttpPost = new HttpPost(url);
                //String url = "http://www.google.com";
                Log.i("DEBUG ->"," Entrou 01");
                if(pingURL(arg0[0],1000)){
                    Log.i("DEBUG ->"," Entrou 03");
                    Toast.makeText(ConectionTestActivity.this, "Tem internet!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ConectionTestActivity.this, "Sem internet!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ConectionTestActivity.this, "no internet!", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }

    }


    public static boolean pingURL(String url, int timeout) {
        Log.i("DEBUG ->"," Entrou 02");
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
}


