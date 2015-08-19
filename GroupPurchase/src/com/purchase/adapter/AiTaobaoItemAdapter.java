package com.purchase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matrix.grouppurchase.R;
import com.purchase.fragment.AiTaoBaoActivityFragment;

public class AiTaobaoItemAdapter extends BaseAdapter {

	private Context context;
	private String[] strs;
	private LayoutInflater inflater;

	public AiTaobaoItemAdapter(Context context, String[] strs) {
		this.context = context;
		this.strs = strs;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return strs.length;
	}

	@Override
	public Object getItem(int position) {
		return strs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.item_listview_taobao, null);

		TextView tv = (TextView) convertView.findViewById(R.id.tv_taobao);
		tv.setText(strs[position]);
		if (position == AiTaoBaoActivityFragment.mPosition) {
			convertView.setBackgroundResource(R.drawable.taobao_item_select_bg);
			tv.setTextColor(Color.parseColor("#ee3939"));
		} else {
			convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
		}

		return convertView;
	}

}
