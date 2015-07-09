package com.purchase.entity;

public class House {

	private String imageUrl;// 图片地址
	private String imagePath;// 图片点击路径(跳转到淘宝)
	private String imageTitle;
	private String price_discount;
	private String price;

	public House() {
		super();
	}

	public House(String imageUrl, String imagePath, String imageTitle,
			String price_discount, String price) {
		super();
		this.imageUrl = imageUrl;
		this.imagePath = imagePath;
		this.imageTitle = imageTitle;
		this.price_discount = price_discount;
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getPrice_discount() {
		return price_discount;
	}

	public void setPrice_discount(String price_discount) {
		this.price_discount = price_discount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "House [imageUrl=" + imageUrl + ", imagePath=" + imagePath
				+ ", imageTitle=" + imageTitle + ", price_discount="
				+ price_discount + ", price=" + price + "]";
	}

}
