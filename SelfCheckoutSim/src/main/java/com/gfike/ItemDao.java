package com.gfike;

import javax.transaction.Transactional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ItemDao extends CrudRepository<Item, Integer> {
	Item findByPlu(int plu);
}
