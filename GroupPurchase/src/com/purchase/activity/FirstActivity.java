package com.purchase.activity;

import com.matrix.grouppurchase.R;
import com.purchase.fragment.AiTaoBaoActivityFragment;
import com.purchase.fragment.MainTabFragment;
import com.purchase.fragment.TaoBaoActivityFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstActivity extends FragmentActivity implements OnClickListener {

	// 三个tab布局
	private RelativeLayout knowLayout, iWantKnowLayout, meLayout;

	// 底部标签切换的Fragment
	private Fragment knowFragment, iWantKnowFragment, meFragment,
			currentFragment;
	// 底部标签图片
	private ImageView knowImg, iWantKnowImg, meImg;
	// 底部标签的文本
	private TextView knowTv, iWantKnowTv, meTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_first);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_main_title);

		initUI();
		initTab();
	}

	/**
	 * 初始化底部标签
	 */
	private void initTab() {
		if (knowFragment == null) {
			knowFragment = new MainTabFragment();
		}
		if (!knowFragment.isAdded()) {
			// 提交事务
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_layout, knowFragment).commit();
			// 记录当前Fragment
			currentFragment = knowFragment;
			// 设置图片文本的变化
			knowImg.setImageResource(R.drawable.icon_home_sel);
			knowTv.setTextColor(getResources().getColor(
					R.color.bottom_tab_press));
			iWantKnowImg.setImageResource(R.drawable.icon_selfinfo_nor);
			iWantKnowTv.setTextColor(getResources().getColor(
					R.color.bottom_tab_normal));
			meImg.setImageResource(R.drawable.icon_square_nor);
			meTv.setTextColor(getResources()
					.getColor(R.color.bottom_tab_normal));

		}
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {
		knowLayout = (RelativeLayout) findViewById(R.id.rl_know);
		iWantKnowLayout = (RelativeLayout) findViewById(R.id.rl_want_know);
		meLayout = (RelativeLayout) findViewById(R.id.rl_me);
		knowLayout.setOnClickListener(this);
		iWantKnowLayout.setOnClickListener(this);
		meLayout.setOnClickListener(this);

		knowImg = (ImageView) findViewById(R.id.iv_know);
		iWantKnowImg = (ImageView) findViewById(R.id.iv_i_want_know);
		meImg = (ImageView) findViewById(R.id.iv_me);
		knowTv = (TextView) findViewById(R.id.tv_know);
		iWantKnowTv = (TextView) findViewById(R.id.tv_i_want_know);
		meTv = (TextView) findViewById(R.id.tv_me);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_know: // 知道
			clickTab1Layout();
			break;
		case R.id.rl_want_know: // 我想知道
			clickTab2Layout();
			break;
		case R.id.rl_me: // 我的
			clickTab3Layout();
			break;

		default:
			break;
		}
	}

	/**
	 * 点击第三个Tab
	 */
	private void clickTab3Layout() {
		if (meFragment == null) {
			meFragment = new TaoBaoActivityFragment();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(),
				meFragment);
		// 设置底部Tab变化

		knowImg.setImageResource(R.drawable.icon_home_nor);
		knowTv.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
		iWantKnowImg.setImageResource(R.drawable.icon_selfinfo_nor);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottom_tab_normal));
		meImg.setImageResource(R.drawable.icon_square_sel);
		meTv.setTextColor(getResources().getColor(R.color.bottom_tab_press));
	}

	/**
	 * 点击第二个Tab
	 */
	private void clickTab2Layout() {
		if (iWantKnowFragment == null) {
			iWantKnowFragment = new AiTaoBaoActivityFragment();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(),
				iWantKnowFragment);
		// 设置底部Tab变化

		knowImg.setImageResource(R.drawable.icon_home_nor);
		knowTv.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
		iWantKnowImg.setImageResource(R.drawable.icon_selfinfo_sel);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottom_tab_press));
		meImg.setImageResource(R.drawable.icon_square_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottom_tab_normal));

	}

	/**
	 * 点击第一个Tab
	 */
	private void clickTab1Layout() {
		if (knowFragment == null) {
			knowFragment = new MainTabFragment();
		}

		addOrShowFragment(getSupportFragmentManager().beginTransaction(),
				knowFragment);
		// 设置底部Tab变化

		knowImg.setImageResource(R.drawable.icon_home_sel);
		knowTv.setTextColor(getResources().getColor(R.color.bottom_tab_press));
		iWantKnowImg.setImageResource(R.drawable.icon_selfinfo_nor);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottom_tab_normal));
		meImg.setImageResource(R.drawable.icon_square_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottom_tab_normal));
	}

	/**
	 * 添加或显示碎片
	 * 
	 * @param beginTransaction
	 * @param fragment
	 */
	private void addOrShowFragment(FragmentTransaction beginTransaction,
			Fragment fragment) {
		if (currentFragment == fragment) {
			return;
		}
		if (!fragment.isAdded()) {// 如果当前fragment未被添加，则添加到Fragment管理器中
			beginTransaction.hide(currentFragment)
					.add(R.id.content_layout, fragment).commit();
		} else {
			beginTransaction.hide(currentFragment).show(fragment).commit();
		}
		currentFragment = fragment;
	}

}
