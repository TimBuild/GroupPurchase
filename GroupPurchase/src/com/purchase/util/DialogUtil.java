package com.purchase.util;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtil {

	/**
	 * 显示组件的对话框
	 * 
	 * @param context
	 * @param message
	 */
	public static void showCustomDialog(Context context, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setNegativeButton("取消", null);
		builder.setPositiveButton("确定", null);
		builder.show();
	}
}
