package com.clomez.invalane.repositories;

import com.clomez.invalane.beans.Options;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends CrudRepository<Options, Long> {
}
