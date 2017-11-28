package com.example.webviewdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    WebView mWebview;
    WebSettings mWebSettings;
    TextView beginLoading,endLoading,loading,mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebview = (WebView) findViewById(R.id.webView1);
        beginLoading = (TextView) findViewById(R.id.text_beginLoading);
        endLoading = (TextView) findViewById(R.id.text_endLoading);
        loading = (TextView) findViewById(R.id.text_Loading);
        mtitle = (TextView) findViewById(R.id.title);

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {//获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                mtitle.setText("网页标题："+title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) { //获取加载进度
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    loading.setText("加载进度:"+progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    loading.setText("加载进度:"+progress);
                }
            }
        });

        mWebview.setWebViewClient(new WebViewClient() {//设置WebViewClient类
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {//设置加载前的函数
                System.out.println("开始加载!!!");
                beginLoading.setText("开始加载!!!");
            }
            @Override
            public void onPageFinished(WebView view, String url) {//设置结束加载函数
                endLoading.setText("结束加载!!!");
            }
        });
    }

    public void loadUrl(View view){
        //mWebSettings = mWebview.getSettings();
        try{
            mWebview.loadUrl("https://www.baidu.com/");
        }catch (Exception ex){
            ex.printStackTrace();
        }
//        mWebview.setWebViewClient(new WebViewClient() {//设置不用系统浏览器打开,直接显示在当前Webview
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }

    public void loadData(View view){
        try{
            String data = "<html><title>河北大学</title>"+"<body>" + "网络空间安全与计算机学院"+"</body>"+"</html>";
            mWebview.loadData(data,"text/html;charset=UTF-8",null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadLocal(View view){
        try{
            mWebview.loadUrl("file:///android_asset/test.html");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadImage(View view){
        try{
            //String data = "测试本地图文混排。这是<IMG src='\"file:///android_asset/icon.jpg\"'>APK中的图片。";
            String data = "测试本地图文混排。这是APK中的图片:"+"<IMG src= file:///android_asset/icon.jpg>"+"图片路径：src= file:///android_asset/icon.jpg";
            mWebview.loadDataWithBaseURL("about:blank",data,"text/html;charset=UTF-8",null,"");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//点击返回上一页面而不是退出浏览器
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {//销毁Webview
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
}
