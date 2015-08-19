package com.purchase.entity;

public class SearchAutoData {
	private String id;
	private String content;

	public SearchAutoData() {
		super();
	}

	public SearchAutoData(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
