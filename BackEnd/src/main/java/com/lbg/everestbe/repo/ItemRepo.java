package com.lbg.everestbe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbg.everestbe.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
