package com.skilldistillery.film.data;

import java.util.List;

public interface FilmDAO {
	String getFilmTitleById(int id);
	List<Film> getFilmTitleByKeyword(String key);
	
}
