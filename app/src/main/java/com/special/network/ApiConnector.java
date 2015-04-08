package com.special.network;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ApiConnector<T, K> {
    private static Gson gson = new GsonBuilder().create();
    private static HashMap<String, TransactionHeader> transactions;
    Activity context;

    public ApiConnector(Activity context) {
        this.context = context;
    }

    public static void init(Application context) {
        AssetManager manager = context.getAssets();
        try {
            String[] fileList = manager.list("transactionconfiguration");
            transactions = new HashMap<String, TransactionHeader>(fileList.length);
            for (String file : fileList) {
                String fileContent = ReadFileContent(context, file);
                transactions.put(file.split("\\.")[0], gson.fromJson(fileContent, TransactionHeader.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String ReadFileContent(Context context, String fileName) {
        BufferedReader reader = null;
        String result = "";
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("transactionconfiguration/" + fileName), "UTF-8"));
            String mLine = reader.readLine();
            while (mLine != null) {
                result += mLine;
                mLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void Fetch(String transactionName, T requestItem, ApiCallBack<K> process) {
        ApiRequest request = new ApiRequest();
        request.Header = transactions.get(transactionName);
        request.Item = gson.toJson(requestItem);
        new ApiPostAsyncTask<T, K>(process).execute();
    }
}
