package com.daynilgroup.DaynilGroupAssignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Entries")
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@JsonIgnoreProperties("id")
public class Entries 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@JsonProperty("API")
	private String title;

	//@JsonProperty("Description")
	private String description;
	
	//@JsonProperty("Auth")
	private String auth;
	
	//@JsonProperty("HTTPS")
	private boolean https;
	
	//@JsonProperty("Cors")
	private String cors;
	
	//@JsonProperty("Link")
	private String link;
	
//	@JsonProperty("Category")
	private String category;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public boolean getHttps() {
		return https;
	}

	public void setHttps(boolean https) {
		this.https = https;
	}

	public String getCors() {
		return cors;
	}

	public void setCors(String cors) {
		this.cors = cors;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
