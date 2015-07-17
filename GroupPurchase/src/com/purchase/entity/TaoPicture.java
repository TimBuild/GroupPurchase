package com.purchase.entity;

public class TaoPicture {
	private String title;
	private int imageId;

	public TaoPicture(String title, int imageId) {
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public TaoPicture() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "TaoPicture [title=" + title + ", imageId=" + imageId + "]";
	}

}
