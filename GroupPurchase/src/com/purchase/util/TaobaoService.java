package com.purchase.util;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.ItemService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.taobao.tae.sdk.webview.TaeWebViewUiSettings;

import android.app.Activity;
import android.content.Context;

public class TaobaoService {

	/**
	 * 跳转到淘宝详细列表界面
	 * 
	 * @param activity
	 * @param open_id
	 * @param type
	 */
	public static void showTaokeItemDetail(Activity activity, String open_id,
			int type) {
		TaeWebViewUiSettings taeWebViewUiSettings = new TaeWebViewUiSettings();
		ItemService service = AlibabaSDK.getService(ItemService.class);
		service.showItemDetailByOpenItemId(activity,
				new TradeProcessCallback() {

					@Override
					public void onFailure(int arg0, String arg1) {

					}

					@Override
					public void onPaySuccess(TradeResult arg0) {

					}
				}, taeWebViewUiSettings, open_id, type, null);
	}
}
