package com.java.Mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManyToManyMapping {

	public static void main(String[] args) {
		
		Subject s1 = new Subject(1001, "Maths",null);
		Subject s2 = new Subject(1002, "Physics",null);
		Subject s3 = new Subject(1003, "Chemistry",null);
		Subject s4 = new Subject(1004, "Biology",null);
		
		List<Subject> listOfPCM = new ArrayList<Subject>();
		listOfPCM.add(s2);
		listOfPCM.add(s3);
		listOfPCM.add(s1);
		
		List<Subject> listOfPCB = new ArrayList<Subject>();
		listOfPCB.add(s2);
		listOfPCB.add(s3);
		listOfPCB.add(s4);
		
		Student stud1 = new Student(11, "Horse", listOfPCB, 70);
		Student stud2 = new Student(12, "Dog", listOfPCM, 82);
		Student stud3 = new Student(13, "Cat", listOfPCB, 73);
		Student stud4 = new Student(14, "Monkey", listOfPCB, 91);
		Student stud5 = new Student(15, "Tiger", listOfPCM, 89);
		
		List<Student> listOfPCMStud = new ArrayList<Student>();
		listOfPCMStud.add(stud2);
		listOfPCMStud.add(stud5);
		
		List<Student> listOfPCBStud = new ArrayList<Student>();
		listOfPCBStud.add(stud1);	
		listOfPCBStud.add(stud3);
		listOfPCBStud.add(stud4);
		
		s1.setListOfStudents(listOfPCMStud);
		s2.setListOfStudents(listOfPCMStud);
		s3.setListOfStudents(listOfPCMStud);
		s4.setListOfStudents(listOfPCBStud);
		
		
		
		Configuration c = new Configuration();
		SessionFactory sf = c.configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.save(stud1);
		s.save(stud2);
		s.save(stud3);
		s.save(stud4);
		s.save(stud5);
		
		t.commit();
		s.close();
		
	}

}
@Entity
class Student{
	
	@Id
	//@GeneratedValue
	int stuId;
	
	String stuName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn
	List<Subject> listOfSubjects;
	
	int stuMarks;
	public Student(int stuId, String stuName, List<Subject> listOfSubjects, int stuMarks) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.listOfSubjects = listOfSubjects;
		this.stuMarks = stuMarks;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public List<Subject> getListOfSubjects() {
		return listOfSubjects;
	}
	public void setListOfSubjects(List<Subject> listOfSubjects) {
		this.listOfSubjects = listOfSubjects;
	}
	public int getStuMarks() {
		return stuMarks;
	}
	public void setStuMarks(int stuMarks) {
		this.stuMarks = stuMarks;
	}
	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", listOfSubjects=" + listOfSubjects + ", stuMarks="
				+ stuMarks + "]";
	}
	
	
	
}

@Entity
class Subject{
	
	@Id
	int subId;
	String subName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	List<Student> listOfStudents;
	
	public Subject(int subId, String subName, List<Student> listOfStudents) {
		super();
		this.subId = subId;
		this.subName = subName;
		this.listOfStudents = listOfStudents;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public List<Student> getListOfStudents() {
		return listOfStudents;
	}
	public void setListOfStudents(List<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}
	@Override
	public String toString() {
		return "Subject [subId=" + subId + ", subName=" + subName + ", listOfStudents="+ listOfStudents + "]";
	}
	
	
	
	
	
}

