package com.purchase.global;

import com.matrix.grouppurchase.R;

public class Constants {

	public final static String BASE_URL = "http://www.jiukuaiyou.com/jiu/";

	public final static String URL_REFRESH = "/whole/1";
	public final static String URL_LOAD = "/whole/2";

	// public final static String TAOBAO_URL =
	// "http://192.168.1.69:8081/PurchaseServer/rest/GoodsService/getGoods";
	public final static String TAOBAO_URL = "http://lavie-1.wx.jaeapp.com/rest/GoodsService/getGoods";

//	public final static String AiTAOBAO_URL = "http://192.168.1.69:8081/PurchaseServer/rest/GoodsService/searchGoods";
	public final static String AiTAOBAO_URL = "http://lavie-1.wx.jaeapp.com/rest/GoodsService/searchGoods";

	/**
	 * 每页最多显示多少条数据
	 */
	public final static int PAGE_SIZE = 20;

	/**
	 * 分类显示的标题
	 */
	public static String[] taobaoTitle = { "女装", "鞋子", "包包", "配饰", "美妆", "家居",
			"母婴", "男士","美食" };
	/**
	 * 分类显示爱淘宝的标题
	 */
	public static String[] aitaobaoTitle = { "女装", "鞋子", "包包", "配饰", "美妆", "家居",
		"母婴", "男士","美食" };
	/**
	 * 男装
	 */
	public static String[] man = new String[] { "背心/马甲", "男士衬衫", "男士风衣", "男士夹克",
			"男士毛呢大衣", "男士棉裤", "男士棉衣", "男士牛仔裤", "男士皮裤", "男士皮衣", "男士Polo衫", "男士T恤", "男士卫衣", "男士西服",
			"男士休闲裤", "男士羽绒服", "男士羽绒裤", "男士针织衫", "男靴", "男鞋", "男包", "打火机", "剃须刀", "男士手表",
			"男士皮带", "男士护理", "男士香水" };
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
	/**
	 * 女装
	 */
	public static String[] woman = new String[] { "连衣裙", "女士T恤", "女士衬衫",
			"女士休闲裤", "女士西裤", "打底裤", "女士牛仔裤", "半身裙", "女士马甲", "雪纺衫/蕾丝衫", "毛针织衫",
			"短外套", "女士毛衣", "女士风衣", "女士羽绒服", "女士棉服", "女士卫衣", "女士西装", "毛呢外套",
			"吊带背心", "长裙", "背带裤", "铅笔裤" };
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
	/**
	 * 鞋子
	 */
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
	/**
	 * 包包
	 */
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
	/**
	 * 配饰
	 */
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
	/**
	 * 美妆
	 */
	public static String[] meihzuang = new String[] { "面膜", "润唇膏", "指甲油", "香水",
			"洁面", "遮瑕", "BB霜", "粉底", "眉笔", "彩妆工具", "化妆水/爽肤水", "眼霜", "眼线笔" };
	public static int[] meihzuang_img = new int[] {
			R.drawable.meizhuang_mianmo, R.drawable.meizhuang_runchun,
			R.drawable.meizhuang_zhijia, R.drawable.meizhuang_xiangshui,
			R.drawable.meizhuang_jiemian, R.drawable.meizhuang_zhexia,
			R.drawable.meizhuang_bb, R.drawable.meizhuang_fendi,
			R.drawable.meizhuang_meibi, R.drawable.meizhuang_gongju,
			R.drawable.meizhuang_huazhuang, R.drawable.meizhuang_yanshuang,
			R.drawable.meizhuang_yanxia };
	/**
	 * 家居
	 */
	public static String[] jiaju = new String[] { "床上用品", "抱枕", "收纳整理", "洗护用品",
			"衣架", "毛巾", "碗筷", "杯子", "烹饪工具", "盆栽", "拖把", "钟表", "创意用品", "照片墙",
			"挂历" };
	public static int[] jiaju_img = new int[] { R.drawable.jiaju_chuang,
			R.drawable.jiaju_baozhen, R.drawable.jiaju_shouna,
			R.drawable.jiaju_xihu, R.drawable.jiaju_yijia,
			R.drawable.jiaju_maojin, R.drawable.jiaju_wankuai,
			R.drawable.jiaju_beizi, R.drawable.jiaju_pengren,
			R.drawable.jiaju_penzai, R.drawable.jiaju_tuoba,
			R.drawable.jiaju_zhongbiao, R.drawable.jiaju_chuangyi,
			R.drawable.jiaju_zhaopian, R.drawable.jiaju_guali };
	/**
	 * 母婴
	 */
	public static String[] muying = new String[] { "儿童外套", "儿童套装", "儿童针织衫",
			"儿童衬衫", "孕妇装", "婴幼装", "童鞋", "托腹裤", "童车", "早教", "书包", "纸尿裤", "奶瓶",
			"过家家", "儿童餐具", "益智玩具", "保温奶瓶", "待产包", "孕妇奶粉", "爬行垫" };
	public static int[] muying_img = new int[] { R.drawable.muying_waitao,
			R.drawable.muying_taozhuang, R.drawable.muying_zhenzhi,
			R.drawable.muying_chenshan, R.drawable.muying_yunfuzhuang,
			R.drawable.muying_yingyou, R.drawable.muying_tongxie,
			R.drawable.muying_tuofu, R.drawable.muying_tongche,
			R.drawable.muying_zaojiao, R.drawable.muying_shubao,
			R.drawable.muying_zhiniao, R.drawable.muying_naiping,
			R.drawable.muying_guojiajia, R.drawable.muying_canju,
			R.drawable.muying_yizhi, R.drawable.muying_naiping,
			R.drawable.muying_daichanbao, R.drawable.muying_yunfunaifen,
			R.drawable.muying_paxing };
	/**
	 * 美食
	 */
	public static String[] meishi = new String[] { "巧克力", "肉食", "咖啡", "饮料",
			"茶叶", "坚果", "果干", "水果", "糖果", "糕点", "海味即食", "饼干", "土特产" };
	public static int[] meishi_img = new int[] { R.drawable.meishi_qiaokeli,
			R.drawable.meishi_roushi, R.drawable.meishi_kafei,
			R.drawable.meishi_yinliao, R.drawable.meishi_chaye,
			R.drawable.meishi_jianguo, R.drawable.meishi_guogan,
			R.drawable.meishi_shuiguo, R.drawable.meishi_tangguo,
			R.drawable.meishi_gaodian, R.drawable.meishi_haiwei,
			R.drawable.meishi_binggan, R.drawable.meishi_tutechan };

}
