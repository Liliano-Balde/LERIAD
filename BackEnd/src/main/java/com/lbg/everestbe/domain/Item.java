package com.lbg.everestbe.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {

//	Primary key for the Item object
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Double price;
	@Column(nullable = false)
	private Long quantity;
	@Column(nullable = false)
	private String description;

//	Many-to-one relationship between Item and Customer objects	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Item() {
	}

//	Constructor with parameters for starting the Item object
	public Item(Integer id, String name, Double price, Long quantity, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}

	public Item(Integer id, String name, Double price, Long quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Item(String name, Double price, Long quantity) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Item(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Item(Integer id) {
		super();
		this.id = id;
	}

//	Getters and setters for the fields of the Item object
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item: id = " + id + ", name = " + name + ", price = " + price + ", quantity = " + quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
