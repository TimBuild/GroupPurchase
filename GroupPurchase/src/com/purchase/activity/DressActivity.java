package com.purchase.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.matrix.grouppurchase.R;
import com.purchase.server.DressManager;
import com.squareup.picasso.Picasso;

public class DressActivity extends Activity{
	
	private ImageView iv_thumb;
	private TextView tv_des,tv_price_discount,tv_price;
	private Button but_jump;
	
	private String path;//跳转时的路径
	private static String TAG = "DressActivity";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dress);
		
		initViews();
		
		Intent intent = getIntent();
		tv_des.setText(intent.getStringExtra("dress_title"));
		tv_price_discount.setText(intent.getStringExtra("dress_discount"));
		tv_price.setText(intent.getStringExtra("dress_price"));
		Picasso.with(this).load(intent.getStringExtra("dress_url")).into(iv_thumb);
		
		new GetDress().execute(intent.getStringExtra("dress_path"));
//		AlibabaSDK.asyncInit(DressActivity.this, new InitResultCallback() {
//			
//			@Override
//			public void onFailure(int arg0, String arg1) {
//				Toast.makeText(DressActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
//			}
//			
//			@Override
//			public void onSuccess() {
//				Toast.makeText(DressActivity.this, "初始化成功"+path, Toast.LENGTH_SHORT).show();
//				
//			}
//		});
		but_jump.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "path:"+path);
//				LoginService loginService = AlibabaSDK.getService(LoginService.class);
//				loginService.showLogin(DressActivity.this, new LoginCallback() {
//					
//					@Override
//					public void onFailure(int code, String message) {
//						Toast.makeText(DressActivity.this, "授权取消"+code+","+message, Toast.LENGTH_SHORT).show();
//					}
//					
//					@Override
//					public void onSuccess(Session session) {
//						Toast.makeText(DressActivity.this, "欢迎"+session.getUser().nick, Toast.LENGTH_SHORT).show();
//					}
//				});
				
				Intent intent = new Intent(DressActivity.this, DressWebView.class);
				intent.putExtra("path", path);
				startActivity(intent);
				
//				ItemService 
				
			}
		});
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		CallbackContext.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 找到所有组件
	 */
	private void initViews() {
		iv_thumb = (ImageView) findViewById(R.id.iv_thumb);
		tv_des = (TextView) findViewById(R.id.tv_des);
		tv_price_discount = (TextView) findViewById(R.id.tv_price_info);
		tv_price = (TextView) findViewById(R.id.tv_info);
		but_jump = (Button) findViewById(R.id.bt_click);
		
	}
	
	private class GetDress extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			
			path = DressManager.getDetailDress(params[0]);
			return path;
		}
		
	}
}
