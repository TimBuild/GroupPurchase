package com.purchase.fragment;

import static com.purchase.global.Constants.taobaoTitle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.alibaba.sdk.android.AlibabaSDK;
import com.matrix.grouppurchase.R;
import com.purchase.adapter.TaobaoItemAdapter;
import com.purchase.subfragment.TaoBaoFragment;

public class TaoBaoActivityFragment extends Fragment implements
		OnItemClickListener {

	private static String TAG = "TaoBaoActivityFragment";

	private ListView mListView;
	public static int mPosition;

	private TaobaoItemAdapter mAdapter;
	private TaoBaoFragment mFragment;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_taobao, container,false);
		
		initViews(rootView);
		//注册sdk，否则sdk无法调用
		AlibabaSDK.asyncInit(getActivity());
		return rootView;
	}


	private void initViews(View view) {
		mListView = (ListView) view.findViewById(R.id.lv_taobao);

		mAdapter = new TaobaoItemAdapter(getActivity(), taobaoTitle);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(this);

		// 创建Fragment对象
		mFragment = new TaoBaoFragment();
		createFragment(mFragment);

	}

	private void createFragment(TaoBaoFragment fragment) {
		FragmentTransaction transaction = getFragmentManager()
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
