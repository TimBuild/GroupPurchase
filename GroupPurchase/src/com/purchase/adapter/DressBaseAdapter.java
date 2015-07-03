package com.purchase.adapter;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matrix.grouppurchase.R;
import com.purchase.entity.Dress;
import com.purchase.view.DressListView;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DressBaseAdapter extends BaseAdapter{
	
	private Context context;
	private LayoutInflater mInflater;
	private DressListView dressListView;
	
	private PullToRefreshGridView mGridView;
	private ViewHolder holder;
	private List<Dress> mDressLists;
	
	private static String TAG = "DressBaseAdapter";
	/*public DressBaseAdapter(Context context,DressListView dressListView){
		this.context = context;
		this.dressListView = dressListView;
	}*/
	public DressBaseAdapter(Context context,PullToRefreshGridView gridView){
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.mGridView = gridView;
		this.mDressLists = new ArrayList<Dress>();
	}
	
	public void setDressData(List<Dress> lists){
//		this.mDressLists.clear();
//		this.mDressLists.addAll(lists);
		this.mDressLists = lists;
	}

	@Override
	public int getCount() {
		return mDressLists.size();
	}

	@Override
	public Object getItem(int position) {
		return mDressLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_fragment_dress, null);
			holder.iv_left = (ImageView) convertView.findViewById(R.id.iv_left);
			holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
			holder.tv_price_info = (TextView) convertView.findViewById(R.id.tv_price_info);
			holder.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
//		Log.d(TAG, "------>"+mDressLists.toString());
		if(mDressLists!=null&&mDressLists.size() != 0){
			Dress dress = mDressLists.get(position);
			Picasso.with(context).load(dress.getImageUrl()).into(holder.iv_left);
			holder.tv_des.setText(dress.getImageTitle());
			holder.tv_price_info.setText(dress.getPrice_discount());
			holder.tv_info.setText(dress.getPrice());
		}
//		Log.d(TAG, "---position--->"+position);
		return convertView;
	}
	
	
	public class ViewHolder{
		//图片
		private ImageView iv_left;
		//文字描述
		private TextView tv_des;
		//价格
		private TextView tv_price_info;
		//原价
		private TextView tv_info;
	}

}
