package com.own.news.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetDataTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<Context> mRef;
    private String urlString = null;
    private Callback callback;
    private static String GET = "GET";
    public static String POST = "POST";
    private String requestMethod = GET;
    private String CONTENT_TYPE = "Content-Type";
    private String requestProperty = ContentType.APPLICATION_JSON;
    private String requestBody;
    private int TIMEOUT = 20000;
    private ParseCallback parseCallback;
    private Object resultObject;


    public interface ContentType {
        String APPLICATION_JSON = "application/json";
    }

    public GetDataTask (Context context, String url) {
        mRef = new WeakReference<>(context);
        this.urlString = url;
    }


    public void setRequestMethod(String type) {
        requestMethod = type;
    }


    public void setRequestProperty(String requestProperty) {
        this.requestProperty = requestProperty;
    }

    public GetDataTask setParseCallback(ParseCallback parseCallback){
        this.parseCallback = parseCallback;
        return this;
    }


    public GetDataTask setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }


    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... voids) {

        if (urlString.contains("http://") || urlString.contains("https://")) {
            Log.e("URL", urlString);
            HttpURLConnection urlConnection = null;
            URL url;
            try {
                url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(TIMEOUT);
                urlConnection.setReadTimeout(TIMEOUT);
                if (requestMethod.equals(GET)) {
                    return parse(urlConnection.getInputStream());
                } else {
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod(requestMethod);
                    urlConnection.setRequestProperty(CONTENT_TYPE, requestProperty);
                    write(urlConnection.getOutputStream(), requestBody);
                    return parse(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(urlConnection !=null){
                    urlConnection.disconnect();
                }
            }
            return null;

        } else {
            try {
                return parse(mRef.get().getAssets().open(urlString));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }


    }

    private void write(OutputStream outputStream, String requestBody) {
        try {
            Log.e("RequestBody", requestBody);
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onPostExecute(String s) {
        Context context = mRef.get();
        Log.e("GetDataTask", urlString + " Response: ".concat(s !=null ? s : "Null"));
        if (callback != null && context != null) {
            callback.onPostExecute(s, resultObject);
        }
    }

    private String parse(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream)));
        String line;
        StringBuilder result = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(parseCallback!=null){
                parseCallback.onParseComplete(result.toString(), this);
            }

        }

        return result.toString();
    }

    public void setRequestBody(Object body, Class c) {
        requestBody = new Gson().toJson(body, c);
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public interface Callback {
        void onPostExecute (String result, Object resultObject);
    }

    public interface ParseCallback{
        void onParseComplete (String result, GetDataTask getDataTask);
    }













}
