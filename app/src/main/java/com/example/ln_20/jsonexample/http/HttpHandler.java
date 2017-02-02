package com.example.ln_20.jsonexample.http;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ln-20 on 2/2/17.
 */

public class HttpHandler  {
    private String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServieCall(String reqUrl){
        String res = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //read the response
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            res = convertStreamToString(inputStream);
        }catch (MalformedURLException e) {
            Log.e(TAG,e.getMessage());
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
        }
        return res;
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            while ((line =  reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
