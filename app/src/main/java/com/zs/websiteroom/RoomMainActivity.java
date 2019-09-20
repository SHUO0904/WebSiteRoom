package com.zs.websiteroom;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class RoomMainActivity extends AppCompatActivity {

    WebView mWebview;
    WebSettings mWebSettings;
    TextView loading;
    private String url_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);


        mWebview = (WebView) findViewById(R.id.webView1);
        //loading = (TextView) findViewById(R.id.text_Loading);



    }

    private void webViewSet(String url) {
        mWebSettings = mWebview.getSettings();
        mWebview.loadUrl(url);
        System.out.println("url=====================" + url);
        //支持javascript
        mWebSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebSettings.setSupportZoom(true);
        // 设置出现缩放工具
        mWebSettings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        mWebSettings.setUseWideViewPort(true);
        //自适应屏幕
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setLoadWithOverviewMode(true);


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                //mtitle.setText(title);
            }


            //获取加载进度
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    String progress = newProgress + "%";
//                    loading.setText(progress);
//                } else if (newProgress == 100) {
//                    String progress = newProgress + "%";
//                    loading.setText(progress);
//                }
//            }
        });


        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载了");
                // beginLoading.setText("开始加载了");

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                //endLoading.setText("结束加载了");

            }
        });


    }

    private String url(String p) {
        url_path = p;
        return url_path;
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m1:
                System.out.println("=====================1");
                String c = "http://36.7.87.209:8088/online/roomResource.xp?action=showResource";
                webViewSet(c);
                break;
            case R.id.m2:
                System.out.println("=====================2");
                String w = "http://117.71.57.99:9080/online/roomResource.xp?action=showResource";
                webViewSet(w);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
