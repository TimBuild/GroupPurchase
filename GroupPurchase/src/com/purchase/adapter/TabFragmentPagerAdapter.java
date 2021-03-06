package com.purchase.adapter;

import com.purchase.activity.NinePostageActivity;
import com.purchase.subfragment.DressFragment;
import com.purchase.subfragment.HouseFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import static com.purchase.activity.NinePostageActivity.tabTitle;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

	public TabFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment ft = null;
		switch (arg0) {
		case 0://服装
			ft = new DressFragment();
			break;
		default:
			ft = new HouseFragment();
			Bundle args = new Bundle();
			args.putString(NinePostageActivity.TITLE_NAME, tabTitle[arg0]);
			ft.setArguments(args);
			break;
		}
		return ft;
	}

	@Override
	public int getCount() {
		return tabTitle.length;
	}

}
