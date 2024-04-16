package com.lbg.everestbe.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.lbg.everestbe.domain.Customer;

public class CustomerConstructorTests {

	@Test
	public void testAllConstructors() {

		Integer id = 1;
		String name = "John Doe";
		String address = "Imaginary Street";
		String email = "JayDough@domain.com";
		String phone = "0123456789";
		String username = "JD@2024";
		String password = "j0nHdO£";

		Customer customer = new Customer(id, name, address, email, phone, username, password);

		assertEquals(id, customer.getId());
		assertEquals(name, customer.getName());
		assertEquals(address, customer.getAddress());
		assertEquals(email, customer.getEmail());
		assertEquals(phone, customer.getPhone());
		assertEquals(username, customer.getUsername());
		assertEquals(password, customer.getPassword());
	}

	@Test
	public void testIdConstructors() {

		Integer id = 1;

		Customer customer = new Customer(id);

		assertEquals(id, customer.getId());

	}

}
