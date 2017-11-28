package com.example.sax;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

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
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button parseRequest = (Button)findViewById(R.id.parse_request);
        Button parseLocal = (Button)findViewById(R.id.parse_local_XML) ;
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
        parseRequest.setOnClickListener(this);
        parseLocal.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {//向服务器发送请求
            sendRequestWithHttpURLConnection();
        }
        if (v.getId() == R.id.parse_request) {//解析接收到的XML文档
            parseXMLWithSAX(responseText.getText().toString());
        }
        if (v.getId() == R.id.parse_local_XML) {//解析本地文档
            parseLocalXMLWithSAX();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {// 开启线程来发起网络请求（下章再讲这部分内容）
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    //获取到HttpConnection的实例，new出一个URL对象，并传入目标的网址，
                    // 然后调用一下openConnection（）方法
                    URL url = new URL("http://10.0.2.2/data.xml");
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
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
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

    private void parseXMLWithSAX(String xmlData) {
        try {//流程参考课本 P186 ：SAX解析思路
            SAXParserFactory factory = SAXParserFactory.newInstance();//1.创建对象
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();//2.3.获取SAXParser解析器，并赋值给事件源对象XMLReader
            ContentHandler handler = new ContentHandler();//4.实例化DefaultHandler对象
            xmlReader.setContentHandler(handler);// 5.将ContentHandler的实例设置到XMLReader中
            xmlReader.parse(new InputSource(new StringReader(xmlData)));// 6.使用XMLReader的parse方法，从输入源获取xml数据
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseLocalXMLWithSAX() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {//方法同上面的一样，只是读取的是本地xml文件
            SAXParser saxParser = saxParserFactory.newSAXParser();
            AssetManager assetManager = null;//从assets中获取xml文本
            assetManager = getAssets();
            InputStream inputStream = assetManager.open("data.xml");
            saxParser.parse(inputStream,new ContentHandler());//解析文档
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ContentHandler extends DefaultHandler {
        private String nodeName;
        private StringBuilder id;
        private StringBuilder name;
        private StringBuilder version;
        private String result="";

        //参考课本 P186 表9.4 DefaultHandler类中的回调方法
        @Override//处理 文档解析 开始事件
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
            result = "";
        }

        @Override//处理 元素 开始事件
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
             nodeName = localName;// 记录当前结点名
        }

        @Override// 处理元素内容。根据当前的结点名判断将内容添加到哪一个StringBuilder对象中。
        public void characters(char[] ch, int start, int length) throws SAXException {
            if ("id".equals(nodeName)) {
                id.append(ch, start, length);
            } else if ("name".equals(nodeName)) {
                name.append(ch, start, length);
            } else if ("version".equals(nodeName)) {
                version.append(ch, start, length);
            }
        }

        @Override //处理 元素 结束事件
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                Log.d("ContentHandler", "【id】" + id.toString().trim());
                Log.d("ContentHandler", "【name】" + name.toString().trim());
                Log.d("ContentHandler", "【version】 " + version.toString().trim());
                Log.d("ContentHandler", "-----------------------------" );

                result += "\n【id】" + id.toString().trim()+"【name】" + name.toString().trim()+"【version】 " + version.toString().trim();
                // 最后要将StringBuilder清空掉
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        @Override
        public void endDocument() throws SAXException {//处理文档解析的结束事件
            super.endDocument();
            showResponse(result);
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
