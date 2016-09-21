package com.mentorandroid.cursoandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mentorandroid.cursoandroid.fragments.CursoFragment;
import com.mentorandroid.cursoandroid.fragments.HelpFragment;
import com.mentorandroid.cursoandroid.fragments.IndexFragment;
import com.mentorandroid.cursoandroid.fragments.NewsFragment;
import com.mentorandroid.cursoandroid.fragments.ShareFragment;
import com.mentorandroid.cursoandroid.fragments.YouTubeFragment;
import com.mentorandroid.cursoandroid.util.Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        Log.i("DEBUG ->", "Oncreate Main");
        String url = "http://www.mentorandroid.com";
        String title = "Curso Gratuito";
        NetworkUtil task = new NetworkUtil( url, new MyInterface() {
            @Override
            public void myMethod(boolean result) {
                if (result == true) {
                    Log.i("DEBUG ->", "Com conexao");
                    Toast.makeText(MainActivity.this, "Connection Succesful",
                            Toast.LENGTH_LONG).show();
                    Fragment fragment = new CursoFragment();
                    String title = "Curso Gratuito";
                    if (fragment != null) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container_body, fragment);
                        ft.commit();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Connection Failed:", Toast.LENGTH_LONG).show();
                }
            }
        });
        task.execute();
        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

    }

    public static boolean isReachable(String targetUrl) throws IOException {
        HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(
                targetUrl).openConnection();
        httpUrlConnection.setRequestMethod("HEAD");

        try {
            int responseCode = httpUrlConnection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (UnknownHostException noInternetConnection) {
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        String title = getString(R.string.app_name);
/*

nav_curso

nav_premium

nav_youtube

nav_manage

nav_blog

nav_share

nav_help

* */
        if (id == R.id.nav_curso) {
            fragment = new CursoFragment();
            title = getString(R.string.app_name);
        } else if (id == R.id.nav_premium) {
            fragment = new IndexFragment();
            title = getString(R.string.app_name);
        } else if (id == R.id.nav_youtube) {
            fragment = new YouTubeFragment();
            title = getString(R.string.canal_youtube);
        } else if (id == R.id.nav_manage) {
            fragment = new IndexFragment();
            title = getString(R.string.canal_youtube);
        } else if (id == R.id.nav_share) {
            fragment = new ShareFragment();
            title = getString(R.string.canal_youtube);
        } else if (id == R.id.nav_blog) {
            fragment = new NewsFragment();
            title = getString(R.string.blog);
        } else if (id == R.id.nav_help) {
            fragment = new HelpFragment();
            title = getString(R.string.canal_youtube);
            //Intent i = new Intent(this, ConectionTestActivity.class);
            //startActivity(i);

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_body, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //------------------ this asynctask is to ping to network before loading the webview ----------

    public interface MyInterface {
        public void myMethod(boolean result);
    }

    public class NetworkUtil extends AsyncTask<String, Void, Boolean> {
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


}
