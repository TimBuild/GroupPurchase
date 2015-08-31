package com.purchase.subfragment;

import static com.purchase.global.Constants.Discount_URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matrix.grouppurchase.R;
import com.purchase.activity.DiscountActivity;
import com.purchase.adapter.DiscountBaseAdapter;
import com.purchase.entity.DiscountBao;
import com.purchase.service.DiscountService;
import com.purchase.util.HttpUtil;
import com.purchase.util.NetWorkUtil;
import com.purchase.util.TaobaoService;
import com.purchase.view.ProgressDialog;

public class DiscountFragment extends Fragment implements
		OnRefreshListener2<GridView> {

	private static String TAG = "DiscountFragment";
	// private DressListView dressListView;
	private DiscountBaseAdapter mAdapter;

	private PullToRefreshGridView mPullToRefreshGridView;

	private final static int LOAD = 1;
	private final static int REFRESH = 2;

	private boolean LOAD_FLAG = true;

	private List<DiscountBao> listResults = new ArrayList<DiscountBao>();

	// private MyHandler handler;

	private List<DiscountBao> listExamples = new ArrayList<DiscountBao>();

	private Dialog pDialog;

	private String path;

	private String sort_aitaobao = "credit_desc";

	private int page_start = 1;
	private int page_size = 20;

	private String title;

	private int mScreenWidth;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			@SuppressWarnings("unchecked")
			List<DiscountBao> lists = (List<DiscountBao>) msg.obj;

			switch (msg.what) {
			case REFRESH:

				listResults.clear();
				listResults.addAll(0, lists);
				mAdapter.setGoodsData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;

			case LOAD:
				listResults.addAll(lists);
				mAdapter.setGoodsData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;
			}
			mAdapter.notifyDataSetChanged();
		};
	};

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Log.d(TAG, "setUserVisibleHint:" + isVisibleToUser);
		if (isVisibleToUser) {
			pDialog = ProgressDialog.createLoadingDialog(getActivity(),
					"正在刷新中...");
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_selection_discount,
				container, false);
		// 得到控件
		mPullToRefreshGridView = (PullToRefreshGridView) rootView
				.findViewById(R.id.mGridView);
		// initData();

		// 初始化数据源
		initIndicator();

		Bundle bundle = getArguments();
		title = bundle.getString(DiscountActivity.TITLE_NAME, "");

		mAdapter = new DiscountBaseAdapter(getActivity(),
				mPullToRefreshGridView);
		mPullToRefreshGridView.setAdapter(mAdapter);
		mAdapter.setScreenWidth(mScreenWidth);

		mPullToRefreshGridView.setOnRefreshListener(this);

		// loadData(REFRESH);
		if (NetWorkUtil.isNetworkAvailable(getActivity())) {
			new GetMessage(REFRESH).execute();
		} else {
			NetWorkUtil.showNoNetWorkDialog(getActivity());
		}
		mPullToRefreshGridView
				.setOnItemClickListener(new GridViewItemClickListner());

		return rootView;
	}

	private class GridViewItemClickListner implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			DiscountBao discountBao = listResults.get(position);
			int type;
			if (discountBao.getShop_type().equals("B")) {
				type = 1;// 天猫
			} else {
				type = 0;
			}
			TaobaoService.showTaokeItemDetail(getActivity(),
					discountBao.getOpen_iid(), type);
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		// mScreenWidth = display.getWidth()/2;
		// WindowManager wm = (WindowManager)
		// getActivity().getSystemService(Context.WINDOW_SERVICE);
		Point size = new Point();
		display.getSize(size);
		mScreenWidth = size.x;// 获取屏幕的宽度
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

	/*
	 * 下拉刷新
	 * 
	 * @see
	 * com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
	 * #onPullDownToRefresh
	 * (com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		Log.d(TAG, "==onPullDownToRefresh刷新==");

		new GetMessage(REFRESH).execute();
	}

	/*
	 * 加载更多
	 * 
	 * @see
	 * com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
	 * #onPullUpToRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		Log.d(TAG, "==onPullDownToRefresh加载更多==");

		new GetMessage(LOAD).execute();
	}

	private class GetMessage extends
			AsyncTask<Integer, Void, List<DiscountBao>> {

		private Integer method;

		public GetMessage(Integer params) {
			this.method = params;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pDialog != null) {
				pDialog.show();
			}
		}

		@Override
		protected List<DiscountBao> doInBackground(Integer... params) {

			List<DiscountBao> dressList = null;
			if (NetWorkUtil.isNetworkAvailable(getActivity())) {
				Map<String, String> map = new HashMap<String, String>();
				if (method == REFRESH) {
					page_start = 1;
				} else if (method == LOAD) {
					page_start = page_start + 1;
				}
				map.put("keyword", title);
				map.put("page_no", String.valueOf(page_start));
				map.put("page_size", String.valueOf(page_size));
				map.put("sort", sort_aitaobao);

				String result = HttpUtil.doPost(map, Discount_URL);
				dressList = DiscountService.getDiscountGoods(result);
			} else {
				Log.e(TAG, "请打开网络连接!");
			}
			// Log.d(TAG, dressList.toString());

			return dressList;
		}

		@Override
		protected void onPostExecute(List<DiscountBao> result) {
			super.onPostExecute(result);
			// Log.d(TAG, "result:"+result);
			if (pDialog != null) {
				pDialog.dismiss();
				pDialog = null;
			}
			if (result != null) {
				Message msg = handler.obtainMessage();
				msg.what = method;
				msg.obj = result;
				handler.sendMessage(msg);
			}

		}

	}

}
