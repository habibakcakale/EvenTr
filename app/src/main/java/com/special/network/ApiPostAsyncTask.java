package com.special.network;

import android.os.AsyncTask;

/**
 * Created by Habib on 1.4.2015.
 */
public class ApiPostAsyncTask<T, L> extends AsyncTask<T, Void, L> {

    ApiCallBack process;

    public ApiPostAsyncTask(ApiCallBack process) {
        this.process = process;
    }

    @Override
    protected L doInBackground(T... ts) {
        return null;
    }

    @Override
    protected void onPostExecute(L l) {

        process.onComplete(l);
    }
}
