package com.purchase.adapter;

import java.util.ArrayList;
import java.util.List;

import com.matrix.grouppurchase.R;
import com.purchase.entity.SearchAutoData;

import static com.purchase.activity.SearchActivity.SEARCH_HISTORY;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchEdittextAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater mInflater;
	public static int mMaxMatch = 15;// 最多显示多少个选项，负数表示全部
	private OnClickListener mOnClickListener;

	private final Object mLock = new Object();

	private ArrayList<SearchAutoData> mOriginalValues;// 所有的item
	private List<SearchAutoData> mObjects;// 过滤后的item
	private SearchAutoData searchAutoData;
	private static String TAG = "SearchEdittextAdapter";

	private View footerView;
	private LinearLayout lin_search, lin_search_history;

	private boolean ClearFlag = true;// 清空历史搜索标志,true为显示

	public SearchEdittextAdapter(Context context, int mMaxMatch,
			OnClickListener mOnClickListener, View footerView,
			LinearLayout lin_search, LinearLayout lin_search_history) {
		super();
		this.context = context;
		this.mMaxMatch = mMaxMatch;
		this.mOnClickListener = mOnClickListener;
		this.footerView = footerView;
		this.lin_search = lin_search;
		this.lin_search_history = lin_search_history;
		this.mInflater = LayoutInflater.from(context);
		readSearchHistory();
		mObjects = mOriginalValues;
	}

	@Override
	public int getCount() {
		return null == mObjects ? 0 : mObjects.size();
	}

	@Override
	public Object getItem(int position) {
		return null == mObjects ? 0 : mObjects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.item_search, parent, false);
			holder = new ViewHolder();
			holder.content = (TextView) convertView
					.findViewById(R.id.auto_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SearchAutoData data = mObjects.get(position);
		holder.content.setText(data.getContent());
		Log.d(TAG, "ClearFlag:" + ClearFlag);

		return convertView;
	}

	/**
	 * 读取历史搜索记录
	 */
	public void readSearchHistory() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SEARCH_HISTORY, 0);
		String longhistory = sharedPreferences.getString(SEARCH_HISTORY, "");

		if (longhistory.equals("")) {
			mObjects = null;
			ClearFlag = false;
			// return;
		} else {
			ClearFlag = true;
		}
		// ClearFlag=true;
		String[] hisArrays = longhistory.split(",");
		mOriginalValues = new ArrayList<SearchAutoData>();
		Log.d(TAG, "hisArrays.length:" + hisArrays.length);
		// if (hisArrays.length == 1) {
		// return;
		// }
		for (int i = 0; i < hisArrays.length; i++) {
			searchAutoData = new SearchAutoData();
			searchAutoData.setContent(hisArrays[i]);
			mOriginalValues.add(searchAutoData);
		}
	}

	/**
	 * 匹配过滤搜索内容
	 * 
	 * @param prefix
	 *            输入框输入的内容
	 */
	public void performFiltering(CharSequence prefix) {
		if (prefix == null || prefix.length() == 0) {// 搜索框内容为空的时候显示所有的历史记录
			synchronized (mLock) {
				mObjects = mOriginalValues;
			}
		} else {
			String prefixString = prefix.toString();
			int count = mOriginalValues.size();
			ArrayList<SearchAutoData> newValues = new ArrayList<SearchAutoData>(
					count);
			for (int i = 0; i < count; i++) {
				String value = mOriginalValues.get(i).getContent();
				if (value.startsWith(prefixString)) {
					searchAutoData = new SearchAutoData();
					searchAutoData.setContent(value);
					newValues.add(searchAutoData);
				} else {

				}
				if (mMaxMatch > 0) {
					if (newValues.size() > mMaxMatch - 1) {
						break;
					}
				}
			}
			mObjects = newValues;
		}
		notifyDataSetChanged();
	}

	/**
	 * 设置footerview的显示与隐藏
	 */
	public void setFootViewVisiable() {
		if (ClearFlag) {
			lin_search.setVisibility(View.VISIBLE);
			lin_search_history.setVisibility(View.GONE);
		} else {
			lin_search.setVisibility(View.GONE);
			lin_search_history.setVisibility(View.VISIBLE);
		}
	}

	private class ViewHolder {
		TextView content;
	}

}
