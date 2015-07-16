package com.purchase.entity;

public class GoodsItem {
	private String discount_price;
	private String open_iid;
	private String pic_url;
	private String pics;
	private String price;
	private String properties_and_values;
	private String shop_type;
	private String tag_id;
	private String title;

	public GoodsItem() {
		super();
	}

	public GoodsItem(String discount_price, String open_iid, String pic_url,
			String pics, String price, String properties_and_values,
			String shop_type, String tag_id, String title) {
		super();
		this.discount_price = discount_price;
		this.open_iid = open_iid;
		this.pic_url = pic_url;
		this.pics = pics;
		this.price = price;
		this.properties_and_values = properties_and_values;
		this.shop_type = shop_type;
		this.tag_id = tag_id;
		this.title = title;
	}

	public String getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(String discount_price) {
		this.discount_price = discount_price;
	}

	public String getOpen_iid() {
		return open_iid;
	}

	public void setOpen_iid(String open_iid) {
		this.open_iid = open_iid;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProperties_and_values() {
		return properties_and_values;
	}

	public void setProperties_and_values(String properties_and_values) {
		this.properties_and_values = properties_and_values;
	}

	public String getShop_type() {
		return shop_type;
	}

	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
