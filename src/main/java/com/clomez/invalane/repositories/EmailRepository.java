package com.clomez.invalane.repositories;

import com.clomez.invalane.beans.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository  extends CrudRepository<Email, Long>{
}
