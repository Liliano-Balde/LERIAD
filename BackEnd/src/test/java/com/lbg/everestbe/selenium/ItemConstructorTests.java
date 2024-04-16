package com.lbg.everestbe.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.lbg.everestbe.domain.Item;

public class ItemConstructorTests {

	@Test
	public void testAllConstructors() {

		Integer id = 1;
		String name = "Water Bottle";
		Double price = 1.99;
		Long quantity = 2L;
		String description = "Mineral Water";

		Item item = new Item(id, name, price, quantity, description);

		assertEquals(id, item.getId());
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());
		assertEquals(quantity, item.getQuantity());
		assertEquals(description, item.getDescription());
	}

	@Test
	public void testIdNamPriQuaConstructor() {

		Integer id = 2;
		String name = "Dice";
		Double price = 0.99;
		Long quantity = 1L;

		Item item = new Item(id, name, price, quantity);

		assertEquals(id, item.getId());
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());
		assertEquals(quantity, item.getQuantity());

	}

	@Test
	public void testNamPriConstructor() {

		String name = "Laptop";
		Double price = 1999.99;

		Item item = new Item(name, price);

		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());

	}

	@Test
	public void testNamPriQuaConstructor() {

		String name = "PS5";
		Double price = 699.99;
		Long quantity = 2L;

		Item item = new Item(name, price, quantity);

		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice());
		assertEquals(quantity, item.getQuantity());

	}

	@Test
	public void testIdConstructor() {
		Integer id = 4;
		Item item = new Item(id);
		assertEquals(id, item.getId());

	}

}
