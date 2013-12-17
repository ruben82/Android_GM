package com.ruben.gm.utils;

import android.content.Context;
import android.os.StrictMode;
import android.util.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: r.deluca
 * Date: 04/07/13
 * Time: 10.39
 * To change this template use File | Settings | File Templates.
 */
public class HTTPUTils {

    public static HttpResponse Authenticate(){
        URI uri = null;
        HttpResponse response=null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String tok = "Basic " + new String(Base64.encode((Utils.username + ':' + Utils.password).getBytes(),Base64.URL_SAFE)).trim();
        
        try {
            uri = new URI(Utils.getAuthUrl());
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            int timeoutSocket = 30000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpGet httpget = new HttpGet(uri);
            System.out.println("Prima della chiamata: "+tok);
            httpget.setHeader("Authorization", tok);
            httpget.setHeader("Accept", "application/json");
            response   = httpclient.execute(httpget);
        }catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return response;
    }






    public static HttpResponse httpGetCall(String finalUrl ,String token){
        URI uri = null;
        HttpResponse response=null;

        try {
            uri = new URI(Utils.getUrl()+finalUrl);
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            int timeoutSocket = 30000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpGet httpget = new HttpGet(uri);
            httpget.setHeader("Authorization",token);
            httpget.setHeader("Accept", "application/json");
            System.out.println("Prima della chimaata ");
            response   = httpclient.execute(httpget);
        }catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return response;
    }





    public static HttpResponse httpPostCall(String finalUrl,StringEntity se,String token){
        URI uri = null;
        HttpResponse response=null;
        try {

            uri = new URI(Utils.getUrl()+finalUrl);
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 20000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            int timeoutSocket = 20000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(uri);
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8");
            httppost.setHeader("Authorization",token);
            httppost.setHeader("Accept", "application/json");
            System.out.println("Prima della chimaata ");
            // aggiungo i dati alla richiesta
            httppost.setEntity(se);
            response = httpclient.execute(httppost);
        }catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return response;
    }
}
