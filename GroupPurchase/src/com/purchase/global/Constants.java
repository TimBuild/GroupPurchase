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
	 * 分类显示的标题
	 */
	public static String[] taobaoTitle = { "女装", "鞋子", "包包", "配饰", "男士" };
	/**
	 * 男装
	 */
	public static String[] man = new String[] { "背心/马甲", "衬衫", "风衣", "夹克",
			"毛呢大衣", "棉裤", "棉衣", "牛仔裤", "皮裤", "皮衣", "Polo衫", "T恤", "卫衣", "西服",
			"休闲裤", "羽绒服", "羽绒裤", "针织衫", "男靴", "男鞋", "男包", "打火机", "剃须刀", "手表",
			"皮带", "男士护理", "男士香水" };
	public static int[] man_img = new int[] { R.drawable.man_beixin,
			R.drawable.man_chenshan, R.drawable.man_fengyi,
			R.drawable.man_jiake, R.drawable.man_maonidayi,
			R.drawable.man_mianku, R.drawable.man_mianyi,
			R.drawable.man_niuzaiku, R.drawable.man_piku, R.drawable.man_piyi,
			R.drawable.man_polo, R.drawable.man_txu, R.drawable.man_weiyi,
			R.drawable.man_xifu, R.drawable.man_xiuxianku,
			R.drawable.man_yurongfu, R.drawable.man_yurongku,
			R.drawable.man_zhenzhishan, R.drawable.man_nanxue,
			R.drawable.man_nanxie, R.drawable.man_nanbao,
			R.drawable.man_dahuoji, R.drawable.man_tixudao,
			R.drawable.man_shoubiao, R.drawable.man_pidai, R.drawable.man_huli,
			R.drawable.man_xiangshui };
	public static String[] woman = new String[] { "连衣裙", "女士T恤", "女士衬衫",
			"女士休闲裤", "女士西裤", "打底裤", "女士牛仔裤", "半身裙", "女士马甲", "雪纺衫/蕾丝衫", "毛针织衫",
			"短外套", "女士毛衣", "女士风衣", "女士羽绒服", "女士棉服", "女士卫衣", "女士西装", "毛呢外套", "吊带背心", "长裙", "背带裤", "铅笔裤" };
	public static int[] woman_img = new int[] { R.drawable.woman_lianyiqun,
			R.drawable.woman_txu, R.drawable.woman_chenshan,
			R.drawable.woman_xiuxianku, R.drawable.woman_xiku,
			R.drawable.woman_dadiku, R.drawable.woman_niuzai,
			R.drawable.woman_banshenqun, R.drawable.woman_majia,
			R.drawable.woman_xuefangshan, R.drawable.woman_maozhenzhishan,
			R.drawable.woman_duanwaitao, R.drawable.woman_maoyi,
			R.drawable.woman_fengyi, R.drawable.woman_yurongfu,
			R.drawable.woman_mianfu, R.drawable.woman_weiyi,
			R.drawable.woman_xizhuang, R.drawable.woman_maoniwaitao,
			R.drawable.woman_diaodai, R.drawable.woman_changqun,
			R.drawable.woman_beidai, R.drawable.woman_qianbi };
	public static String[] shoe = new String[] { "低帮鞋", "高帮鞋", "靴子", "凉鞋",
			"拖鞋", "帆布鞋", "雨鞋", "篮球鞋", "足球鞋", "网球鞋", "休闲鞋/板鞋", "运动鞋", "跑步鞋",
			"童鞋/青少年鞋", "羽毛球鞋", "其他运动鞋" };
	public static int[] shoe_img = new int[] { R.drawable.shoe_dibang,
			R.drawable.shoe_gao, R.drawable.shoe_xuezi,
			R.drawable.shoe_liangxie, R.drawable.shoe_tuoxie,
			R.drawable.shoe_fanbu, R.drawable.shoe_yu, R.drawable.shoe_lanqiu,
			R.drawable.shoe_zuqiu, R.drawable.shoe_wangqiu,
			R.drawable.shoe_xiuxian, R.drawable.shoe_yundong,
			R.drawable.shoe_paobu, R.drawable.shoe_tong,
			R.drawable.shoe_yumaoqiu, R.drawable.shoe_qita };
	public static String[] baobao = new String[] { "化妆包", "钱包", "卡包", "单肩包",
			"行李箱", "手提包", "链条包", "大包", "斜挎包", "韩版包", "手拿包", "双肩包", "贝壳包",
			"流苏包", "水桶包", "邮差包", "帆布包" };
	public static int[] baobao_img = new int[] { R.drawable.bao_huazhuang,
			R.drawable.bao_qian, R.drawable.bao_ka, R.drawable.bao_danjian,
			R.drawable.bao_xingli, R.drawable.bao_shouti,
			R.drawable.bao_liantiao, R.drawable.bao_da, R.drawable.bao_xiekua,
			R.drawable.bao_hanban, R.drawable.bao_shouna,
			R.drawable.bao_shuangjian, R.drawable.bao_beike,
			R.drawable.bao_liusu, R.drawable.bao_shuitong,
			R.drawable.bao_youchai, R.drawable.bao_fanbu };
	public static String[] peishi = new String[] { "摆件", "棒球帽", "贝雷帽", "耳钉",
			"耳环", "发饰", "戒指", "毛线帽", "墨镜", "手机壳", "手链", "袜子", "围巾", "项链", "腰带",
			"渔夫帽", "雨伞" };
	public static int[] peishi_img = new int[] { R.drawable.peishi_baijian,
			R.drawable.peishi_bangqiu, R.drawable.peishi_beilei,
			R.drawable.peishi_eding, R.drawable.peishi_ehuan,
			R.drawable.peishi_fashi, R.drawable.peishi_jiezhi,
			R.drawable.peishi_maoxian, R.drawable.peishi_mojin,
			R.drawable.peishi_shoujike, R.drawable.peishi_shoulian,
			R.drawable.peishi_wazi, R.drawable.peishi_weijin,
			R.drawable.peishi_xianglian, R.drawable.peishi_yaodai,
			R.drawable.peishi_yufu, R.drawable.peishi_yusan };
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
