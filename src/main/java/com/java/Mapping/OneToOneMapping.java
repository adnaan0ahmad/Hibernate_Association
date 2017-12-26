package com.java.Mapping;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.cfg.Configuration;

public class OneToOneMapping {

	public static void main(String[] args) {

		Address ad1 = new Address(17, "Pune", 2087, null);
		Employee e1 = new Employee(167, "Dog", 45, 8000, ad1);
		ad1.setEmp(e1);

		Address ad2 = new Address(29, "Mumbai", 2033, null);
		Employee e2 = new Employee(278, "Cat", 25, 60000, ad2);
		ad2.setEmp(e2);

		Address ad3 = new Address(31, "Chennai", 4387, null);
		Employee e3 = new Employee(389, "Pig", 51, 80900, ad3);
		ad3.setEmp(e3);

		Address ad4 = new Address(47, "Hyderabad", 7692, null);
		Employee e4 = new Employee(454, "Puppy", 9, 6000, ad4);
		ad4.setEmp(e4);

		Configuration c = new Configuration();
		SessionFactory sf = c.configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.save(e1);
		s.save(e2);
		s.save(e3);
		s.save(e4);

		t.commit();
		s.close();
	}

}

@Entity
@Table(name = "Employee")
class Employee {

	@Id
	@GeneratedValue
	int empId;
	String empName;
	int empAge;
	int empSalary;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Address empAddress;

	public Employee(int empId, String empName, int empAge, int empSalary, Address empAddress) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empSalary = empSalary;
		this.empAddress = empAddress;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public Address getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(Address empAddress) {
		this.empAddress = empAddress;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empSalary=" + empSalary
				+ ", empAddress=" + empAddress + "]";
	}

}

@Entity
@Table(name = "Address")
class Address {
	@Id
	int AddId;
	String city;
	int pincode;
	
	@OneToOne(cascade = CascadeType.ALL)
	Employee emp;

	public Address(int addId, String city, int pincode, Employee emp) {
		super();
		AddId = addId;
		this.city = city;
		this.pincode = pincode;
		this.emp = emp;
	}

	public Address() {
		super();
	}

	@Override
	public String toString() {
		return "Address [AddId=" + AddId + ", city=" + city + ", pincode=" + pincode + ", emp=" + emp + "]";
	}

	public int getAddId() {
		return AddId;
	}

	public void setAddId(int addId) {
		AddId = addId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	

}
