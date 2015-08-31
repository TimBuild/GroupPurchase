package com.purchase.entity;

public class DiscountBao {
	private String open_iid;// 开放商品id
	private String pic_url;// 图片url
	private String title;// 商品名称
	private String price;// 商品价格
	private String coupon_price;// 商品折扣价格
	private String shop_type;// 店铺类型B为天猫，C为集市
	private String nick;// 卖家名称

	public DiscountBao() {
		super();
	}

	public DiscountBao(String open_iid, String pic_url, String title,
			String price, String coupon_price, String shop_type, String nick) {
		super();
		this.open_iid = open_iid;
		this.pic_url = pic_url;
		this.title = title;
		this.price = price;
		this.coupon_price = coupon_price;
		this.shop_type = shop_type;
		this.nick = nick;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCoupon_price() {
		return coupon_price;
	}

	public void setCoupon_price(String coupon_price) {
		this.coupon_price = coupon_price;
	}

	public String getShop_type() {
		return shop_type;
	}

	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "Discount [open_iid=" + open_iid + ", pic_url=" + pic_url
				+ ", title=" + title + ", price=" + price + ", coupon_price="
				+ coupon_price + ", shop_type=" + shop_type + ", nick=" + nick
				+ "]";
	}

}
