package com.purchase.fragment;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.matrix.grouppurchase.R;
import com.purchase.activity.DressWebView;
import com.purchase.adapter.HouseBaseAdapter;
import com.purchase.entity.House;
import com.purchase.server.HouseManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class HouseFragment extends Fragment implements
		OnRefreshListener2<GridView> {

	private static String TAG = "HouseFragment";

	private HouseBaseAdapter houseAdapter;
	private PullToRefreshGridView mPullToRefreshGridView;

	private final static int LOAD = 1;
	private final static int REFRESH = 2;

	private boolean LOAD_FLAG = true;

	private List<House> listResults = new ArrayList<House>();

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			@SuppressWarnings("unchecked")
			List<House> lists = (List<House>) msg.obj;

			switch (msg.what) {
			case REFRESH:
				listResults.clear();
				listResults.addAll(0, lists);
				houseAdapter.setHouseData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;

			case LOAD:
				listResults.addAll(lists);
				houseAdapter.setHouseData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;
			}
			houseAdapter.notifyDataSetChanged();
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_selection_house,
				container, false);
		// 得到控件
		mPullToRefreshGridView = (PullToRefreshGridView) rootView
				.findViewById(R.id.houseGridView);

		// 初始化数据源
		initIndicator();

		houseAdapter = new HouseBaseAdapter(getActivity(),
				mPullToRefreshGridView);
		mPullToRefreshGridView.setAdapter(houseAdapter);

		mPullToRefreshGridView.setOnRefreshListener(this);

		new GetHouse(REFRESH).execute();
		
		mPullToRefreshGridView.setOnItemClickListener(new GridViewItemClickListener());

		return rootView;
	}
	
	private class GridViewItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(getActivity(), DressWebView.class);
			String path = listResults.get(position).getImagePath();
			intent.putExtra("path", path);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
		}
		
	}

	/**
	 * 设置刷新和加载的文本
	 */
	private void initIndicator() {
		ILoadingLayout startLayout = mPullToRefreshGridView
				.getLoadingLayoutProxy(true, false);
		startLayout.setPullLabel("下拉刷新");
		startLayout.setRefreshingLabel("正在刷新...");
		startLayout.setReleaseLabel("松开刷新");

		ILoadingLayout endLayout = mPullToRefreshGridView
				.getLoadingLayoutProxy(false, true);
		endLayout.setPullLabel("上拉加载更多");
		endLayout.setRefreshingLabel("正在加载...");
		endLayout.setReleaseLabel("松开加载");
	}

	private class GetHouse extends AsyncTask<Integer, Void, List<House>> {

		private Integer method;

		public GetHouse(Integer params) {
			this.method = params;
		}

		@Override
		protected List<House> doInBackground(Integer... params) {
			List<House> houses = null;

			if (method == REFRESH) {

				houses = HouseManager
						.getHouseByUrl(HouseManager.house_url_refresh);
			} else if (method == LOAD) {
				if (LOAD_FLAG == true) {
					houses = HouseManager
							.getHouseByUrl(HouseManager.house_url_load);
					LOAD_FLAG = false;
				} else {
					houses = new ArrayList<House>();
				}
			}
			Log.d(TAG, houses.toString());
			return houses;
		}

		@Override
		protected void onPostExecute(List<House> result) {
			super.onPostExecute(result);
			if (result != null) {
				Message msg = handler.obtainMessage();
				msg.what = method;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		}

	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		new GetHouse(REFRESH).execute();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		new GetHouse(LOAD).execute();
	}
}
