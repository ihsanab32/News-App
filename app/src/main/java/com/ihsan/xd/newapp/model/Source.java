package com.ihsan.xd.newapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Source{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}
}