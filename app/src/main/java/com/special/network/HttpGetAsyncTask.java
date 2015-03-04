package com.special.network;
import android.app.Activity;

import com.special.messageDefinition.Movie;
import com.special.utils.AsyncTaskClass;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrimsonKing on 2.3.2015.
 */
public class HttpGetAsyncTask extends AsyncTaskClass<NameValuePair,String,String> {

    public HttpGetAsyncTask(Activity activity, String url,  CallBack callBack) {
        super(activity,url, callBack);
    }



    @Override
    protected String doInBackground(NameValuePair... strings) {
        if(!url.endsWith("?")){
            url+="?";
        }
        url += URLEncodedUtils.format(getParams(strings), "utf-8");
        HttpClient client = new DefaultHttpClient();
        HttpGet post = new HttpGet(url);
        try {
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<NameValuePair> getParams(NameValuePair... pairs) {
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(pairs.length);
        for (NameValuePair pair : pairs){
            list.add(pair);
        }
        return list;
    }
}
//http://www.sinemalar.com/json/mobile/playingMovies?asd = 5& qwe=12