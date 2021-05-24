package com.mindorks.framework.mvvm.daos;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class testAPIold {

    public String get(String url)
    {
        String JSONString = "";
        HttpResponse response;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getConnection = new HttpGet(url);
            try {
                response = httpClient.execute(getConnection);
                JSONString = EntityUtils.toString(response.getEntity(),
                        "UTF-8");
                JSONArray jsonArray = new JSONArray(JSONString); //Assuming it's a JSON Object

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONString;
        /*
        String strResp = "";
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpUriRequest request = new HttpGet(url);
            //request.addHeader("Context", "some-value");
            HttpResponse httpResp = httpClient.execute(request);
            strResp = IOUtils.toString(httpResp.getEntity().getContent(), "UTF-8");
            return strResp;
        }
        catch (Exception e) {
            strResp = "Error";
        }
        return strResp;

         */
    }
/*
        StringBuilder sb = new StringBuilder();


        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            Httpget httpget = new HttpGet(url);

            HttpEntity entity = null;
            try {
                HttpResponse response = httpclient.execute(httpget);
                entity = response.getEntity();
            } catch (Exception e) {
                Log.d("Exception", e);
            }


            if (entity != null) {
                InputStream is = null;
                is = entity.getContent();

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    reader.close();
                } catch (IOException e) {

                    throw e;

                } catch (RuntimeException e) {

                    httpget.abort();
                    throw e;

                } finally {

                    is.close();

                }
                httpclient.getConnectionManager().shutdown();
            }
        } catch (Exception e) {
            Log.d("Exception", e);
        }

        String result = sb.toString().trim();

        return result;

 */
}
