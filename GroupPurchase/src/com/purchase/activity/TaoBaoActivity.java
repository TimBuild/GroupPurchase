package com.purchase.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.alibaba.sdk.android.AlibabaSDK;
import com.matrix.grouppurchase.R;
import com.purchase.adapter.TaobaoItemAdapter;
import com.purchase.subfragment.TaoBaoFragment;

import static com.purchase.global.Constants.taobaoTitle;

public class TaoBaoActivity extends FragmentActivity implements
		OnItemClickListener {

	private static String TAG = "TaoBaoActivity";

	private ListView mListView;
	public static int mPosition;

	private TaobaoItemAdapter mAdapter;
	private TaoBaoFragment mFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taobao);
		
		initViews();

	}

	private void initViews() {
		mListView = (ListView) findViewById(R.id.lv_taobao);

		mAdapter = new TaobaoItemAdapter(this, taobaoTitle);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(this);

		// 创建Fragment对象
		mFragment = new TaoBaoFragment();
		createFragment(mFragment);

	}

	private void createFragment(TaoBaoFragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fl_taobao, fragment);
		// 通过bundle传值给Fragment
		Bundle bundle = new Bundle();
		bundle.putString(TaoBaoFragment.TAG, taobaoTitle[mPosition]);
		fragment.setArguments(bundle);
		transaction.commit();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		mPosition = position;
		// 刷新adapter
		mAdapter.notifyDataSetChanged();
		for (int i = 0; i < taobaoTitle.length; i++) {
			mFragment = new TaoBaoFragment();
			createFragment(mFragment);

		}
	}

}
