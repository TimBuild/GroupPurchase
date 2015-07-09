package com.purchase.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.matrix.grouppurchase.R;
import com.purchase.view.ProgressDialog;

public class DressWebView extends Activity {

	private WebView webView;
	private TextView tv_title;
	private TextView tv_progress;
	private RelativeLayout rl_back;
	private static String TAG = "DressWebView";
	private ProgressBar pb_webview;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_dress_webview);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.activity_detail_title);

		tv_title = (TextView) findViewById(R.id.title_menu_text);
		tv_progress = (TextView) findViewById(R.id.title_menu_right);
		rl_back = (RelativeLayout) findViewById(R.id.rl_back);
		webView = (WebView) findViewById(R.id.wv_dress);
		pb_webview = (ProgressBar) findViewById(R.id.pb_webview);
		pb_webview.setMax(100);
		
		
		
		rl_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
			}
		});

		String path = getIntent().getStringExtra("path");
//		String path = "http://item.taobao.com/item.htm?id=520137923678";
//		String path = "http://h5.m.taobao.com/awp/core/detail.htm?id=520137923678";

		
		
		// 启用支持javascript
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.supportMultipleWindows();
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setLoadsImagesAutomatically(true);

		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				if(url.startsWith("taobao://")){
					url = url.replace("taobao:", "http:");
					return true;
				}
				view.loadUrl(url);
				return true;
			}
			
			
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
				
				Log.e(TAG, "errorCode:"+errorCode+",description-->"+description+",failingUrl:"+failingUrl);
			}
			
			
		});

		MyWebChromeClient myWebChromeClient = new MyWebChromeClient();
		webView.setWebChromeClient(myWebChromeClient);
		
//		webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		// webview加载网络资源
		webView.loadUrl(path);

	}


	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress < 100) {
				String progress = newProgress + "%";
				tv_progress.setText(progress);
				pb_webview.setProgress(newProgress);
			} else {
				
				tv_progress.setText("");
				pb_webview.setVisibility(View.GONE);
				webView.setVisibility(View.VISIBLE);
			}
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			tv_title.setText(title);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode == KeyEvent.KEYCODE_BACK)&&webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
