package com.ihsan.xd.newapp.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.ihsan.xd.newapp.model.Articles;

public class NewsResponse{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("articles")
	private List<Articles> articles;

	@SerializedName("status")
	private String status;

	public int getTotalResults(){
		return totalResults;
	}

	public List<Articles> getArticles(){
		return articles;
	}

	public String getStatus(){
		return status;
	}
}