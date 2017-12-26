package com.java.Mapping;

import java.util.List;

public class ManyToOneMapping {

	public static void main(String[] args) {

		

	}

}



class College{
	
	int collegeId;
	String collegeName;
	
	
	
}

class University{
	
	int univId;
	String univName;
	
	List<College> listOfColleges;
	
	
	
}