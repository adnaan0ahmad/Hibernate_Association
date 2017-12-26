package com.java.Inheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Parent {
	@Id
	private int id;
	@Column
	private int p_var1;
	@Column
	private int p_var2;

	public Parent(int id, int p_var1, int p_var2) {
		super();
		this.id = id;
		this.p_var1 = p_var1;
		this.p_var2 = p_var2;
	}

	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getP_var1() {
		return p_var1;
	}

	public void setP_var1(int p_var1) {
		this.p_var1 = p_var1;
	}

	public int getP_var2() {
		return p_var2;
	}

	public void setP_var2(int p_var2) {
		this.p_var2 = p_var2;
	}

	@Override
	public String toString() {
		return "Parent [id=" + id + ", p_var1=" + p_var1 + ", p_var2=" + p_var2 + "]";
	}

}


@Entity
class Child1 extends Parent {
	@Column
	private int c1_var1;
	@Column
	private int c1_var2;
	@Column
	private int c1_var3;

	

	public Child1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Child1(int id, int p_var1, int p_var2) {
		super(id, p_var1, p_var2);
		// TODO Auto-generated constructor stub
	}

	public Child1(int id, int p_var1, int p_var2, int c1_var1, int c1_var2, int c1_var3) {
		super(id, p_var1, p_var2);
		this.c1_var1 = c1_var1;
		this.c1_var2 = c1_var2;
		this.c1_var3 = c1_var3;
	}

	public int getC1_var1() {
		return c1_var1;
	}

	public void setC1_var1(int c1_var1) {
		this.c1_var1 = c1_var1;
	}

	public int getC1_var2() {
		return c1_var2;
	}

	public void setC1_var2(int c1_var2) {
		this.c1_var2 = c1_var2;
	}

	public int getC1_var3() {
		return c1_var3;
	}

	public void setC1_var3(int c1_var3) {
		this.c1_var3 = c1_var3;
	}

	@Override
	public String toString() {
		return "Child1 [c1_var1=" + c1_var1 + ", c1_var2=" + c1_var2 + ", c1_var3=" + c1_var3 + "]";
	}

}


@Entity
class Child2 extends Parent {
	@Column
	private int c2_var1;
	@Column
	private int c2_var2;
	@Column
	private int c2_var3;



	public Child2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Child2(int id, int p_var1, int p_var2) {
		super(id, p_var1, p_var2);
		// TODO Auto-generated constructor stub
	}

	public Child2(int id, int p_var1, int p_var2, int c2_var1, int c2_var2, int c2_var3) {
		super(id, p_var1, p_var2);
		this.c2_var1 = c2_var1;
		this.c2_var2 = c2_var2;
		this.c2_var3 = c2_var3;
	}

	public int getC2_var1() {
		return c2_var1;
	}

	public void setC2_var1(int c2_var1) {
		this.c2_var1 = c2_var1;
	}

	public int getC2_var2() {
		return c2_var2;
	}

	public void setC2_var2(int c2_var2) {
		this.c2_var2 = c2_var2;
	}

	public int getC2_var3() {
		return c2_var3;
	}

	public void setC2_var3(int c2_var3) {
		this.c2_var3 = c2_var3;
	}

	@Override
	public String toString() {
		return "Child2 [c2_var1=" + c2_var1 + ", c2_var2=" + c2_var2 + ", c2_var3=" + c2_var3 + "]";
	}

}