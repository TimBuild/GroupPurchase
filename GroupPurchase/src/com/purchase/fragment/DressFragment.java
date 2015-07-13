package com.purchase.fragment;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.matrix.grouppurchase.R;
import com.purchase.activity.DressActivity;
import com.purchase.activity.DressWebView;
import com.purchase.adapter.DressBaseAdapter;
import com.purchase.entity.Dress;
import com.purchase.server.DressManager;
import com.purchase.util.NetWorkUtil;
import com.purchase.view.DressListView;
import com.purchase.view.ProgressDialog;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

public class DressFragment extends Fragment implements OnRefreshListener2<GridView>{
	
	
	private static String TAG = "DressFragment";
//	private DressListView dressListView;
	private DressBaseAdapter dressAdapter;
	
	private PullToRefreshGridView mPullToRefreshGridView;
	
	private final static int LOAD = 1;
	private final static int REFRESH = 2;
	
	private boolean LOAD_FLAG = true;
	
	private List<Dress> listResults = new ArrayList<Dress>();
	
//	private MyHandler handler;
	
	private List<Dress> listExamples = new ArrayList<Dress>();
	
	private Dialog pDialog;
	
	private String path;
	
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
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Log.d(TAG, "setUserVisibleHint:"+isVisibleToUser);
		if(isVisibleToUser){
			pDialog = ProgressDialog.createLoadingDialog(getActivity(), "正在刷新中...");
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_selection_dress, container,false);
		//得到控件
		mPullToRefreshGridView = (PullToRefreshGridView) rootView.findViewById(R.id.dressGridView);
//		initData();
		
		//初始化数据源
		initIndicator();
		
//		pDialog = ProgressDialog.createLoadingDialog(getActivity(), "正在刷新中...");
		
		
		dressAdapter = new DressBaseAdapter(getActivity(),mPullToRefreshGridView);
		mPullToRefreshGridView.setAdapter(dressAdapter);
		
		
		
		mPullToRefreshGridView.setOnRefreshListener(this);
		
//		loadData(REFRESH);
		if(NetWorkUtil.isNetworkAvailable(getActivity())){
			new GetMessage(REFRESH).execute();
		}else{
			NetWorkUtil.showNoNetWorkDialog(getActivity());
		}
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
			
			/*Intent intent = new Intent(getActivity(),DressActivity.class);
			Dress dress = listResults.get(position);
			String imageUrl = dress.getImageUrl();
			String imagePath = dress.getImagePath();
			String imageTitle = dress.getImageTitle();
			String price_discount = dress.getPrice_discount();
			String price = dress.getPrice();
			intent.putExtra("dress_url", imageUrl);
			intent.putExtra("dress_path", imagePath);
			intent.putExtra("dress_title", imageTitle);
			intent.putExtra("dress_discount", price_discount);
			intent.putExtra("dress_price", price);*/
			Intent intent = new Intent(getActivity(),DressWebView.class);
			Dress dress = listResults.get(position);
			String path = dress.getImagePath();
			intent.putExtra("path", path);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
			
//			new GetDress(intent).execute(dress.getImagePath());
//			intent.putExtra("path", path);
//			startActivity(intent);
			
			
			
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
		
		new GetMessage(REFRESH).execute();
	}

	/* 加载更多
	 * @see com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2#onPullUpToRefresh(com.handmark.pulltorefresh.library.PullToRefreshBase)
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		Log.d(TAG, "==onPullDownToRefresh加载更多==");
		
		new GetMessage(LOAD).execute();
	}
	
	
	private class GetMessage extends AsyncTask<Integer, Void, List<Dress>>{
		
		private Integer method;
		public GetMessage(Integer params){
			this.method = params;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if(pDialog!=null){
				pDialog.show();
			}
		}

		@Override
		protected List<Dress> doInBackground(Integer... params) {
			
			List<Dress> dressList = null;
			if(NetWorkUtil.isNetworkAvailable(getActivity())){
				
				if(method == REFRESH){
					dressList = DressManager.getDressByUrl(DressManager.dress_url);
				}else if(method == LOAD){
					if(LOAD_FLAG == true){
						dressList = DressManager.getDressByUrl(DressManager.dress_url_load);
						LOAD_FLAG = false;
					}else{
						dressList = new ArrayList<Dress>();
					}
				}
			}else{
				Log.e(TAG, "请打开网络连接!");
			}
//			Log.d(TAG, dressList.toString());
			
			return dressList;
		}
		
		@Override
		protected void onPostExecute(List<Dress> result) {
			super.onPostExecute(result);
//			Log.d(TAG, "result:"+result);
			if(pDialog!=null){
				pDialog.dismiss();
				pDialog=null;
			}
			if(result!=null){
				Message msg = handler.obtainMessage();
				msg.what = method;
				msg.obj = result;
				handler.sendMessage(msg);
			}
			
		}
		
	}
	
	
}
