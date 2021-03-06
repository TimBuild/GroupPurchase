package com.purchase.activity;

import static com.purchase.global.Constants.PAGE_SIZE;
import static com.purchase.global.Constants.TAOBAO_URL;
import static com.purchase.adapter.TaoGridViewAdapter.TAGID;
import static com.purchase.adapter.TaoGridViewAdapter.TITLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.ItemService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.matrix.grouppurchase.R;
import com.purchase.adapter.TaoGridViewAdapter;
import com.purchase.adapter.TaobaoBaseAdapter;
import com.purchase.entity.GoodsItem;
import com.purchase.service.GoodsItemService;
import com.purchase.util.HttpUtil;
import com.purchase.util.NetWorkUtil;
import com.purchase.view.ProgressDialog;
import com.taobao.tae.sdk.webview.TaeWebViewUiSettings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TaoBaoItemActivity extends Activity implements
		OnRefreshListener2<GridView> {

	private static String TAG = "TaoBaoFragment";
	private TaobaoBaseAdapter taobaoAdapter;

	private PullToRefreshGridView mPullToRefreshGridView;
	private TextView tv_title;
	private RelativeLayout rl_back;

	private final static int LOAD = 1;
	private final static int REFRESH = 2;

	private List<GoodsItem> listResults = new ArrayList<GoodsItem>();

	private Dialog pDialog;

	private int page_start = 0;
	private int page_size = PAGE_SIZE;

	private String tagId;
	private String title;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			@SuppressWarnings("unchecked")
			List<GoodsItem> lists = (List<GoodsItem>) msg.obj;
			switch (msg.what) {
			case REFRESH:

				listResults.clear();
				listResults.addAll(0, lists);
				taobaoAdapter.setGoodsData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;

			case LOAD:
				listResults.addAll(lists);
				taobaoAdapter.setGoodsData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;
			}
			taobaoAdapter.notifyDataSetChanged();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_selection_taobao);
		mPullToRefreshGridView = (PullToRefreshGridView) findViewById(R.id.dressGridView);
		tv_title = (TextView) findViewById(R.id.title_menu_text);
		rl_back = (RelativeLayout) findViewById(R.id.rl_back);
//		rl_click = (RelativeLayout) findViewById(R.id.rl_click);
		
		// 初始化数据源
		initIndicator();
		
		tagId = getIntent().getExtras().getString(TAGID, "3212");
		title = getIntent().getExtras().getString(TITLE,"");
		
		tv_title.setText(title);

		taobaoAdapter = new TaobaoBaseAdapter(this, mPullToRefreshGridView);
		mPullToRefreshGridView.setAdapter(taobaoAdapter);

		mPullToRefreshGridView.setOnRefreshListener(this);
		
		pDialog = ProgressDialog.createLoadingDialog(this, "正在刷新中...");

		if (NetWorkUtil.isNetworkAvailable(this)) {
			new GetMessage(REFRESH).execute();
		} else {
			NetWorkUtil.showNoNetWorkDialog(this);
		}
		mPullToRefreshGridView
				.setOnItemClickListener(new GridViewItemClickListner());
		
		//返回上级菜单
		rl_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
			}
		});

	};

	private class GridViewItemClickListner implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.d(TAG, "GridViewItemClickListner:"
					+ listResults.get(position).toString());
			GoodsItem goods = listResults.get(position);
			showTaokeItemDetail(goods.getOpen_iid(),
					Integer.parseInt(goods.getShop_type()));

		}

	}

	protected void showTaokeItemDetail(String open_id, int type) {
		TaeWebViewUiSettings taeWebViewUiSettings = new TaeWebViewUiSettings();
//		taeWebViewUiSettings.title = "jsjdjijdijwidj";
		ItemService service = AlibabaSDK.getService(ItemService.class);
		service.showItemDetailByOpenItemId(this, new TradeProcessCallback() {

			@Override
			public void onFailure(int arg0, String arg1) {

			}

			@Override
			public void onPaySuccess(TradeResult arg0) {

			}
		}, taeWebViewUiSettings, open_id, type, null);

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

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		new GetMessage(REFRESH).execute();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		new GetMessage(LOAD).execute();
	}

	private class GetMessage extends AsyncTask<Integer, Void, List<GoodsItem>> {

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
		protected List<GoodsItem> doInBackground(Integer... params) {

			List<GoodsItem> lists = null;
			if (NetWorkUtil.isNetworkAvailable(TaoBaoItemActivity.this)) {
				Map<String, String> map = new HashMap<String, String>();

				if (method == REFRESH) {
					page_start = 0;
				} else if (method == LOAD) {
					page_start = page_start + 1;
				}
				Log.i(TAG, "page_start:" + page_start + ",page_size:"
						+ page_size);
				map.put("tagIds", tagId);
				map.put("pageSize", String.valueOf(page_size));
				map.put("page", String.valueOf(page_start));
				// 从服务器中获取数据
				String result = HttpUtil.doPost(map, TAOBAO_URL);
				lists = GoodsItemService.getGoodsItemByJson(result);
				Log.i(TAG, "淘宝：" + lists.size());
			} else {
				Log.e(TAG, "请打开网络连接!");
			}
			// Log.d(TAG, dressList.toString());

			return lists;
		}

		@Override
		protected void onPostExecute(List<GoodsItem> result) {
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
