package com.microservices.items.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservices.items.entity.Item;


public interface ItemRepository extends CrudRepository<Item, Long> {
}
