package com.skilldistillery.family.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.family.data.FamilyTreeDAOdb;
import com.skilldistillery.family.data.People;

@EnableWebMvc
@Controller
public class crudController {
	@Autowired
	private FamilyTreeDAOdb familyTreeDAO;

	@RequestMapping(path = "home.do")
	public ModelAndView home() {
		return new ModelAndView("index.html");
	}

	@RequestMapping(path = "addpeople.do")
	public ModelAndView addPeople() {
		return new ModelAndView("newPeople.html");
	}

	@RequestMapping(path = "killpeople.do")
	public ModelAndView deletePeople() {
		return new ModelAndView("killPeople.html");
	}

	@RequestMapping(path = "searchname.do", method = RequestMethod.GET)
	public ModelAndView getPeopleByName(@RequestParam("data") String s, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/resultsearch.jsp");
		// session.setAttribute("searchresults", familyTreeDAO.getPeopleByName(s));
		mv.addObject("result", familyTreeDAO.getPeopleByName(s));
		return mv;
	}

	@RequestMapping(path = "viewtree.do", method = RequestMethod.GET)
	public ModelAndView getlist(HttpSession session, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/result.jsp");
		mv.addObject("tree", familyTreeDAO.CurrentTree());

		return mv;
	}

	@RequestMapping(path = "addpeople1.do", method = RequestMethod.POST)
	public ModelAndView addMember(@RequestParam("fname") String fname, @RequestParam("lname") String lname,@RequestParam("age") int age, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:addpeople2.do");
		People person = new People(age, fname, lname);
		familyTreeDAO.addPeople(person);
		return mv;
	}

	@RequestMapping(path = "addpeople2.do", method = RequestMethod.GET)
	public ModelAndView addMember2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/resultaddPeople.jsp");
		return mv;
	}

	@RequestMapping(path = "killpeople.do", method = RequestMethod.POST)
	public ModelAndView killMember(@RequestParam("fname") String fname, @RequestParam("lname") String lname, HttpSession session, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:killpeople2.do");
		redir.addFlashAttribute("result", familyTreeDAO.killPeople(fname, lname));
		return mv;
	}

	@RequestMapping(path = "killpeople2.do", method = RequestMethod.GET)
	public ModelAndView killMember2(People people, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/resultkillPeople.jsp");
		return mv;
	}

	@RequestMapping(path = "killallpeople.do", method = RequestMethod.POST)
	public ModelAndView DeleteAll(HttpSession session, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:killallpeople2.do");
		redir.addFlashAttribute("result", familyTreeDAO.DeleteAll());
		return mv;
	}

	@RequestMapping(path = "killallpeople2.do", method = RequestMethod.GET)
	public ModelAndView DeleteAll(People people, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/resultallPeople.jsp");
		return mv;
	}

}
