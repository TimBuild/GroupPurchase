package com.purchase.activity;

import java.util.ArrayList;
import java.util.Arrays;

import static com.purchase.adapter.SearchEdittextAdapter.mMaxMatch;

import com.matrix.grouppurchase.R;
import com.purchase.adapter.SearchEdittextAdapter;
import com.purchase.entity.SearchAutoData;
import com.purchase.util.DialogUtil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SearchActivity extends Activity implements OnClickListener {

	public static final String SEARCH_HISTORY = "search_edittext_history";

	private ListView lv_search;
	private EditText et_search;
	private Button bt_search;// 搜索按钮
	private Button search_clear;// 清空搜索历史
	private ImageView iv_delete;// 删除图标按钮
	
	private LinearLayout lin_search,lin_search_history;
	
	private View footerView;

	private static String TAG = "SearchActivity";
	private SearchEdittextAdapter mSearchAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		init();

		mSearchAdapter = new SearchEdittextAdapter(this, mMaxMatch, this,footerView,lin_search,lin_search_history);
		mSearchAdapter.setFootViewVisiable();
		lv_search.setAdapter(mSearchAdapter);
		lv_search.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SearchAutoData data = (SearchAutoData) mSearchAdapter
						.getItem(position);
				et_search.setText(data.getContent());
				bt_search.performClick();
			}
		});

	}

	private void init() {
		lin_search = (LinearLayout) findViewById(R.id.lin_search);
		lin_search_history = (LinearLayout) findViewById(R.id.lin_search_history);
		lv_search = (ListView) findViewById(R.id.lv_search);
		footerView= getLayoutInflater().inflate(R.layout.footer_button,
				null);
		lv_search.addFooterView(footerView);
		search_clear = (Button) footerView.findViewById(R.id.search_clear);
		search_clear.setOnClickListener(this);
		et_search = (EditText) findViewById(R.id.etSearch);
		bt_search = (Button) findViewById(R.id.btnSearch);
		iv_delete = (ImageView) findViewById(R.id.ivDeleteText);
		bt_search.setVisibility(View.VISIBLE);
		iv_delete.setOnClickListener(this);
		et_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mSearchAdapter.performFiltering(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					iv_delete.setVisibility(View.GONE);
				} else {
					iv_delete.setVisibility(View.VISIBLE);
				}
			}
		});

		bt_search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivDeleteText:
			// 删除按钮图标事件
			et_search.setText("");
			break;

		case R.id.btnSearch:
			// 搜索事件
			Log.d(TAG, et_search.getText().toString());
			saveSearchHistory();
			mSearchAdapter.readSearchHistory();
			mSearchAdapter.setFootViewVisiable();
			break;
		case R.id.search_clear:
			clearSearchHistory();
			mSearchAdapter.readSearchHistory();
			mSearchAdapter.setFootViewVisiable();
			mSearchAdapter.notifyDataSetChanged();
			break;
		}
	}

	/**
	 * 清空历史记录
	 */
	private void clearSearchHistory() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				SEARCH_HISTORY, 0);

		Editor editor = sharedPreferences.edit();
		editor.putString(SEARCH_HISTORY, "");
		editor.commit();

	}

	/**
	 * 保存历史记录
	 */
	private void saveSearchHistory() {
		String text = et_search.getText().toString();
		if (text.length() < 1) {
			return;
		}
		SharedPreferences sharedPreferences = getSharedPreferences(
				SEARCH_HISTORY, 0);
		String longhistory = sharedPreferences.getString(SEARCH_HISTORY, "");
		String[] tmpHistory = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
//		Log.i(TAG, "history.size():" + history.size());
		if (history.size() > 0) {
			for (int i = 0; i < history.size(); i++) {
				if (text.equals(history.get(i))) {
					history.remove(i);
					break;
				}
			}
			history.add(0, text);
		}
		if (history.size() > mMaxMatch) {
			history.remove(mMaxMatch);
		}
//		DialogUtil.showCustomDialog(this, history.toString());
//		Log.i(TAG, "ArrayList<String>:" + history.toString()+"history.size()-->"+history.size());
		if (history.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) {
				if(!history.get(i).equals("")){
					sb.append(history.get(i) + ",");
					Log.d(TAG, "StringBuilder:"+sb.toString());
				}
			}
			sharedPreferences.edit().putString(SEARCH_HISTORY, sb.toString())
					.commit();
		} else {
			sharedPreferences.edit().putString(SEARCH_HISTORY, text + ",")
					.commit();
		}

	}
}
