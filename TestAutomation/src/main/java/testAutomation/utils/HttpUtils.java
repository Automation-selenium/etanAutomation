package testAutomation.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class HttpUtils {

    private static HttpUtils instance;

    /**
     * Gets GridHttpClient instance.
     * @return GridHttpClient
     */
    public static HttpUtils getInstance(){
        if (instance == null){
            instance = new HttpUtils();
        }
        return instance;
    }

    public HttpResponse get(String url) throws IOException{
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpGet);

        return response;
    }


}