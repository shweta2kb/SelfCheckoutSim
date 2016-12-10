package com.gfike;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<Item, Integer>{
	Order findByUid(int uid);

}
