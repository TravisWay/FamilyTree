package com.skilldistillery.family.data;

public class People {

	private int id, age,mother_id,father_id,sibling_id;
	private String fname, lname;

	public People(){ 
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMother_id() {
		return mother_id;
	}

	public void setMother_id(int mother_id) {
		this.mother_id = mother_id;
	}

	public int getFather_id() {
		return father_id;
	}

	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}

	public int getSibling_id() {
		return sibling_id;
	}

	public void setSibling_id(int sibling_id) {
		this.sibling_id = sibling_id;
	}

	public People(int id,int age, String fname, String lname) {
		this.id = id;
		this.age = age;
		this.fname = fname;
		this.lname = lname;
	}
	public People(int age, String fname, String lname) {
		this.age = age;
		this.fname = fname;
		this.lname = lname;
		mother_id=0;
		father_id=0;
		sibling_id=0;
	}
	
	
	public People(int id, int age, String fname, String lname, int mother_id, int father_id,int sibling_id) {
		this.id = id;
		this.age = age;
		this.fname = fname;
		this.lname = lname;
		this.mother_id=mother_id;
		this.father_id=father_id;
		this.sibling_id=sibling_id;
		
	}
	public People(int id, int age, String fname, String lname, int mother_id, int father_id) {
		this.id = id;
		this.age = age;
		this.fname = fname;
		this.lname = lname;
		this.mother_id=mother_id;
		this.father_id=father_id;
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(fname+",");
		builder.append(lname);
		builder.append(",");
		builder.append(age);
		builder.append(System.getProperty("line.separator"));
		return (builder.toString());
	}

}
