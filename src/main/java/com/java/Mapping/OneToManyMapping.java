package com.java.Mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToManyMapping {

	public static void main(String[] args) {
		
		Book b1 = new Book(10, "Book1");
		Book b2 = new Book(11, "Book2");
		Book b3 = new Book(12, "Book3");
		
		List<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(b1);
		listOfBooks.add(b2);
		listOfBooks.add(b3);
		
		Author a1 = new Author(1, "Monkey", listOfBooks);
		Author a2 = new Author(2, "Cat", listOfBooks);

		
		Configuration c = new Configuration();
		SessionFactory sf = c.configure().buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.save(a1);
		
		
		t.commit();
		s.close();
	
	}
	
}

@Entity
class Author{
	@Id
	int authorId;
	String authorName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	List<Book> listOfBooks;
	
	public Author(int authorId, String authorName, List<Book> listOfBooks) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.listOfBooks = listOfBooks;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<Book> getListOfBooks() {
		return listOfBooks;
	}
	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", listOfBooks=" + listOfBooks + "]";
	}
	
	
}

@Entity
class Book{
	@Id
	//@GeneratedValue
	int bookId;
	String bookName;
	public Book(int bookId, String bookName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + "]";
	}
	
	
}
	
