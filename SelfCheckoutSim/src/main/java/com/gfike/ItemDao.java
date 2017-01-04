package com.gfike;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ItemDao extends CrudRepository<Item, String> {
	Item findByPlu(String plu);

	List<Item> findAll();

	Item findByName(String name);
}
