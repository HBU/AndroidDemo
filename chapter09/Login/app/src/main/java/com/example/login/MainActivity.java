package com.example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onLoginClick(View v){
        //Toast.makeText(this, "登录功能暂未实现", Toast.LENGTH_SHORT).show();  //Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）的值
        sendRequest();

    }

    public void onRegClick(View v){
        Toast.makeText(this, "注册功能暂未实现", Toast.LENGTH_SHORT).show();
    }
    private StringBuilder response;
    private void sendRequest() {
        // 开启线程来发起网络请求（下章再讲这部分内容）
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    //获取到HttpConnection的实例，new出一个URL对象，并传入目标的网址，
                    // 然后调用一下openConnection（）方法
                    URL url = new URL("https://e0d980a0-9406-4d96-b8af-8a3375049db0.coding.io/conn.php");
                    connection = (HttpURLConnection) url.openConnection();
                    //得到了HttpConnection的实例后，设置请求所用的方法
                    // （GET：从服务器获取数据，POST：提交数据给服务器）
                    connection.setRequestMethod("GET");
                    //设置连接超时，读取的毫秒数
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //利用getInputStream（）方法获取服务器的返回的输入流，然后读取
                    InputStream in = connection.getInputStream();
                    // 对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.d("wyg",response.toString());
                    parseRequest();

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
            JSONArray jsonArray = new JSONArray(response.toString());
            String result="";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id =         jsonObject.getString("id");
                String psw =       jsonObject.getString("psw");
                result = "\n【id】" + id +"  【name】" + psw+"\n" ;
                Log.d("wyg",result);
                if(id.equals("1") && psw.equals("1"))
                    Log.d("wyg","Login success");
                else
                    Log.d("wyg","Error! Check user name and password.");
                //showResponse(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
