package com.skilldistillery.film.data;

import java.util.List;

public class Film {
	private int length;
	private String rating;
	private String title;
	private String description;
	private List<Actor>cast;

	
	public List<Actor> getCast() {
		return cast;
	}


	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}
	
	public Film(int length, String rating, String title, String description, List<Actor> cast) {
		this.length = length;
		this.rating = rating;
		this.title = title;
		this.description = description;
		this.cast = cast;
		}
	
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
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
	@Override
	public String toString() {
		return "Film [length=" + length + ", rating=" + rating + ", title=" + title + ", description=" + description
				+ "]";
	}
	
}
