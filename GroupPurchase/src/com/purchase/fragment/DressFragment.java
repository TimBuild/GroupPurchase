package com.purchase.fragment;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matrix.grouppurchase.R;
import com.purchase.adapter.DressBaseAdapter;
import com.purchase.entity.Dress;
import com.purchase.server.DressManager;
import com.purchase.view.DressListView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class DressFragment extends Fragment implements OnRefreshListener2<GridView>{
	
	
	private static String TAG = "DressFragment";
//	private DressListView dressListView;
	private DressBaseAdapter dressAdapter;
	
	private PullToRefreshGridView mPullToRefreshGridView;
	
	private final static int LOAD = 1;
	private final static int REFRESH = 2;
	
	private List<Dress> listResults = new ArrayList<Dress>();
	
//	private MyHandler handler;
	
	private List<Dress> listExamples = new ArrayList<Dress>();
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			List<Dress> lists = (List<Dress>) msg.obj;
			switch (msg.what) {
			case REFRESH:
				
				listResults.clear();
				listResults.addAll(0, lists);
				dressAdapter.setDressData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;

			case LOAD:
				listResults.addAll(lists);
				dressAdapter.setDressData(listResults);
				mPullToRefreshGridView.onRefreshComplete();
				break;
			}
			dressAdapter.notifyDataSetChanged();
		};
	};
	
//	private class MyHandler extends Handler{
//		
//		private DressBaseAdapter dressAdapter;
//		public MyHandler(){
//			
//		}
//		public MyHandler(DressBaseAdapter dressAdapter) {
//			super();
//			this.dressAdapter = dressAdapter;
//		}
//		
////		public MyHandler(Looper looper){
////			super(looper);
////		}
//		
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case REFRESH:
//				List<Dress> lists = (List<Dress>) msg.obj;
//				Log.d(TAG, "------>"+lists.toString());
//				dressAdapter.setDressData(lists);
//				dressAdapter.notifyDataSetChanged();
//				break;
//
//			case LOAD:
//				break;
//			}
//		}
//		
//	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_selection_dress, container,false);
		//得到控件
		mPullToRefreshGridView = (PullToRefreshGridView) rootView.findViewById(R.id.dressGridView);
//		initData();
		
		//初始化数据源
		initIndicator();
		
		
		
		
		dressAdapter = new DressBaseAdapter(getActivity(),mPullToRefreshGridView);
		mPullToRefreshGridView.setAdapter(dressAdapter);
		
		
		
		mPullToRefreshGridView.setOnRefreshListener(this);
		
//		loadData(REFRESH);
		new GetMessage(REFRESH).execute(REFRESH);
		mPullToRefreshGridView.setOnItemClickListener(new GridViewItemClickListner());
		
		return rootView;
	}

//	private void initData() {
//		for(int i=0;i<5;i++){
//			Dress dress = new Dress();
//			dress.setImagePath("http: //www.jiukuaiyou.com/deal/76001624");
//			dress.setImageTitle("情侣纯棉短袖卡通T恤");
//			dress.setImageUrl("http: //s1.juancdn.com/bao/150702/9/f/5594d316d6dd5_400x400.jpg_285x285.jpg");
//			dress.setPrice("￥45(1.2折)");
//			dress.setPrice_discount("￥5.6");
//			listExamples.add(dress);
//		}		
//	}
	
	private class GridViewItemClickListner implements OnItemClickListener{
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Log.d(TAG, "GridViewItemClickListner:"+listResults.get(position).toString());
		}
		
	}

	/**
	 * 设置刷新和加载的文本
	 */
	private void initIndicator() {
		ILoadingLayout startLayout = mPullToRefreshGridView.getLoadingLayoutProxy(true, false);
		startLayout.setPullLabel("下拉刷新");
		startLayout.setRefreshingLabel("正在刷新...");
		startLayout.setReleaseLabel("松开刷新");
		
		ILoadingLayout endLayout = mPullToRefreshGridView.getLoadingLayoutProxy(false, true);
		endLayout.setPullLabel("上拉加载更多");
		endLayout.setRefreshingLabel("正在加载...");
		endLayout.setReleaseLabel("松开加载");
	}

	/* 下拉刷新
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2#onPullDownToRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		Log.d(TAG, "==onPullDownToRefresh刷新==");
		
//		loadData(REFRESH);
		new GetMessage(REFRESH).execute(REFRESH);
	}

	/* 加载更多
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2#onPullUpToRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		Log.d(TAG, "==onPullDownToRefresh加载更多==");
		
//		loadData(LOAD);
		new GetMessage(LOAD).execute(LOAD);
	}
	
	
	/*private void loadData(final int what){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Message msg = handler.obtainMessage();
				msg.what = what;
				List<Dress> dressList = DressManager.getDressByUrl(DressManager.url);
				Log.d(TAG, "loadData:-->"+dressList.size());
				msg.obj = dressList;
				handler.sendMessage(msg);
			}
		}).start();
	}*/
	
	private class GetMessage extends AsyncTask<Integer, Void, List<Dress>>{
		
		private Integer method;
		public GetMessage(Integer params){
			this.method = params;
		}

		@Override
		protected List<Dress> doInBackground(Integer... params) {
			
			List<Dress> dressList = null;
			if(params[0] == REFRESH){
				dressList = DressManager.getDressByUrl(DressManager.url);
			}else if(params[0]==LOAD){
				dressList = DressManager.getDressByUrl(DressManager.url_load);
			}
//			Log.d(TAG, dressList.toString());
			
			return dressList;
		}
		
		@Override
		protected void onPostExecute(List<Dress> result) {
			super.onPostExecute(result);
//			Log.d(TAG, "result:"+result);
			if(result!=null){
				Message msg = handler.obtainMessage();
				msg.what = method;
				msg.obj = result;
				handler.sendMessage(msg);
			}
			
//			mPullToRefreshGridView.onRefreshComplete();
		}
		
	}
	
	
}
