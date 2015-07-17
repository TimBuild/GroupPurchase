package com.purchase.global;

import com.matrix.grouppurchase.R;

public class Constants {

	public final static String BASE_URL = "http://www.jiukuaiyou.com/jiu/";

	public final static String URL_REFRESH = "/whole/1";
	public final static String URL_LOAD = "/whole/2";

	// public final static String TAOBAO_URL =
	// "http://192.168.1.69:8081/PurchaseServer/rest/GoodsService/getGoods";
	public final static String TAOBAO_URL = "http://lavie-1.wx.jaeapp.com/rest/GoodsService/getGoods";

	/**
	 * 每页最多显示多少条数据
	 */
	public final static int PAGE_SIZE = 20;

	/**
	 * 男装
	 */
	public static String[] man = new String[] { "背心/马甲", "衬衫", "风衣", "夹克",
			"毛呢大衣", "棉裤", "棉衣", "牛仔裤", "皮裤", "皮衣", "Polo衫", "T恤", "卫衣", "西服",
			"休闲裤", "羽绒服", "羽绒裤", "针织衫" };
	public static int[] man_img = new int[] { R.drawable.man_beixin,
			R.drawable.man_chenshan, R.drawable.man_fengyi,
			R.drawable.man_jiake, R.drawable.man_maonidayi,
			R.drawable.man_mianku, R.drawable.man_mianyi,
			R.drawable.man_niuzaiku, R.drawable.man_piku, R.drawable.man_piyi,
			R.drawable.man_polo, R.drawable.man_txu, R.drawable.man_weiyi,
			R.drawable.man_xifu, R.drawable.man_xiuxianku,
			R.drawable.man_yurongfu, R.drawable.man_yurongku,
			R.drawable.man_zhenzhishan };
	public static String[] clothing = new String[] { "pic1", "pic2", "pic3",
			"pic4", "pic5", "pic6", "pic7" };
	public static int[] clothing_img = new int[] { R.drawable.man_beixin,
			R.drawable.man_beixin, R.drawable.man_beixin,
			R.drawable.man_beixin, R.drawable.man_chenshan,
			R.drawable.man_beixin, R.drawable.man_beixin };
	public static String[] meihzuang = new String[] { "pic1", "pic2", "pic3",
			"pic4", "pic5", "pic6", "pic7", "pic7", "pic8", "pic9", "pic7",
			"pic8", "pic9" };
	public static int[] meihzuang_img = new int[] { R.drawable.man_chenshan,
			R.drawable.man_chenshan, R.drawable.man_chenshan,
			R.drawable.man_chenshan, R.drawable.man_chenshan,
			R.drawable.man_chenshan, R.drawable.man_chenshan,
			R.drawable.man_chenshan, R.drawable.man_chenshan,
			R.drawable.man_chenshan, R.drawable.man_chenshan,
			R.drawable.man_chenshan, R.drawable.man_chenshan };

}
