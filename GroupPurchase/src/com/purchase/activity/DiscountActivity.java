package com.purchase.activity;

import com.alibaba.sdk.android.AlibabaSDK;
import com.matrix.grouppurchase.R;
import com.purchase.adapter.DiscountFragmentPagerAdapter;
import com.purchase.view.SyncHorizontalScrollView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class DiscountActivity extends FragmentActivity implements OnClickListener{

	private RelativeLayout rl_nav,rl_title_left,rl_title_right;
	private ImageView iv_nav_left, iv_nav_right, iv_nav_indicator;
	private SyncHorizontalScrollView shScrollView;
	private RadioGroup rg_nav_content;
	private ViewPager mViewPager;
	private int indicatorWidth;
	private LayoutInflater mInflater;

	public static final String TITLE_NAME = "title_name";
	public static String[] tabTitle = { "服装", "居家", "母婴", "美食", "鞋包配饰","美妆","数码电器","文体" }; // 标题

	private DiscountFragmentPagerAdapter mAdapter;
	private int currentIndicatorLeft = 0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main_title);
		AlibabaSDK.asyncInit(this);
		
		initTitleView();
		initView();
		setListener();
		
	}
	
	private void setListener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (rg_nav_content != null
						&& rg_nav_content.getChildCount() > position) {
					((RadioButton) rg_nav_content.getChildAt(position))
							.performClick();
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		rg_nav_content
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (rg_nav_content.getChildAt(checkedId) != null) {
							TranslateAnimation animation = new TranslateAnimation(
									currentIndicatorLeft,
									((RadioButton) rg_nav_content
											.getChildAt(checkedId)).getLeft(),
									0f, 0f);
							animation.setInterpolator(new LinearInterpolator());
							animation.setDuration(100);
							animation.setFillAfter(true);

							// 执行位移动画
							iv_nav_indicator.startAnimation(animation);

							mViewPager.setCurrentItem(checkedId);// ViewPager跟随一起切换

							// 记录当前下标最左侧的距离
							currentIndicatorLeft = ((RadioButton) rg_nav_content
									.getChildAt(checkedId)).getLeft();

							shScrollView.smoothScrollTo(
											(checkedId > 1 ? ((RadioButton) rg_nav_content.getChildAt(checkedId)).getLeft() : 0)- ((RadioButton) rg_nav_content.getChildAt(2)).getLeft(), 0);
						}
					}
				});
		mViewPager.setOffscreenPageLimit(1);
	}
	
	private void initTitleView() {
		rl_title_left = (RelativeLayout) findViewById(R.id.rel_main_title_left);
		rl_title_right = (RelativeLayout) findViewById(R.id.rel_main_title_right);
		
		rl_title_left.setOnClickListener(this);
		rl_title_right.setOnClickListener(this);
	}
	
	private void initView() {
		rl_nav = (RelativeLayout) findViewById(R.id.rel_nav);

		shScrollView = (SyncHorizontalScrollView) findViewById(R.id.mHsv);

		rg_nav_content = (RadioGroup) findViewById(R.id.rg_nav_content);

		iv_nav_indicator = (ImageView) findViewById(R.id.iv_nav_indicator);
		iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left);
		iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right);

		mViewPager = (ViewPager) findViewById(R.id.main_viewpager);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		indicatorWidth = dm.widthPixels / 4;

		LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
		cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
		iv_nav_indicator.setLayoutParams(cursor_Params);

		shScrollView.setSomeParam(rl_nav, iv_nav_left, iv_nav_right, this);

		// 获取布局填充器
		mInflater = LayoutInflater.from(this);
		initNavigationSV();

		mAdapter = new DiscountFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
	}
	
	private void initNavigationSV() {
		rg_nav_content.removeAllViews();
		for (int i = 0; i < tabTitle.length; i++) {
			RadioButton rb = (RadioButton) mInflater.inflate(
					R.layout.nav_radiogroup_item, null);
			rb.setId(i);
			rb.setText(tabTitle[i]);
			rb.setLayoutParams(new LayoutParams(indicatorWidth,
					LayoutParams.MATCH_PARENT));
			rg_nav_content.addView(rb);
		}
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//main_title左边的按钮
		case R.id.rel_main_title_left:
			finish();
			break;
		//main_title右边的按钮
		case R.id.rel_main_title_right:
			finish();
			break;
		}		
	}

}
