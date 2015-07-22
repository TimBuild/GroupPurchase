package com.purchase.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.purchase.entity.GoodsItem;

public class GoodsItemService {

	public static List<GoodsItem> getGoodsItemByJson(String json) {

		List<GoodsItem> listGoods = new ArrayList<GoodsItem>();

		GoodsItem good = null;

		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				JSONArray jsonArray = jsonObject.getJSONArray("goodsItem");

				for (int i = 0; i < jsonArray.length()-1; i++) {
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					String discount_price = (String) jsonObject2
							.get("discount_price");
					String open_iid = (String) jsonObject2.get("open_iid");
					String pic_url = (String) jsonObject2.get("pic_url");
					String pics = (String) jsonObject2.get("pics");
					String price = (String) jsonObject2.get("price");
					String properties_and_values = (String) jsonObject2
							.get("properties_and_values");
					String shop_type = (String) jsonObject2.get("shop_type");
					String tag_id = (String) jsonObject2.get("tag_id");
					String title = (String) jsonObject2.get("title");
					good = new GoodsItem();
					good.setDiscount_price(discount_price);
					good.setOpen_iid(open_iid);
					good.setPic_url(pic_url);
					good.setPics(pics);
					good.setPrice(price);
					good.setProperties_and_values(properties_and_values);
					good.setShop_type(shop_type);
					good.setTag_id(tag_id);
					good.setTitle(title);

					listGoods.add(good);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return listGoods;

	}
}
