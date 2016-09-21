package com.mentorandroid.cursoandroid.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mentorandroid.cursoandroid.R;
import com.mentorandroid.cursoandroid.util.Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursoFragment extends Fragment {


    public CursoFragment() {
        // Required empty public constructor
    }

    private View rootView;

    WebView web;
    private View mProgressView;
    private View mLoginFormView;
    private Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        web = (WebView) rootView.findViewById(R.id.webview01);
        mLoginFormView = rootView.findViewById(R.id.login_form);
        mProgressView = rootView.findViewById(R.id.login_progress);
        ctx = getActivity();
        if (Util.isNetworkAvailable(ctx)) {
            web.setWebViewClient(new myWebClient());
            showProgress(true);
            web.getSettings().setJavaScriptEnabled(true);
            String url = "http://mentorandroid.com/start/iniciar_curso_gratuito_app/2";
            //if(Util.pingURL(url,1000)){
                Log.i("URL ->",url);
                web.loadUrl(url);
            //}


        }else{

        }
        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub


            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            //progressBar.setVisibility(View.GONE);
            showProgress(false);
        }
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            view.loadUrl("file:///android_asset/myerrorpage.html");
        }
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
                    Log.i("DEBUG ->"," Com internet");

                }else{
                    Log.i("DEBUG ->","Sem internet");
                }
            } else {

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

    /*
    * mWebView.setWebViewClient(new WebViewClient() {
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        mWebView.loadUrl("file:///android_asset/myerrorpage.html");

    }
});*/


}
