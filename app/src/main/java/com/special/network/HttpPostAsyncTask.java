package com.special.network;

import android.app.Activity;

import com.special.utils.AsyncTaskClass;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by CrimsonKing on 2.3.2015.
 */
public class HttpPostAsyncTask extends AsyncTaskClass<NameValuePair,String,String> {
    public HttpPostAsyncTask(Activity activity, String url, CallBack callBack) {
        super(activity,url, callBack);
    }

    @Override
    protected String doInBackground(NameValuePair... strings) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new UrlEncodedFormEntity(getParameters(strings)));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    ArrayList<NameValuePair> getParameters(NameValuePair... pairs){
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(pairs.length);
        for (NameValuePair pair:pairs)
        {
            list.add(pair);
        }
        return list;
    }
}
