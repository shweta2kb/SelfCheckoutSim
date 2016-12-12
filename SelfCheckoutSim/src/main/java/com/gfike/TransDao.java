package com.gfike;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public interface TransDao extends CrudRepository<Trans, Integer>{
	Trans findByUid(int uid);
	List <Trans> findAll();
}
