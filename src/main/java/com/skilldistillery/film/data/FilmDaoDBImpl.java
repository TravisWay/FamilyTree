package com.skilldistillery.film.data;

import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDaoDBImpl implements FilmDAO{
	private static String url = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";
	@Override
	public String getFilmTitleById(int id) {
	  String title = null;
	  try {
	    Connection conn = DriverManager.getConnection(url, user, pass);
	    String sql = "SELECT title FROM film WHERE id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	      title = rs.getString(1);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return title;
	}

	public FilmDaoDBImpl() {
		  try {
		    Class.forName("com.mysql.jdbc.Driver");
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    System.err.println("Error loading MySQL Driver!!!");
		  }
		}

	@Override
	public List<Film> getFilmTitleByKeyword(String key) {
		List<Film> films = new ArrayList<>();
		
		List<Actor> cast = new ArrayList<>();
		String title= null;
		String rating= null;
		String description= null;
		int length;
		try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String sql = "SELECT title, rating, description,length, id  FROM film WHERE title LIKE ?;";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, "%" + key +"%");
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      title = rs.getString(1);
		      rating	= rs.getString(2);
		      description = rs.getString(3);
		      length = rs.getInt(4);
		      int filmid = rs.getInt(5);
		      cast = getActorsByID(filmid);
		      Film film = new Film(length, rating, title, description, cast);
		      films.add(film);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
		}
	public List<Actor> getActorsByID(int filmid) {
		
		List<Actor> cast = new ArrayList<>();
		String firstname=null;
		String lastname= null;
		int id;
		try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String sql2 = "SELECT first_name, last_name,id FROM actor WHERE id IN ( SELECT actor_id FROM film_actor WHERE film_id = ?)";
		    PreparedStatement stmt = conn.prepareStatement(sql2);
		    stmt.setInt(1,filmid);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      firstname = rs.getString(1);
		      lastname	= rs.getString(2);
		      id = rs.getInt(3);
		      Actor actor = new Actor(id, firstname, lastname);
		      cast.add(actor);
		    }
		    
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		    
		    } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  
		  return cast;
		}
	
	
	}

