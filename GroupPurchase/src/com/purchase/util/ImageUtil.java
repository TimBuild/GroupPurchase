package com.purchase.util;

import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class ImageUtil {

	/**
	 * @param imageView
	 *            (ImageView)
	 * @param mScreenWidth
	 *            (屏幕宽度)
	 * @return 返回一个正方形尺寸大小的Imageview(固定大小)
	 */
	public static void resizeSquareImage(ImageView imageView, int mScreenWidth) {
		LayoutParams params = imageView.getLayoutParams();

		params.width = (mScreenWidth - 10) / 2;
		params.height = (mScreenWidth - 10) / 2;
		imageView.setLayoutParams(params);
	}
}
