package com.example.json;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.response_text);
        Button buildJson =(Button)findViewById(R.id.build_json);
        Button parseJson = (Button)findViewById(R.id.parse_json) ;
        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button parseRequest = (Button)findViewById(R.id.parse_request);
        sendRequest.setOnClickListener(this);
        buildJson.setOnClickListener(this);
        parseJson.setOnClickListener(this);
        parseRequest.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequest();
        }
        if (v.getId() == R.id.build_json) {
            buildJson();
        }
        if (v.getId() == R.id.parse_json) {
            parseJson();
        }
        if (v.getId() == R.id.parse_request) {
            parseRequest();
        }
    }

    private void buildJson(){
                try {
                    JSONObject person = new JSONObject();
                    JSONArray phone = new JSONArray();
                    phone.put("123");
                    person.put("phone", phone);
                    person.put("name","Tom");
                    person.put("age","18");
                    JSONObject address = new JSONObject();
                    address.put("country","china");
                    address.put("Province","He Bei");
                    person.put("address",address);
                    person.put("married",false);
                    Log.d("David","Person");
                    showResponse(person.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
    }

    private void parseJson(){
        try {
            Log.e("wyg",responseText.getText().toString());
            JSONObject object = new JSONObject(responseText.getText().toString());
            JSONArray array = object.getJSONArray("phone");
            String phone = array.get(0).toString();
            String name = object.getString("name");
            int age = object.getInt("age");
            JSONObject object1 = object.getJSONObject("address");
            String country = object1.getString("country");
            String province = object1.getString("Province");
            Boolean married = object.getBoolean("married");
            String parsedJson;
            parsedJson = phone +"\n"+ name+"\n" + age+"\n" + country+"\n" + province+"\n" + married;
            showResponse(parsedJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendRequest() {
        // 开启线程来发起网络请求（下章再讲这部分内容）
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    //获取到HttpConnection的实例，new出一个URL对象，并传入目标的网址，然后调用一下openConnection（）方法
                    URL url = new URL("http://www.weather.com.cn/data/sk/101090201.html");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); //得到了HttpConnection的实例后，设置请求所用的方法（GET：从服务器获取数据，POST：提交数据给服务器）
                    connection.setConnectTimeout(8000);//设置连接超时，读取的毫秒数
                    connection.setReadTimeout(8000);

                    InputStream in = connection.getInputStream();//利用getInputStream（）方法获取服务器的返回的输入流，然后读取
                    reader = new BufferedReader(new InputStreamReader(in));// 对获取到的输入流进行读取
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.e("wyg",responseText.getText().toString());
                    showResponse(response.toString());//在模拟器显示返回值
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        //调用disconnect（）方法将HTTP连接关闭掉
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void parseRequest() {
        try {
            Log.e("wyg",responseText.getText().toString());
            JSONObject object = new JSONObject(responseText.getText().toString());
            JSONObject object1 = object.getJSONObject("weatherinfo");
            String city = object1.getString("city");
            String temp = object1.getString("temp");
            String WD = object1.getString("WD");
            String WS = object1.getString("WS");
            String parsedJson;
            parsedJson =  city + "\n" + temp + "\n" + WD +WS+"\n" ;
            showResponse(parsedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }


}