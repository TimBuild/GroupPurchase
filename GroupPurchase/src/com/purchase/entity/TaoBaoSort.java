package com.purchase.entity;

public enum TaoBaoSort {
	
	Man1("背心/马甲", "3745"), Man2("衬衫", "3213"), Man3("风衣", "3782"), 
	Man4("夹克","3780"), Man5("毛呢大衣", "3784"), Man6("棉裤", "3777"), 
	Man7("棉衣","3785"), Man8("牛仔裤", "3775"), Man9("皮裤", "3779"), 
	Man10("皮衣",	"3786"), Man11("Polo衫", "3773"), Man12("T恤", "3212"), 
	Man13("卫衣",	"3781"), Man14("西服", "3788"), Man15("休闲裤", "3776"), 
	Man16("羽绒服","3787"), Man17("羽绒裤", "3778"), Man18("针织衫", "3774"), 
	Man19("男靴",	"3950"), Man20("男鞋", "3952"), Man21("男包", "3953"), 
	Man22("打火机","3954"), Man23("剃须刀", "3955"), Man24("手表", "3956"), 
	Man25("皮带",	"3957"), Man26("男士护理", "3959"), Man27("男士香水", "3960"),
	Woman1("连衣裙","3972"),Woman2("女士T恤","3974"),Woman3("女士衬衫","3975"),
	Woman4("女士休闲裤","3976"),Woman5("女士西裤","3977"),Woman6("打底裤","3978"),
	Woman7("女士牛仔裤","3987"),Woman8("半身裙","3988"),Woman9("女士马甲","3989"),
	Woman10("雪纺衫/蕾丝衫","3992"),Woman11("毛针织衫","3993"),Woman12("短外套","3994"),
	Woman13("女士毛衣","4002"),Woman14("女士风衣","4031"),Woman15("女士羽绒服","4032"),
	Woman16("女士棉服","4033"),Woman17("女士卫衣","4034"),Woman18("女士西装","4035"),
	Woman19("毛呢外套","4036"),
	Shoe1("低帮鞋","4052"),Shoe2("高帮鞋","4053"),Shoe3("靴子","4054"),
	Shoe4("凉鞋","4055"),Shoe5("拖鞋","4056"),Shoe6("帆布鞋","4057"),
	Shoe7("雨鞋","4058"),Shoe8("篮球鞋","4059"),Shoe9("足球鞋","4060"),
	Shoe10("网球鞋","4061"),Shoe11("休闲鞋/板鞋","4062"),Shoe12("运动鞋","4063"),
	Shoe13("跑步鞋","4064"),Shoe14("童鞋/青少年鞋","4065"),Shoe15("羽毛球鞋","4069"),
	Shoe16("其他运动鞋","4070"),
	Bao1("化妆包","4076"),Bao2("钱包","4077"),Bao3("卡包","4078"),
	Bao4("单肩包","4079"),Bao5("行李箱","4080"),Bao6("手提包","4081"),
	Bao7("链条包","4082"),Bao8("大包","4083"),Bao9("斜挎包","4084"),
	Bao10("韩版包","4085"),Bao11("手拿包","4086"),Bao12("双肩包","4087"),
	Bao13("贝壳包","4088"),Bao14("流苏包","4089"),Bao15("水桶包","4090"),
	Bao16("邮差包","4091"),Bao17("帆布包","4092"),
	Peishi1("摆件","4141"),Peishi2("棒球帽","4143"),Peishi3("贝雷帽","4133"),
	Peishi4("耳钉","4145"),Peishi5("耳环","4139"),Peishi6("发饰","4136"),
	Peishi7("戒指","4134"),Peishi8("毛线帽","4140"),Peishi9("墨镜","4146"),
	Peishi10("手机壳","4130"),Peishi11("手链","4131"),Peishi12("袜子","4132"),
	Peishi13("围巾","4147"),Peishi14("项链","4135"),Peishi15("腰带","4148"),
	Peishi16("渔夫帽","4137"),Peishi17("雨伞","4144")
	;
	

	private String name;
	private String index;

	private TaoBaoSort(String name, String index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * 通过title的名字找到所对应的tagID
	 * @param name
	 * @return
	 */
	public static String getTagsId(String name) {
		for (TaoBaoSort tao : TaoBaoSort.values()) {
			if (tao.getName().equals(name)) {
				return tao.getIndex();
			}
		}
		return name;
	}

}
