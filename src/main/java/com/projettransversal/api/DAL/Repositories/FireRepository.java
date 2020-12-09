package com.projettransversal.api.DAL.Repositories;

import com.projettransversal.api.Models.Fire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireRepository extends CrudRepository<Fire, Integer> {

}
