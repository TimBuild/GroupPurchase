package com.purchase.adapter;

import com.purchase.activity.MainActivity;
import com.purchase.fragment.CommonUIFragment;
import com.purchase.fragment.LaunchUIFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.purchase.activity.MainActivity.tabTitle;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

	public TabFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment ft = null;
		switch (arg0) {
		case 0:
			ft = new LaunchUIFragment();
			break;

		default:
			ft = new CommonUIFragment();
			Bundle args = new Bundle();
			args.putString(MainActivity.ARGUMENTS_NAME, tabTitle[arg0]);
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
