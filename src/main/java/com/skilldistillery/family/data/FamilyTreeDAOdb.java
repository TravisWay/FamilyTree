package com.skilldistillery.family.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FamilyTreeDAOdb {

	public List<People> getPeopleByName(String name);

	public People addPeople(People people);

	public boolean killPeople(People people);

	public boolean DeleteAll();

	public List<People> CurrentTree();
	
	public Map<String, ArrayList<People>> Relatives();

}
