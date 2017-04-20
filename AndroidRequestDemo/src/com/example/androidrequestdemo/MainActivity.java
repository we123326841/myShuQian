package com.example.androidrequestdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	final int TIME_OUT = 10000*10;
private static final String APPLICATION_JSON = "application/json";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    
    public void doclick(View v){

    	
    	
    	Toast.makeText(MainActivity.this, "尼塔纳的", Toast.LENGTH_SHORT).show();
    	new Thread(){
        	public void run() {
        		
        		try {
//					doPost();
        			//httpPostWithJSON();
//        			postJsonAction();
//        			doGet();
        			getAction();
        		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  
        	};
        }.start();
    	
    }
    
    
    private void getAction(){
//    	GET /MJServer/login?username=123&pwd=123 HTTP/1.1
//    			User-Agent: allen wang de note7
//    			Host: 192.168.1.17:8080
//    			Connection: Keep-Alive
//    			Accept-Encoding: gzip


    	
    	try{
    		String requestURL = "http://192.168.1.101:8080/MJServer/login?username=123&pwd=123";
    		 URL url = new URL(requestURL);  
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
             conn.setReadTimeout(TIME_OUT);  
             conn.setConnectTimeout(TIME_OUT);  
//             conn.setDoInput(true); // 
//             conn.setDoOutput(true); // 
//             conn.setUseCaches(false); //
//             conn.setRequestMethod("GET"); // 
          //   conn.setRequestProperty("Charset", "utf-8");//
             conn.setRequestProperty("User-Agent", "allen wang de note7");
             
         //   OutputStream stream = conn.getOutputStream();
           // String bodyStr = "username=345&pwd=123";
           //  byte[] arr = bodyStr.getBytes();
            // stream.write(arr);
            // stream.flush();
             
             int res = conn.getResponseCode();  
             Log.e("info", "response code:"+res);  
             if(res==200) {  
                 String oneLine;  
                 StringBuffer response = new StringBuffer();  
                 BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
                 while ((oneLine = input.readLine()) != null) {  
                     response.append(oneLine);  
                 }  
                 Log.i("info", response.toString());
             }
                
             
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    }
    
    
    private void postAction() {
    	
//    	POST /MJServer/login HTTP/1.1
//    	Charset: utf-8
//    	User-Agent: allen?note7
//    	Host: 192.168.1.17:8080
//    	Connection: Keep-Alive
//    	Accept-Encoding: gzip
//    	Content-Type: application/x-www-form-urlencoded
//    	Content-Length: 20
//
//    	username=345&pwd=123
    	try{
    		String requestURL = "http://localhost:8080/MJServer/login";
    		 URL url = new URL(requestURL);  
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
             conn.setReadTimeout(TIME_OUT);  
             conn.setConnectTimeout(TIME_OUT);  
             conn.setDoInput(true); // 
             conn.setDoOutput(true); // 
             conn.setUseCaches(false); //
             conn.setRequestMethod("POST"); // 
             conn.setRequestProperty("Charset", "utf-8");//
             conn.setRequestProperty("User-Agent", "allen的note7");
             
            OutputStream stream = conn.getOutputStream();
            String bodyStr = "username=345&pwd=123";
             byte[] arr = bodyStr.getBytes();
             stream.write(arr);
             stream.flush();
             
             int res = conn.getResponseCode();  
             Log.e("info", "response code:"+res);  
             if(res==200) {  
                 String oneLine;  
                 StringBuffer response = new StringBuffer();  
                 BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
                 while ((oneLine = input.readLine()) != null) {  
                     response.append(oneLine);  
                 }  
             }
                
             
    		}catch (Exception e){
    			e.printStackTrace();
    		}
	}
    
    
    
    public void postJsonAction() throws Exception{
    	//POST /MJServer/order HTTP/1.1
//    	Content-Type: application/json
//    	User-Agent: Dalvik/1.6.0 (Linux; U; Android 4.2.2; Droid4X-MAC Build/JDQ39E)
//    	Host: 192.168.91.103:8080
//    	Connection: Keep-Alive
//    	Accept-Encoding: gzip
//    	Content-Length: 66
//
//    	{"shop_id":"123456","shop_name":"我的精品屋","user_id":"456"}
//    	
    	
    	//response
//    	HTTP/1.1 200 OK
//    	Server: Apache-Coyote/1.1
//    	Content-Type: application/json;charset=UTF-8
//    	Date: Mon, 17 Oct 2016 15:15:21 GMT
//    	Transfer-Encoding: chunked
//    	Proxy-Connection: Keep-alive
//
//    	{"success":"成功接收到订单数据"}
    	
    	
    	
    	URL url= new URL("http://192.168.1.3:8080/MJServer/order");
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	conn.setRequestMethod("POST");
    	 conn.setReadTimeout(TIME_OUT);  
         conn.setConnectTimeout(TIME_OUT); 
         conn.setRequestProperty("Content-Type", "application/json");
//    	String jsonStr ="{\"shop_id\":\"123456\",\"shop_name\":\"我的精品屋\",\"user_id\":\"456\",\"isregister\":true}";
         String jsonStr = "{  \"ishaha\" : false,  \"nimabi\" : 1,  \"nijibi\" : 0,  \"isRegister\" : true,  \"user_id\" : \"899\",  \"shop_id\" : \"1243324\",  \"shop_name\" : \"啊哈哈哈\"}";
    	byte[] jsonArr = jsonStr.getBytes();
    	OutputStream stream = conn.getOutputStream();
    	stream.write(jsonArr);
    	stream.flush();
    	
    	int code = conn.getResponseCode();
    	if(code == 200){
    		  String oneLine;  
              StringBuffer response = new StringBuffer();  
              BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
              while ((oneLine = input.readLine()) != null) {  
                  response.append(oneLine);  
              }  
              Log.i("info", response.toString());
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public String doPost()  
    {  
        String uriAPI = "http://192.168.91.104:8080/MJServer/login";//Post方式没有参数在这里  
        String result = "";  
        HttpPost httpRequst = new HttpPost(uriAPI);//创建HttpPost对象  
          
        List <NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("username", "I am Post String"));
        params.add(new BasicNameValuePair("pwd", "{  \"ishaha\" : false,  \"nimabi\" : 1,  \"nijibi\" : 0,  \"isRegister\" : true,  \"user_id\" : \"899\",  \"shop_id\" : \"1243324\",  \"shop_name\" : \"啊哈哈哈\"}")); 
          
        try {  
            httpRequst.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);  
            if(httpResponse.getStatusLine().getStatusCode() == 200)  
            {  
                HttpEntity httpEntity = httpResponse.getEntity();  
                result = EntityUtils.toString(httpEntity);//取出应答字符串  
                Log.i("info", "allen"+result);
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        return result;  
    }  
    
    
    public static void httpPostWithJSON() throws Exception {
    String	url = "http://192.168.91.104:8080/MJServer/order";
    String encoderJson = "{  \"ishaha\" : false,  \"nimabi\" : 1,  \"nijibi\" : 0,  \"isRegister\" : true,  \"user_id\" : \"899\",  \"shop_id\" : \"1243324\",  \"shop_name\" : \"啊哈哈哈\"}";
        // 将JSON进行UTF-8编码,以便传输中文
       encoderJson = URLEncoder.encode(encoderJson, HTTP.UTF_8);
        
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        
        StringEntity se = new StringEntity(encoderJson);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        httpClient.execute(httpPost);
    }
    
    
    public String doGet()  
    {  
        String uriAPI = "http://192.168.91.104:8080/MJServer/login?username=123&pwd=123";  
        String result= "";  
//      HttpGet httpRequst = new HttpGet(URI uri);  
//      HttpGet httpRequst = new HttpGet(String uri);  
//      创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。  
        HttpGet httpRequst = new HttpGet(uriAPI);  
  
//      new DefaultHttpClient().execute(HttpUriRequst requst);  
        try {  
   //使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。  
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类  
            if(httpResponse.getStatusLine().getStatusCode() == 200)  
            {  
                HttpEntity httpEntity = httpResponse.getEntity();  
                result = EntityUtils.toString(httpEntity);//取出应答字符串  
            // 一般来说都要删除多余的字符   
                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
            }  
                   else   
                        httpRequst.abort();  
           } catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        return result;  
    }  
    
}
