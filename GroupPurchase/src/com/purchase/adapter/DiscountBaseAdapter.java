package com.purchase.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matrix.grouppurchase.R;
import com.purchase.entity.DiscountBao;
import com.purchase.util.ImageUtil;
import com.squareup.picasso.Picasso;

public class DiscountBaseAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;

	private PullToRefreshGridView mGridView;
	private ViewHolder holder;
	private List<DiscountBao> mDressLists;

	private static String TAG = "DressBaseAdapter";
	private int mScreenWidth;

	public DiscountBaseAdapter(Context context, PullToRefreshGridView gridView) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.mGridView = gridView;
		this.mDressLists = new ArrayList<DiscountBao>();
	}

	public void setGoodsData(List<DiscountBao> lists) {
		this.mDressLists = lists;
	}

	public void setScreenWidth(int mScreenWidth) {
		this.mScreenWidth = (mScreenWidth - 10) / 2;//屏幕宽度的一半
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

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_fragment_discount,
					null);
			holder.iv_left = (ImageView) convertView.findViewById(R.id.iv_left);
			holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
			holder.tv_price_info = (TextView) convertView
					.findViewById(R.id.tv_price);
			holder.tv_coupon_price = (TextView) convertView.findViewById(R.id.tv_coupon_price);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
//		 Log.d(TAG, "------>"+mScreenWidth);
		if (mDressLists != null && mDressLists.size() != 0) {
			DiscountBao discount = mDressLists.get(position);
			// 给图片设置固定的大小
			Picasso.with(context).load(discount.getPic_url())
					.placeholder(R.drawable.discount_blank)
					.error(R.drawable.discount_blank_error)
					.resize(mScreenWidth, mScreenWidth).centerCrop()
					.into(holder.iv_left);
			
			holder.tv_des.setText(discount.getTitle());
			holder.tv_price_info.setText(discount.getPrice());
			holder.tv_coupon_price.setText(discount.getCoupon_price());
			holder.tv_price_info.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		}
		return convertView;
	}

	static class ViewHolder {
		// 图片
		private ImageView iv_left;
		// 所属平台图片
		// private ImageView iv_right;
		// 文字描述
		private TextView tv_des;
		// 价格
		private TextView tv_price_info;
		// 折扣价格
		private TextView tv_coupon_price;
	}

}
