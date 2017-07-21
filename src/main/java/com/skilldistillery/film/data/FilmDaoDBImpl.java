package com.skilldistillery.film.data;

import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDaoDBImpl implements FilmDAO {
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
		String title = null;
		String rating = null;
		String description = null;
		int length;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT title, rating, description,length, id  FROM film WHERE title LIKE ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + key + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				title = rs.getString(1);
				rating = rs.getString(2);
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
		String firstname = null;
		String lastname = null;
		int id;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql2 = "SELECT first_name, last_name,id FROM actor WHERE id IN ( SELECT actor_id FROM film_actor WHERE film_id = ?)";
			PreparedStatement stmt = conn.prepareStatement(sql2);
			stmt.setInt(1, filmid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				firstname = rs.getString(1);
				lastname = rs.getString(2);
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

	@Override
	public Film addFilm(Film film) {
		Connection conn =null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, length, rating, description, language_id) VALUES(?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setInt(2, film.getLength());
			stmt.setString(3, film.getRating());
			stmt.setString(4, film.getDescription());
			stmt.setInt(5, 3);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
				}
			} else {
				film = null;
			}
			 conn.commit(); // COMMIT TRANSACTION
		  } catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); }
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		    throw new RuntimeException("Error inserting actor " + film);
		  }
		  return film;
		}

	@Override
	public Film deleteFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

}
