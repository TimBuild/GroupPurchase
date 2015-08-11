package com.purchase.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.purchase.entity.AiTaoBao;

public class AiTaoBaoService {

	public static List<AiTaoBao> getTaoBaoGoods(String json) {

		List<AiTaoBao> lists = new ArrayList<AiTaoBao>();

		AiTaoBao good = null;
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				JSONArray jsonArray = jsonObject.getJSONArray("aiTaoBao");
				for (int i = 0; i < jsonArray.length() - 1; i++) {
					// 因为多加了一个空的数组，所以要减一
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					String commission_num = jsonObject2.getString("commission_num");
					String nick = jsonObject2.getString("nick");
					String open_iid = jsonObject2.getString("open_iid");
					String pic_url = jsonObject2.getString("pic_url");
					String price = jsonObject2.getString("price");
					String seller_credit_score = jsonObject2.getString("seller_credit_score");
					String shop_type = jsonObject2.getString("shop_type");
					String title = jsonObject2.getString("title");
					
					good = new AiTaoBao();
					good.setCommission_num(commission_num);
					good.setNick(nick);
					good.setOpen_iid(open_iid);
					good.setPic_url(pic_url);
					good.setPrice(price);
					good.setSeller_credit_score(seller_credit_score);
					good.setShop_type(shop_type);
					good.setTitle(title);
					
					lists.add(good);
					
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return lists;

	}

}
