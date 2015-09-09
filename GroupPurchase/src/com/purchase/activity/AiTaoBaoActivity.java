package com.purchase.activity;

import static com.purchase.global.Constants.AiTAOBAO_URL;
import static com.purchase.global.Constants.PAGE_SIZE;
import static com.purchase.adapter.AiTaoGridViewAdapter.TITLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.ItemService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.matrix.grouppurchase.R;
import com.purchase.adapter.AiTaobaoBaseAdapter;
import com.purchase.entity.AiTaoBao;
import com.purchase.service.AiTaoBaoService;
import com.purchase.util.HttpUtil;
import com.purchase.util.NetWorkUtil;
import com.purchase.view.ProgressDialog;
import com.taobao.tae.sdk.webview.TaeWebViewUiSettings;

public class AiTaoBaoActivity extends Activity implements
		OnRefreshListener2<ListView>, OnClickListener {

	private static String TAG = "AiTaoBaoActivity";
	private AiTaobaoBaseAdapter taobaoAdapter;

	private PullToRefreshListView mPullToRefreshListView;
	private TextView tv_title;
	private RelativeLayout rl_back;

	private TextView tv_zonghe, tv_xiaoliang, tv_jiage;
	private LinearLayout lin_jiage;// 显示上下按钮的Linearlayout组件
	private LinearLayout lin_nodata;//显示没有数据的Linearlayout组件
	private ImageView iv_jiage_up, iv_jiage_down;// 显示价格优先上下的组件

	private final static int LOAD = 1;
	private final static int REFRESH = 2;

	private List<AiTaoBao> listResults = new ArrayList<AiTaoBao>();

	private Dialog pDialog;

	private int page_start = 1;
	private int page_size = PAGE_SIZE;
	
	private String title;//keyword,也就是标题

	private String sort_aitaobao = "credit_desc";
	private String[] sorts = new String[]{"credit_desc","commissionNum_desc","price_desc","price_asc"}; 
	/**
	 * 判断点击价格排序的次数
	 */
	private Integer jiageNum = 0;
	
	private int mScreenWidth;//屏幕宽度

	// private String tagId;
	// private String title;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			@SuppressWarnings("unchecked")
			List<AiTaoBao> lists = (List<AiTaoBao>) msg.obj;
			
			switch (msg.what) {
			case REFRESH:
				
				if(lists.size()==0){
//					Log.d(TAG, "----->"+lists.toString()+"-lists==null:"+(lists==null)+"-lists.size():"+lists.size());
					lin_nodata.setVisibility(View.VISIBLE);
				}else{
					lin_nodata.setVisibility(View.GONE);
					listResults.clear();
					listResults.addAll(0, lists);
					taobaoAdapter.setGoodsData(listResults);
					mPullToRefreshListView.onRefreshComplete();
					taobaoAdapter.notifyDataSetChanged();
					
					mPullToRefreshListView.getRefreshableView().setSelection(0);
				}

				break;

			case LOAD:
				listResults.addAll(lists);
				taobaoAdapter.setGoodsData(listResults);
				mPullToRefreshListView.onRefreshComplete();
				taobaoAdapter.notifyDataSetChanged();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_selection_ai_taobao);
//		AlibabaSDK.asyncInit(this);
		
		//获得屏幕宽度
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		mScreenWidth = size.x;//获得屏幕宽度
		
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.dressGridView);
		tv_title = (TextView) findViewById(R.id.title_menu_text);
		rl_back = (RelativeLayout) findViewById(R.id.rl_back);

		tv_zonghe = (TextView) findViewById(R.id.tv_zonghe);
		tv_xiaoliang = (TextView) findViewById(R.id.tv_xiaoliang);
		tv_jiage = (TextView) findViewById(R.id.tv_jiage);

		lin_jiage = (LinearLayout) findViewById(R.id.lin_jiage);
		iv_jiage_up = (ImageView) findViewById(R.id.iv_jiage_up);
		iv_jiage_down = (ImageView) findViewById(R.id.iv_jiage_down);
		
		lin_nodata = (LinearLayout) findViewById(R.id.lin_noMsg);

		tv_zonghe.setOnClickListener(this);
		tv_xiaoliang.setOnClickListener(this);
		tv_jiage.setOnClickListener(this);

		// 初始化数据源
		initIndicator();

		// tagId = getIntent().getExtras().getString(TAGID, "3212");
		title = getIntent().getExtras().getString(TITLE,"");

		tv_title.setText(title);

		taobaoAdapter = new AiTaobaoBaseAdapter(this, mPullToRefreshListView);
		mPullToRefreshListView.setAdapter(taobaoAdapter);
		taobaoAdapter.setScreenWidth(mScreenWidth);

		mPullToRefreshListView.setOnRefreshListener(this);

		pDialog = ProgressDialog.createLoadingDialog(this, "正在刷新中...");

		if (NetWorkUtil.isNetworkAvailable(this)) {
			new GetMessage(REFRESH).execute(sort_aitaobao);
		} else {
			NetWorkUtil.showNoNetWorkDialog(this);
		}
		mPullToRefreshListView
				.setOnItemClickListener(new GridViewItemClickListner());

		// 返回上级菜单
		rl_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.in_from_left,
						R.anim.out_from_right);
			}
		});

	};

	private class GridViewItemClickListner implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.d(TAG, "GridViewItemClickListner:"
					+ listResults.get(position).toString());
			AiTaoBao goods = listResults.get(position);
			int type;
			if (goods.getShop_type().equals("B")) {
				type = 1;// 天猫
			} else {
				type = 0;// 淘宝
			}
			showTaokeItemDetail(goods.getOpen_iid(), type);

		}

	}

	protected void showTaokeItemDetail(String open_id, int type) {
		TaeWebViewUiSettings taeWebViewUiSettings = new TaeWebViewUiSettings();
		// taeWebViewUiSettings.title = "jsjdjijdijwidj";
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
		ILoadingLayout startLayout = mPullToRefreshListView
				.getLoadingLayoutProxy(true, false);
		startLayout.setPullLabel("下拉刷新");
		startLayout.setRefreshingLabel("正在刷新...");
		startLayout.setReleaseLabel("松开刷新");

		ILoadingLayout endLayout = mPullToRefreshListView
				.getLoadingLayoutProxy(false, true);
		endLayout.setPullLabel("上拉加载更多");
		endLayout.setRefreshingLabel("正在加载...");
		endLayout.setReleaseLabel("松开加载");
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetMessage(REFRESH).execute(sort_aitaobao);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetMessage(LOAD).execute(sort_aitaobao);
	}

	private class GetMessage extends AsyncTask<String, Void, List<AiTaoBao>> {

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
		protected List<AiTaoBao> doInBackground(String... params) {

			List<AiTaoBao> lists = null;
			if (NetWorkUtil.isNetworkAvailable(AiTaoBaoActivity.this)) {
				Map<String, String> map = new HashMap<String, String>();

				if (method == REFRESH) {
					page_start = 1;
				} else if (method == LOAD) {
					page_start = page_start + 1;
				}
				Log.i(TAG, "page_start:" + page_start + ",page_size:"
						+ page_size + ",sortString:" + params[0]+",title:"+title);
				// map.put("tagIds", tagId);
				// @FormParam("keyword"),@FormParam("page_no"),@FormParam("page_size"),@FormParam("sort")
				map.put("keyword", title);
				map.put("page_no", String.valueOf(page_start));
				map.put("page_size", String.valueOf(page_size));
				map.put("sort", params[0]);
				// 从服务器中获取数据
				String result = HttpUtil.doPost(map, AiTAOBAO_URL);
				lists = AiTaoBaoService.getTaoBaoGoods(result);
				Log.i(TAG, "淘宝：" + lists.toString());
			} else {
				Log.e(TAG, "请打开网络连接!");
			}
			// Log.d(TAG, dressList.toString());

			return lists;
		}

		@Override
		protected void onPostExecute(List<AiTaoBao> result) {
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_zonghe:// 综合排序

			sort_aitaobao = sorts[0];
			lin_jiage.setVisibility(View.GONE);
			tv_zonghe.setTextColor(getResources().getColor(
					R.color.textview_click_true));
			tv_xiaoliang.setTextColor(getResources().getColor(
					R.color.textview_click_false));
			tv_jiage.setTextColor(getResources().getColor(
					R.color.textview_click_false));
			new GetMessage(REFRESH).execute(sort_aitaobao);

			break;
		case R.id.tv_xiaoliang:// 销量
			sort_aitaobao = sorts[1];
			lin_jiage.setVisibility(View.GONE);
			tv_xiaoliang.setTextColor(getResources().getColor(
					R.color.textview_click_true));
			tv_zonghe.setTextColor(getResources().getColor(
					R.color.textview_click_false));
			tv_jiage.setTextColor(getResources().getColor(
					R.color.textview_click_false));
			new GetMessage(REFRESH).execute(sort_aitaobao);
			break;
		case R.id.tv_jiage:// 价格排序

			tv_jiage.setTextColor(getResources().getColor(
					R.color.textview_click_true));
			tv_xiaoliang.setTextColor(getResources().getColor(
					R.color.textview_click_false));
			tv_zonghe.setTextColor(getResources().getColor(
					R.color.textview_click_false));
			lin_jiage.setVisibility(View.VISIBLE);
			jiageNum++;// 每点击一次，次数+1；
			if (jiageNum % 2 == 0) {// 偶数次代表价格降序排序,价格从高到低
				iv_jiage_down
						.setImageResource(R.drawable.iv_navagation_down_red);
				iv_jiage_up.setImageResource(R.drawable.iv_navagation_up_gray);
				sort_aitaobao = sorts[2];
			} else {
				iv_jiage_up.setImageResource(R.drawable.iv_navagation_up_red);
				iv_jiage_down
						.setImageResource(R.drawable.iv_navagation_down_gray);
				sort_aitaobao = sorts[3];
			}
			new GetMessage(REFRESH).execute(sort_aitaobao);
			break;

		default:
			break;
		}
	}

}
