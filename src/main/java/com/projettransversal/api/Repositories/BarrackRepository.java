package com.projettransversal.api.Repositories;

import com.projettransversal.api.Models.Barrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarrackRepository extends CrudRepository<Barrack, Long> {

}
