package com.purchase.entity;

public enum Clothing {
	Dress("fushi", "服装"), 
	House("jujia", "居家"), 
	Infant("muying", "母婴"), 
	Food("meishi", "美食"),
	Shoes("xiebaopeishi","鞋包配饰"),
	Cosmetic("meizhuang","美妆"),
	Appliance("shuma","数码电器"),
	Wenti("wenti","文体");

	private String name;
	private String index;

	private Clothing(String name, String index) {
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

	public static String getClothingName(String index) {
		for (Clothing clothing : Clothing.values()) {
			if (clothing.getIndex().equals(index)) {
				return clothing.getName();
			}
		}
		return index;
	}

}
