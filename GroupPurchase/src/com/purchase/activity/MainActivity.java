package com.purchase.activity;

import com.matrix.grouppurchase.R;
import com.purchase.adapter.TabFragmentPagerAdapter;
import com.purchase.view.SyncHorizontalScrollView;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class MainActivity extends FragmentActivity {

//	private LinearLayout lin_main, lin_tab, lin_nav;
	private RelativeLayout rl_nav;
	private ImageView iv_nav_left, iv_nav_right, iv_nav_indicator;
	private SyncHorizontalScrollView shScrollView;
	private RadioGroup rg_nav_content;
	private ViewPager mViewPager;
	private int indicatorWidth;
	private LayoutInflater mInflater;

	public static final String ARGUMENTS_NAME = "arg";
	public static String[] tabTitle = { "选项1", "选项2", "选项3", "选项4", "选项5","音乐" }; // 标题

	private TabFragmentPagerAdapter mAdapter;
	private int currentIndicatorLeft = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
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
}
