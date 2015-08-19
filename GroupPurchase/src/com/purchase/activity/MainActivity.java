package com.purchase.activity;


import com.matrix.grouppurchase.R;
import com.purchase.fragment.AiTaoBaoActivityFragment;
import com.purchase.fragment.MainTabFragment;
import com.purchase.fragment.MainTabFragment2;
import com.purchase.fragment.TaoBaoActivityFragment;
import com.purchase.subfragment.DressFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private String txt_Array[] = {"精选","9.9包邮","逛逛"};
	private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_selfinfo_btn,R.drawable.tab_square_btn};
	
	private Class fragmentArray[] = {MainTabFragment.class,AiTaoBaoActivityFragment.class,TaoBaoActivityFragment.class};
	private FragmentTabHost frg_tabHost;
	//定义一个布局
	private LayoutInflater layoutInflater;
	private RelativeLayout rl_nav,rl_title_left,rl_title_right;
	private static boolean isExit = false; // 定义变量，判断是否退出
	private final static int EXIT = 1;//退出的标志 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_maintab);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main_title);
		
		initTitleView();
		
		layoutInflater = LayoutInflater.from(this);
		int count = txt_Array.length;
		//实例化TabHost对象，得到TabHost
		frg_tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		frg_tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		for(int i=0;i<count;i++){
			//为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = frg_tabHost.newTabSpec(txt_Array[i]).setIndicator(getTabItemView(i));
			//将Tab按钮添加进Tab选项卡中
			frg_tabHost.addTab(tabSpec, fragmentArray[i], null);
			//设置Tab按钮的背景
			frg_tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
		
		frg_tabHost.setCurrentTab(0);
		
		
	}
	
	/**
	 * 给Tab按钮设置图标和文字
	 * @param index
	 * @return
	 */
	private View getTabItemView(int index){
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
		
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		
		imageView.setImageResource(mImageViewArray[index]);
		textView.setText(txt_Array[index]);
		
		return view;
	}
	
	
	
	private void initTitleView() {
		rl_title_left = (RelativeLayout) findViewById(R.id.rel_main_title_left);
		rl_title_right = (RelativeLayout) findViewById(R.id.rel_main_title_right);
		
		rl_title_left.setOnClickListener(this);
		rl_title_right.setOnClickListener(this);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!isExit) {
				isExit = true;
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				// 利用Handler延迟2秒发送更改消息
				mainHandler.sendEmptyMessageDelayed(EXIT, 2000);
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	};
	
	@SuppressLint("HandlerLeak")
	private Handler mainHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == EXIT) {
				isExit = false; // 退出程序
			} 
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//main_title左边的按钮
		case R.id.rel_main_title_left:
			Toast.makeText(this, "左键", Toast.LENGTH_SHORT).show();
			break;
		//main_title右边的按钮
		case R.id.rel_main_title_right:
			Toast.makeText(this, "右键", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
