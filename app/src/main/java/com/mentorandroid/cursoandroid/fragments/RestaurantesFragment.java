package com.mentorandroid.cursoandroid.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.mentorandroid.cursoandroid.R;
import com.mentorandroid.cursoandroid.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantesFragment extends Fragment {


    public RestaurantesFragment() {
        // Required empty public constructor
    }

    public EditText editTextRest;

    public View rootView;
    private View mProgressView;
    private Context ctx;
    public WebView web;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_restaurantes, container, false);

        //editTextRest = (EditText)rootView.findViewById(R.id.editTextRest);

        //editTextRest.setText("Mobile Hybrid !!!");

        mProgressView = rootView.findViewById(R.id.login_progress);
        web = (WebView) rootView.findViewById(R.id.webview01);

        Log.d("DEBUG","Entrou 0fdfdsfds");

        ctx = getActivity();
        if (Util.isNetworkAvailable(ctx)) {
            web.setWebViewClient(new RestaurantesFragment.myWebClient());
            showProgress(true);
            Log.d("DEBUG","Entrou 02");
            web.getSettings().setJavaScriptEnabled(true);

            Log.d("DEBUG","Entrou 01");

            //"http://www.workmates.com.br/index.php/contato/compartilhar_marmita");
            String url = "http://www.softwareon.com.br/marmita";

            Log.i("URL ->",url);
            web.loadUrl(url);


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

            web.setVisibility(show ? View.GONE : View.VISIBLE);
            web.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    web.setVisibility(show ? View.GONE : View.VISIBLE);
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
            web.setVisibility(show ? View.GONE : View.VISIBLE);
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
    }

}
