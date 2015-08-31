package com.purchase.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.purchase.entity.DiscountBao;


public class DiscountService {

	/**
	 * @param json
	 * @return 爱淘宝折扣商品List集合
	 */
	public static List<DiscountBao> getDiscountGoods(String json) {

		List<DiscountBao> lists = new ArrayList<DiscountBao>();

		DiscountBao good = null;
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json);
//				System.out.println("discount:"+jsonObject.toString());
				JSONArray jsonArray = jsonObject.getJSONArray("discountBao");
				for (int i = 0; i < jsonArray.length() - 1; i++) {
					// 因为多加了一个空的数组，所以要减一
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					String nick = jsonObject2.getString("nick");
					String open_iid = jsonObject2.getString("open_iid");
					String pic_url = jsonObject2.getString("pic_url");
					String price = jsonObject2.getString("price");
					String coupon_price = jsonObject2.getString("coupon_price");
					String shop_type = jsonObject2.getString("shop_type");
					String title = jsonObject2.getString("title");
					
					good = new DiscountBao();
					good.setNick(nick);
					good.setOpen_iid(open_iid);
					good.setPic_url(pic_url);
					good.setPrice(price);
					good.setShop_type(shop_type);
					good.setTitle(title);
					good.setCoupon_price(coupon_price);
					
					lists.add(good);
					
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return lists;

	}

}
