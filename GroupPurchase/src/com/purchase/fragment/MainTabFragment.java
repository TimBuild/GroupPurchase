package com.purchase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.matrix.grouppurchase.R;
import com.purchase.activity.AiTaoBaoActivity;
import com.purchase.activity.NinePostageActivity;

public class MainTabFragment extends Fragment {

	private Button but;
	private Button but_ai;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.test_test, container, false);

		but = (Button) rootView.findViewById(R.id.turn);
		but_ai = (Button) rootView.findViewById(R.id.turnToAiTao);

		but.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), NinePostageActivity.class);
				startActivity(intent);
			}
		});
		
		/*but_ai.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AiTaoBaoActivity.class);
				startActivity(intent);
			}
		});*/

		return rootView;

	}

}
