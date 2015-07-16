package com.purchase.activity;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.trade.ItemService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matrix.grouppurchase.R;
import com.purchase.entity.GoodsItem;
import com.purchase.service.GoodsItemService;
import com.purchase.util.HttpUtil;
import com.taobao.tae.sdk.model.TaokeParams;
import com.taobao.tae.sdk.webview.TaeWebViewUiSettings;

public class TaoBaoActivity extends Activity{
	
	private static String TAG = "TaoBaoActivity";
	private Button but_show;
	private WebView webView;;
	
	private String open_id;
	private Integer type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taobao);
		but_show = (Button) findViewById(R.id.but_show);
//		webView = (WebView) findViewById(R.id.wv_taobao);
		
		open_id = getIntent().getStringExtra("itemId");
		type = Integer.parseInt(getIntent().getStringExtra("itemType"));
		
		Log.i(TAG, "type:"+type+",open_id:"+open_id);
				
		AlibabaSDK.asyncInit(this, new InitResultCallback() {

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(TaoBaoActivity.this, "初始化失败", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onSuccess() {
				Toast.makeText(TaoBaoActivity.this, "初始化成功", Toast.LENGTH_SHORT)
						.show();
			}
		});
		but_show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				webView.setVisibility(View.VISIBLE);
//				but_show.setVisibility(View.VISIBLE);
				showTaokeItemDetail();
			}
		});
	}
	
	protected void showTaokeItemDetail() {
		TaeWebViewUiSettings taeWebViewUiSettings = new TaeWebViewUiSettings();
		taeWebViewUiSettings.title = "jsjdjijdijwidj";
		ItemService service = AlibabaSDK.getService(ItemService.class);
		service.showItemDetailByOpenItemId(this, new TradeProcessCallback() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPaySuccess(TradeResult arg0) {
				// TODO Auto-generated method stub
				
			}
		}, taeWebViewUiSettings, open_id, type, null);
		
	}

	private class TaoBaoInfo extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			String url = "http://192.168.1.69:8081/PurchaseServer/rest/GoodsService/getGoods";
//			String url = "http://lavie-1.wx.jaeapp.com/rest/GoodsService/getGoods";
			String result = HttpUtil.doGet(url);
			
			List<GoodsItem> lists = GoodsItemService.getGoodsItemByJson(result);
			Log.i(TAG, lists.size()+"");
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}
		
	}
}
