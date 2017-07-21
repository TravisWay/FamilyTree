package com.skilldistillery.film.data;

import java.util.List;


public interface FilmDAO {
	String getFilmTitleById(int id);
	List<Film> getFilmTitleByKeyword(String key);
	Film addFilm(Film film);
	Film deleteFilm(Film film);
	Film updateFilm(Film film);
}
