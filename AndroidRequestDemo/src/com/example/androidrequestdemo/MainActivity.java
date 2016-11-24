package com.example.androidrequestdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	final int TIME_OUT = 10000*10;
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
					postJsonAction();
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
    		String requestURL = "http://192.168.91.103:8080/MJServer/login?username=123&pwd=123";
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
    	
    	
    	
    	URL url= new URL("http://192.168.1.21:8080/MJServer/order");
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
    
}
