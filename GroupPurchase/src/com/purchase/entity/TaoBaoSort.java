package com.purchase.entity;

public enum TaoBaoSort {
	
	Man1("背心/马甲", "3745"), 
	Man2("衬衫", "3213"), 
	Man3("风衣", "3782"), 
	Man4("夹克", "3780"), 
	Man5("毛呢大衣", "3784"), 
	Man6("棉裤", "3777"), 
	Man7("棉衣", "3785"), 
	Man8("牛仔裤", "3775"), 
	Man9("皮裤", "3779"), 
	Man10("皮衣", "3786"), 
	Man11("Polo衫", "3773"), 
	Man12("T恤", "3212"), 
	Man13("卫衣", "3781"), 
	Man14("西服", "3788"), 
	Man15("休闲裤", "3776"), 
	Man16("羽绒服", "3787"), 
	Man17("羽绒裤", "3778"), 
	Man18("针织衫","3774");

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
