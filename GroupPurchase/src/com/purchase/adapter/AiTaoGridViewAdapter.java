package com.purchase.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.matrix.grouppurchase.R;
import com.purchase.activity.AiTaoBaoActivity;
import com.purchase.entity.TaoBaoSort;
import com.purchase.entity.TaoPicture;
import com.purchase.global.Constants;

public class AiTaoGridViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private String str;
	private ViewHolder holder;
	private GridView mGridView;
	
	private static String TAG = "AiTaoGridViewAdapter";
	
//	public static String TAGID = "GridView_tagid";
	public static String TITLE = "AiTaoGridViewAdapter_title";

	private String[] titles;
	private int[] images;
	private List<TaoPicture> pictures;

	public AiTaoGridViewAdapter(Context context,GridView gridview) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.mGridView = gridview;
	}

	@Override
	public int getCount() {
		if(null!=pictures){
			return pictures.size();
		}else{
			return 0;
		}
			
	}

	@Override
	public Object getItem(int position) {
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void setString(String str){
		this.str = str;
		pictures = new ArrayList<TaoPicture>();
		if(str.equals("男士")){
			titles = Constants.man;
			images = Constants.man_img;
		}else if(str.equals("女装")){
			titles = Constants.woman;
			images = Constants.woman_img;
		}else if(str.equals("鞋子")){
			titles = Constants.shoe;
			images = Constants.shoe_img;
		}else if(str.equals("包包")){
			titles = Constants.baobao;
			images = Constants.baobao_img;
		}else if(str.equals("配饰")){
			titles = Constants.peishi;
			images = Constants.peishi_img;
		}else if(str.equals("美妆")){
			titles = Constants.meihzuang;
			images = Constants.meihzuang_img;
		}else if(str.equals("家居")){
			titles = Constants.jiaju;
			images = Constants.jiaju_img;
		}else if(str.equals("母婴")){
			titles = Constants.muying;
			images = Constants.muying_img;
		}else if(str.equals("美食")){
			titles = Constants.meishi;
			images = Constants.meishi_img;
		}
		
		for(int i=0;i<titles.length;i++){
			TaoPicture picture = new TaoPicture(titles[i], images[i]);
			pictures.add(picture);
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.fragment_selection_taobao_show, null);
			
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_image);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_title.setText(pictures.get(position).getTitle());
		holder.iv_icon.setImageResource(pictures.get(position).getImageId());
		
		//对GridView设置事件监听，跳转到activity中
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String title  =  pictures.get(position).getTitle();
				
				String tagId = TaoBaoSort.getTagsId(title);
				
				Intent intent = new Intent(context, AiTaoBaoActivity.class);
//				intent.putExtra(TAGID, tagId);
				intent.putExtra(TITLE, title);
				
				context.startActivity(intent);
				
//				Toast.makeText(context,tagId, Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}
	
	
	class ViewHolder{
		public TextView tv_title;
		public ImageView iv_icon;
	}

}
