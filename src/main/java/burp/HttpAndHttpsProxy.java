package burp;

import okhttp3.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.X509TrustManager;


public class HttpAndHttpsProxy {
    public static Map<String,String> Proxy(IHttpRequestResponse requestResponse) throws InterruptedException{
        byte[] req = requestResponse.getRequest();
        String url = null;
        byte[] reqbody = null;
        List<String> headers = null;

        IHttpService httpService = requestResponse.getHttpService();
        IRequestInfo reqInfo = BurpExtender.helpers.analyzeRequest(httpService,req);

        if(reqInfo.getMethod().equals("POST")){
            int bodyOffset = reqInfo.getBodyOffset();
            String body = null;
            try {
                body = new String(req, bodyOffset, req.length - bodyOffset, "UTF-8");
                reqbody = body.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //BurpExtender.stderr.println("[+] url: " + resInfo.getUrl());
        headers = reqInfo.getHeaders();
        url = reqInfo.getUrl().toString();
        Thread.sleep(Config.INTERVAL_TIME);
        return HttpsProxy(url, headers, reqbody, Config.PROXY_HOST, Config.PROXY_PORT,Config.PROXY_USERNAME,Config.PROXY_PASSWORD,Config.PROXY_TIMEOUT);
    }
    public static Headers SetHeaders(List<String> headers) {
        Headers Headers = null;
        okhttp3.Headers.Builder headersbuilder = new okhttp3.Headers.Builder();
        for(String header:headers){
            if(header.startsWith("GET") ||
                    header.startsWith("POST") ||
                    header.startsWith("PUT")){
                continue;
            }
            String[] h = header.split(":",2);
            String header_key = h[0].trim();
            String header_value = h[1].trim();
            headersbuilder.add(header_key, header_value);
        }


        Headers = headersbuilder.build();
        return Headers;
    }
    public static Map<String,String> HttpsProxy(String url, List<String> headers, byte[] body, String proxy, int port, final String username, final String password,Integer timeout){
        Map<String,String> mapResult = new HashMap<String,String>();
        String status="";
        String result = "";
        String rspHeader = "";
        String contenttype=null;
        Response response = null;
        Request build=null;
        OkHttpClient client=null;






        try {

            Proxy proxy1 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy, port));

            Authenticator proxyAuthenticator = new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic(username, password);
                    return response.request().newBuilder()
                            .header("Proxy-Authorization", credential)
                            .build();
                }
            };



            Headers setHeaders = SetHeaders(headers);
            X509TrustManager manager = SSLSocketClientUtil.getX509TrustManager();

            client = new OkHttpClient().newBuilder().
                    connectTimeout(timeout, TimeUnit.MILLISECONDS).readTimeout(timeout, TimeUnit.MILLISECONDS).proxy(proxy1)
                    .proxyAuthenticator(proxyAuthenticator)
                    .sslSocketFactory(SSLSocketClientUtil.getSocketFactory(manager), manager)// 忽略校验
                    .hostnameVerifier(SSLSocketClientUtil.getHostnameVerifier())//忽略校验
                    .build();


            if(body!=null){
                for(String header:headers){
                    if(header.startsWith("Content-Type")){
                        String[] h = header.split(":");
                        contenttype = h[1].trim();
                        break;
                    }
                }
                MediaType media = MediaType.parse(contenttype!=null?contenttype:"application/x-www-form-urlencoded");
               RequestBody requestbody= RequestBody.create(media,body);
               System.out.println(url);
                build = new Request.Builder()
                        .url(url)
                        .headers(setHeaders)
                        .post(requestbody)
                        .build();

            }
            else {
               build= new Request.Builder()
                        .url(url)
                        .headers(setHeaders)
                        .build();

            }
            response = client.newCall(build).execute();

            Integer i=response.code();
            status=i.toString();

            Headers responseHeaders = response.headers();
            for (int ii = 0; ii < responseHeaders.size(); ii++) {
                String header_line = String.format("%s: %s\r\n", responseHeaders.name(ii), responseHeaders.value(ii));
                rspHeader += header_line;
            }
            result=response.body().string();




        } catch (Exception e) {
            e.printStackTrace();
            BurpExtender.stderr.println("[*] " + e.getMessage());
            result = e.getMessage();
            Utils.updateFailCount();
        } finally {
            if(response!=null){
            response.body().close();}
            if(client!=null){
            client.dispatcher().executorService().shutdown();   //清除并关闭线程池
            client.connectionPool().evictAll();                 //清除并关闭连接池
            }
        }
        mapResult.put("status",status);
        mapResult.put("header",rspHeader);
        mapResult.put("result",result);

        return mapResult;

    }





}
