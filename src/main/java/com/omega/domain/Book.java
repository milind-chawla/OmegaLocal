package com.omega.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BOOK")
public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
    @Size(min=3, max=20)
	private String name;
	
	@Column
	private String image;
	
	public Book() {
		this.id = -1L;
		this.name = null;
		this.image = "xyz";
	}

	public Book(String name, String image) {
		this.id = -1L;
		this.name = name;
		this.image = image;
	}
	
	public Book(long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book (id=" + id + ", name=" + name + ", image=" + image + ")";
	}
}
