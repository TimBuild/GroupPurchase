package com.purchase.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.matrix.grouppurchase.R;

public class NetWorkUtil {
	/**
	 * 判断是不是有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {

		} else {
			// 如果仅仅是用来判断网络连接,则可以使用 cm.getActiveNetworkInfo().isAvailable();
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}

		}
		return false;
	}

	/**
	 * 连接网络连接的对话框
	 * 
	 * @param context
	 */
	public static void showNoNetWorkDialog(final Context context) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setIcon(R.drawable.network).setTitle("设置网络").setMessage("当前无网络")
				.setPositiveButton("设置", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 跳转到系统的网络设置界面
						Intent intent = new Intent(
								android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						context.startActivity(intent);

					}
				}).setNegativeButton("退出程序", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						((Activity) context).finish();
					}
				}).show();
	}
}
