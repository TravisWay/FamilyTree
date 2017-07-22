package com.skilldistillery.film.controllers;

import java.util.List;

import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.Film;
import com.skilldistillery.film.data.FilmDAO;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO dao;

	@RequestMapping(path = "home.do")
	public ModelAndView home() {
		return new ModelAndView("WEB-INF/views/home.jsp");
	}

	@RequestMapping(path = "getTitle.do")
	public ModelAndView getFilmTitleById(@RequestParam(name = "filmId") Integer filmId) {
		String viewName = "WEB-INF/views/searchresult.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		List<Film> films = dao.getFilmTitleById(filmId);
		mv.addObject("filmTitlekey", films);
		return mv;
	}

	@RequestMapping(path = "getKeyword.do")
	public ModelAndView getFilmTitleByKeyword(@RequestParam("filmkey") String filmkey) {
		String viewName = "WEB-INF/views/searchresult.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		List<Film> films = dao.getFilmTitleByKeyword(filmkey);
		mv.addObject("filmTitlekey", films);
		return mv;
	}

	@RequestMapping(path = "addFilm.do")
	public ModelAndView addnewFilm(@RequestParam("film title") String title, @RequestParam("film length") int length,
			@RequestParam("film rating") String rating, @RequestParam("film description") String description) {
		
		Film film = new Film(length, rating, title, description);
		String viewName = "WEB-INF/views/result.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("film", dao.addFilm(film));

		return mv;
	}
	@RequestMapping(path = "deletefilm.do")
	public ModelAndView deleteFilm(@RequestParam("id") int id) {
		String viewName = "WEB-INF/views/deleteresult.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		List<Film> films = dao.getFilmTitleById(id);
		mv.addObject("filmTitlekey", films);
		dao.deleteFilm(id);
		return mv;
	}
	@RequestMapping(path = "updatefilm.do")
	public ModelAndView updateFilm(@RequestParam("film title") String title, @RequestParam("film length") int length,
			@RequestParam("film rating") String rating, @RequestParam("film description") String description, @RequestParam("film id") int id) {
		Film film = new Film(length, rating, title, description);
		film.setId(id);
		dao.updateFilm(film);
		String viewName = "WEB-INF/views/updateresult.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		List<Film> films = dao.getFilmTitleById(film.getId());
		mv.addObject("filmTitlekey", films);
		return mv;
	}
	
}
