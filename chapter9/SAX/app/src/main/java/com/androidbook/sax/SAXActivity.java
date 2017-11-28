package com.androidbook.sax;  
  
import java.io.InputStream;  
import java.util.ArrayList;  
  
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
 
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  
  
import android.app.Activity;  
import android.content.res.AssetManager;
import android.os.Bundle;  
import android.util.Log;  
import android.widget.TextView;  
  
 
public class SAXActivity extends Activity {  
  
    private ArrayList<Information> list = new ArrayList<Information>();  
    private Information info = new Information();
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
  
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        try {
        	
            SAXParser parser = factory.newSAXParser();  
            
            AssetManager asset = null;  
            asset = getAssets();  
            InputStream input = asset.open("information.xml"); 
            parser.parse(input, new MyDefaultHandler());    
            String result = "";  
            for(Information info: list){  
                result = info.toString();  
            }     
            TextView textView = (TextView) findViewById(R.id.textView);  
            textView.setText(result);        
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    private class MyDefaultHandler extends DefaultHandler {  
  
        // 存储目前为止读取到的最后一个element的localname   
        private String currentElementName = "";
        
        /** 
         * 解析到元素的开始处触发 startElement (String uri, String localName, String qName, 
         * Attributes attributes) uri:Namespace值，当用户没有明确指定以及当命名空间没有被使用的时候，为null 
         * localName：element的名称，或者通俗点叫标签的名称。如<name>中的name就是localName qName: 
         * 和localName的唯一其别是 
         * ，当标签有namespace时，该值返回的数据为全限定名称。例如<chen:name>中，localName为name 
         * ，qName为chen:name attributes：元素包含的属性对象。如果没有属性时，返回一个空的属性对象 
         */  
        @Override  
        public void startElement(String uri, String localName, String qName,  
                Attributes attributes) throws SAXException {  
            currentElementName = localName;  
            if(localName.equals("info")){  
                info = new Information();  
            }  
              
        }  
  
        /** 
         * characters (char ch[], int start, int length)当解析xml中遇到文本内容时会执行。 ch 
         * 这个数组中存放的是整个xml文件的字符串的数组形式 start是当前解析的文本在整个xml字符串文件中的开始位置 
         * length是当前解析的文本内容的长度 由上面的介绍我们可以知道，我们可以通过new 
         * String(ch,start,length)方法来获取我们正解析的文本内容 
         */  
        @Override  
        public void characters(char[] ch, int start, int length)  
                throws SAXException {  
            Log.i("currentElementName", currentElementName);  
            String textContent = new String(ch, start, length);  
              
            if(currentElementName.equals("name")&&textContent!=null&&!textContent.trim().equals("")){  
                Log.i("textContent name", textContent);  
                info.setName(textContent);  
                  
            }  
            if(currentElementName.equals("age")&&textContent!=null&&!textContent.trim().equals("")){  
                Log.i("textContent age", textContent);  
                info.setAge(textContent);  
            }  
  
        }  
  
        /** 
         *解析到xml文档的末尾时触发 
         */  
        @Override  
        public void endDocument() throws SAXException {  
        }  
  
        /** 
         * 解析到元素的末尾时触发 
         */  
        @Override  
        public void endElement(String uri, String localName, String qName)  
                throws SAXException {  
            if(localName.equals("info")){  
                list.add(info);  
                Log.i("info", info.toString());  
                  
            }  
        } 
        
    }  
} 