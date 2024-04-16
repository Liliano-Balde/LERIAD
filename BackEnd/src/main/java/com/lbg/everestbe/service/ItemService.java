package com.lbg.everestbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.everestbe.domain.Customer;
import com.lbg.everestbe.domain.Item;
import com.lbg.everestbe.repo.CustomerRepo;
import com.lbg.everestbe.repo.ItemRepo;

@Service
public class ItemService {

	private ItemRepo repo;
	private CustomerRepo customerRepo;

	public ResponseEntity<Item> createItem(Item newItem) {
		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public ItemService(ItemRepo repo, CustomerRepo customerRepo) {
		super();
		this.repo = repo;
		this.customerRepo = customerRepo;
	}

	public List<Item> getItem() {
		return this.repo.findAll();
	}

	public ResponseEntity<Item> addItemToCustomer(int customerId, Item newItem) {
		Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Customer customer = optionalCustomer.get();

		if (optionalCustomer.isEmpty()) {

			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Optional<Item> found = this.repo.findById(newItem.getId());

		Item item = found.get();

		item.setCustomer(customer);
		Item savedItem = this.repo.save(item);

		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

	}

	public ResponseEntity<Item> removeItemFromCustomer(int itemId) {
		Optional<Item> found = this.repo.findById(itemId);

		Item item = found.get();

		item.setCustomer(null);
		Item savedItem = this.repo.save(item);

		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

	}

	public ResponseEntity<Item> getItem(int id) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item body = found.get();
		return ResponseEntity.ok(body);
	}

	public boolean deleteItem(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public ResponseEntity<Item> updateItem(int id, Item newItem) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {

			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}

		Item body = found.get();

		if (newItem.getName() != null)
			body.setName(newItem.getName());
		if (newItem.getPrice() != null)
			body.setPrice(newItem.getPrice());
		if (newItem.getQuantity() != null)
			body.setQuantity(newItem.getQuantity());
		if (newItem.getDescription() != null)
			body.setDescription(newItem.getDescription());

		Item updated = this.repo.save(body);

		return ResponseEntity.ok(updated);
	}

//	public ResponseEntity<Item> updateItem(int id, int customerId) {
//		Optional<Item> found = this.repo.findById(id);
//
//		if (found.isEmpty()) {
//			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
//		}
//		Item body = found.get();
//
//		body.setCustomer(this.customerRepo.findById(customerId).get());
//		return ResponseEntity.ok(this.repo.save(body));
//	}

}
