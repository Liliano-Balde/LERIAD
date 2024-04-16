package com.lbg.everestbe.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.everestbe.domain.Item;
import com.lbg.everestbe.service.ItemService;

@RequestMapping("/item")
@CrossOrigin
@RestController
public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
		return this.service.createItem(newItem);
	}

	@PostMapping("/addItem/{customerId}")
	public ResponseEntity<Item> addItemToCustomer(@PathVariable int customerId, @RequestBody Item newItem) {
		return this.service.addItemToCustomer(customerId, newItem);
	}

	@PatchMapping("/removeItem/{itemId}")
	public ResponseEntity<Item> removeItemFromCustomer(@PathVariable int itemId) {
		return this.service.removeItemFromCustomer(itemId);
	}

	@GetMapping("/get")
	public List<Item> getItem() {
		return this.service.getItem();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Item> getItem(@PathVariable int id) {
		return this.service.getItem(id);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteItem(@PathVariable int id) {
		return this.service.deleteItem(id);

	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item updateItem) {

		return this.service.updateItem(id, updateItem);

	}

}
