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
	  String viewName = "WEB-INF/views/home.jsp";
	  ModelAndView mv = new ModelAndView(viewName);
	  String title = dao.getFilmTitleById(filmId);
	  mv.addObject("filmTitle", title);
	  return mv;
	}	
	@RequestMapping(path = "getKeyword.do")
	public ModelAndView getFilmTitleByKeyword(@RequestParam("filmkey") String filmkey) {
		String viewName = "WEB-INF/views/home.jsp";
	  ModelAndView mv = new ModelAndView(viewName);
	  List<Film> title = dao.getFilmTitleByKeyword(filmkey);
	  mv.addObject("filmTitlekey", title);
	  return mv;
	}	
}
