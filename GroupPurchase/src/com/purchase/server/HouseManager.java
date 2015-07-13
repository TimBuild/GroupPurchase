package com.purchase.server;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.purchase.entity.Dress;
import com.purchase.entity.House;

public class HouseManager {
	public static String house_url_refresh = "http://www.jiukuaiyou.com/jiu/jujia/whole/1";
	public static String house_url_load = "http://www.jiukuaiyou.com/jiu/jujia/whole/2";
	
	
	
	public static List<House> getHouseByUrl(String url){
		List<House> houses = new ArrayList<House>();
		House house;
		
		String parseHtml = ParseHtml.getHtmlContent(url, "utf-8");
		Document doc = Jsoup.parse(parseHtml);
		Elements contents = doc.select("div.main>ul.goods-list>li");
		
		for(Element content:contents){
			house = new House();
			Elements images = content.getElementsByClass("pic-img");
			//http://www.jiukuaiyou.com/deal/79301624
//			String imagePath = images.select("a").first().attr("href");
			//http://s1.juancdn.com/bao/150701/5/2/5593576b3f97f_400x400.jpg_285x285.jpg
			String imageUrl = images.select("img").first().attr("src");
			//http://s.juancdn.com/jiukuaiyou/images/blank.png?ts=20150702171700
			if(imageUrl.contains("images/blank.png")){
				imageUrl = images.select("img").first().attr("data-original");
			}
			
			
			Elements titles = content.getElementsByClass("good-title");
			//男士宽松直筒五分裤
			String imageTitle = titles.select("a").first().text();
			
			Elements prices = content.getElementsByClass("good-price");
			//￥9.9
			String price_discount = prices.select("span").first().text();
			String price_before = prices.select("span").get(1).text();
			//￥68 (1.5折)
			String price = price_before.replace("拍下改价 ", "");
			
			String imagePath = prices.select("div>a").first().attr("href");
			imagePath = imagePath+"&person=new";
			
			house.setImagePath(imagePath);
			house.setImageUrl(imageUrl);
			house.setImageTitle(imageTitle);
			house.setPrice(price);
			house.setPrice_discount(price_discount);
			
			houses.add(house);
			
		}
		return houses;
	}
}
