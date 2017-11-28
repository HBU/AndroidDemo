package com.androidbook.JsToJava;
  
import com.androidbook.JsToJava.R;

import android.app.Activity;   
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;   
import android.os.Handler;

import android.util.Log;
import android.view.KeyEvent;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;   
  
public class JsToJavaActivity extends Activity {   
    WebView webView;   
    final String mimeType = "text/html";   
    final String encoding = "utf-8";   
    private Handler mHandler = new Handler(); 
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);   
        webView = (WebView) findViewById(R.id.webview);   
        webView.getSettings().setJavaScriptEnabled(true); 
        //javascript与java之间的信息交互接口       
           
        webView.addJavascriptInterface(new Object() {
        /**
         * 该方法被浏览器调用
         * @param str
         */
        	public void callAndroid(final String str) { 
        		
        		mHandler.post(new Runnable() {  
        			
        			public void run() {  
        				
                        startActivity(new Intent( JsToJavaActivity.this, Activity1.class))  ; //按确定键跳转到下一个Activity
}   
        		}); 
        	}
        }, "demo");   
        webView.loadUrl("file:///android_asset/demo.html");
    } 

}   
