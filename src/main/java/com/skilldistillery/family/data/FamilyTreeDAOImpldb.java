package com.skilldistillery.family.data;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//@Component
public class FamilyTreeDAOImpldb implements FamilyTreeDAOdb {
	private List<People> familymembers = new ArrayList<>();
	private static String url = "jdbc:mysql://localhost:3306/familytree";
	private String user = "ftuser";
	private String pass = "ftuser";

	public FamilyTreeDAOImpldb() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public List<People> getPeopleByName(String name) {
		List<People> searchresults = new ArrayList<>();
		String fname = null;
		String lname = null;
		int id, age;

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT fname, lname, age FROM people WHERE fname LIKE ? OR Lname Like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%".concat(name).concat("%"));
			stmt.setString(2, "%".concat(name).concat("%"));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fname = rs.getString(1);
				lname = rs.getString(2);
				age = rs.getInt(3);
				People person = new People(age, fname, lname);
				searchresults.add(person);
			}
			if (searchresults.size() == 0) {
				searchresults = null;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchresults;
	}

	@Override
	public People addPeople(People people) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO people (fname, lname, age,mother_id,father_id,sibling_id) VALUES(?,?,?,?,?,?)";
			System.out.println(people);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, people.getFname());
			stmt.setString(2, people.getLname());
			stmt.setInt(3, people.getAge());
			stmt.setInt(4, people.getMother_id());
			stmt.setInt(5, people.getFather_id());
			stmt.setInt(6, people.getSibling_id());
			System.out.println(people);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newPeopleID = keys.getInt(1);
					people.setId(newPeopleID);
				}
			} else {
				people = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting people " + people);
		}
		return people;
	}
	// Searches the list for the member with the matching data and deletes that
	// member from the list and rewrites the file.

	@Override
	public boolean DeleteAll() {
		Connection conn = null;
		boolean bol = false;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM people";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				bol=true;
				ResultSet keys = stmt.getGeneratedKeys();
				
				}
	
			
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error deleting ");
		}
		return bol;

	}

	// Returns the updated familymembers list
	@Override
	public List<People> CurrentTree() {
		List<People> familymembers = new ArrayList<>();
		Connection conn = null;
		try {
			int id, age;
			String fname, lname;
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "Select id, fname, lname, age from people";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.executeQuery();
			conn.commit(); // COMMIT TRANSACTION
			while (rs.next()) {
				id = rs.getInt(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				age = rs.getInt(4);
				People people = new People(id, age, fname, lname);

				familymembers.add(people);

			}
			if (familymembers.size() == 0) {
				familymembers = null;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return familymembers;
	}

	@Override
	public Map<String, ArrayList<People>> Relatives() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean killPeople(String fname, String lname) {
		Connection conn = null;
		boolean bol = false;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM PEOPLE WHERE fname = ? AND lname = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				bol = true;
				ResultSet keys = stmt.getGeneratedKeys();

			}

			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error deleting " + fname);
		}
		return bol;
	}
	//
	// // Returns the Search Results list
	// public List<People> getSearchresults() {
	// return searchresults;
	// }
	//
	// Returns the updated familymembers list
	// @Override
	// public List<People> CurrentTree() {
	// System.out.println(familymembers);
	// readFile();
	// System.out.println(familymembers);
	// readFile();
	// return familymembers;
	// }
	//
	// // Reads the file and adds those members to the familymembers list.
	// public void readFile() {
	// familymembers.clear();
	// System.out.println("attempting to read file");
	//
	// try {
	//
	// BufferedReader buf = new BufferedReader(new
	// FileReader(wac.getServletContext().getRealPath(FILE_NAME)));
	// String line = "";
	// while ((line = buf.readLine()) != null) {
	// System.out.println("made it");
	// String[] tokens = line.split(",");
	// String relation = tokens[0];
	// String fname = tokens[1];
	// String lname = tokens[2];
	// int age = Integer.parseInt(tokens[3]);
	// familymembers.add(new People(age, relation, fname, lname));
	// System.out.println(familymembers);
	// }
	// buf.close();
	// } catch (Exception e) {
	// System.err.println(e);
	// }
	// }
	//
	// // Deletes all members.
	// @Override
	// public boolean DeleteAll() {
	// boolean delete = true;
	// if (!familymembers.isEmpty()) {
	// familymembers.clear();
	//
	// }
	// try {
	// FileWriter fw = new
	// FileWriter((((wac.getServletContext()).getRealPath(FILE_NAME))));
	// BufferedWriter bw = new BufferedWriter(fw);
	// for (People people2 : familymembers) {
	// bw.write(people2.toString());
	// System.out.println("Done");
	// }
	// bw.flush();
	// bw.close();
	// fw.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return delete;
	// }
	//
	// @Override
	// public Map<String, ArrayList<People>> Relatives() {
	// CurrentTree();
	// List<People> siblings = new ArrayList<>();
	// List<People> parents = new ArrayList<>();
	// List<People> auntsUncles = new ArrayList<>();
	// List<People> grandparents = new ArrayList<>();
	// List<People> greatgrandparents = new ArrayList<>();
	// List<People> children = new ArrayList<>();
	// List<People> you = new ArrayList<>();
	// List<People> spouse = new ArrayList<>();
	//
	// Map<String, ArrayList<People>> All = new HashMap<>();
	//
	// Iterator<People> iter = familymembers.iterator();
	// while (iter.hasNext()) {
	// People people = iter.next();
	// switch (people.getRelation()) {
	// case "You":
	// you.add(people);
	// break;
	// case "Spouse":
	// spouse.add(people);
	// break;
	// case "Sister":
	// siblings.add(people);
	// break;
	// case "Brother":
	// siblings.add(people);
	// break;
	// case "Daughter":
	// children.add(people);
	// break;
	// case "Son":
	// children.add(people);
	// break;
	// case "Mother":
	// parents.add(people);
	// break;
	// case "Father":
	// parents.add(people);
	// break;
	// case "Aunt":
	// auntsUncles.add(people);
	// break;
	// case "Uncle":
	// auntsUncles.add(people);
	// break;
	// case "Grandmother M":
	// grandparents.add(people);
	// break;
	//
	// case "Grandmother F":
	// grandparents.add(people);
	// break;
	// case "Grandfather M":
	// grandparents.add(people);
	// break;
	// case "Grandfather F":
	// grandparents.add(people);
	// break;
	// case "GreatGrandmother M":
	// greatgrandparents.add(people);
	// break;
	// case "GreatGrandmother F":
	// greatgrandparents.add(people);
	// break;
	// case "GreatGrandfather M":
	// greatgrandparents.add(people);
	// break;
	// case "GreatGrandfather F":
	// greatgrandparents.add(people);
	// break;
	//
	// }
	// }
	//
	// All.put("GreatGrandParents", (ArrayList<People>) greatgrandparents);
	// All.put("GrandParents", (ArrayList<People>) grandparents);
	// All.put("AuntsUncles", (ArrayList<People>) auntsUncles);
	// All.put("Parents", (ArrayList<People>) parents);
	// All.put("You", (ArrayList<People>) you);
	// All.put("Spouse", (ArrayList<People>) spouse);
	// All.put("Children", (ArrayList<People>) children);
	// All.put("Siblings", (ArrayList<People>) siblings);
	//
	// return All;
	// }

}