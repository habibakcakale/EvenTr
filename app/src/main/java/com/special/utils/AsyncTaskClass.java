package com.special.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Created by CrimsonKing on 24.2.2015.
 */
public abstract class AsyncTaskClass<T,K,L> extends AsyncTask<T, K, L> {
    protected Activity activity;
   protected CallBack callBack;
    protected String url;
    public AsyncTaskClass(Activity activity, String url, CallBack callBack){
    this.activity = activity;
    this.callBack = callBack;
        this.url     = url;
}
/*
    @Override
    protected String doInBackground(String... uri) {
        gsonBuilder = new GsonBuilder().create();
        HttpClient client = new DefaultHttpClient();
        HttpGet post = new HttpGet(uri[0]);
        String result = null;

        try {

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
*/
    @Override
    protected void onPostExecute(L l) {
        if(callBack!=null){
            callBack.doJob(l);
        }
        super.onPostExecute(l);
    }

    public interface CallBack<X>{
        void doJob(X params);
    }
}
