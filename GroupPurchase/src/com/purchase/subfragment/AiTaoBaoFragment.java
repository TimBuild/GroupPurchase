package com.purchase.subfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.matrix.grouppurchase.R;
import com.purchase.adapter.AiTaoGridViewAdapter;;

public class AiTaoBaoFragment extends Fragment{
	public static final String TAG = "AiTaoBaoFragment";
	private String str;
	
	private GridView mGridView;
	
	private AiTaoGridViewAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gridview_taobao, container,false);
		
		mGridView = (GridView) view.findViewById(R.id.gv_taobao);
		mAdapter = new AiTaoGridViewAdapter(getActivity(),mGridView);
		
		//得到数据
		str = getArguments().getString(TAG);
		mAdapter.setString(str);
		
		mGridView.setAdapter(mAdapter);
		

		return view;
	}
}
