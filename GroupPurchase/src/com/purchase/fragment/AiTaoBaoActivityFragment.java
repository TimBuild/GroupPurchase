package com.purchase.fragment;

import static com.purchase.global.Constants.aitaobaoTitle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.sdk.android.AlibabaSDK;
import com.matrix.grouppurchase.R;
import com.purchase.activity.SearchActivity;
import com.purchase.adapter.AiTaobaoItemAdapter;
import com.purchase.subfragment.AiTaoBaoFragment;

public class AiTaoBaoActivityFragment extends Fragment implements
		OnItemClickListener {

	private static String TAG = "AiTaoBaoActivityFragment";

	private ListView mListView;
	public static int mPosition;

	private AiTaobaoItemAdapter mAdapter;
	private AiTaoBaoFragment mFragment;
	
	private EditText et_search;
//	private RelativeLayout rl_search;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_aitaobao, container,false);
		
		initViews(rootView);
		//注册sdk，否则sdk无法调用
		AlibabaSDK.asyncInit(getActivity());
		return rootView;
	}


	private void initViews(View view) {
		mListView = (ListView) view.findViewById(R.id.lv_taobao);

		mAdapter = new AiTaobaoItemAdapter(getActivity(), aitaobaoTitle);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(this);
		
		et_search = (EditText) view.findViewById(R.id.etSearch);
//		rl_search = (RelativeLayout) view.findViewById(R.id.top);
		et_search.setFocusable(false);
		et_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});

		// 创建Fragment对象
		mFragment = new AiTaoBaoFragment();
		createFragment(mFragment);

	}

	private void createFragment(AiTaoBaoFragment fragment) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fl_taobao, fragment);
		// 通过bundle传值给Fragment
		Bundle bundle = new Bundle();
		bundle.putString(AiTaoBaoFragment.TAG, aitaobaoTitle[mPosition]);
		fragment.setArguments(bundle);
		transaction.commit();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		mPosition = position;
		// 刷新adapter
		mAdapter.notifyDataSetChanged();
		for (int i = 0; i < aitaobaoTitle.length; i++) {
			mFragment = new AiTaoBaoFragment();
			createFragment(mFragment);

		}
	}

}
