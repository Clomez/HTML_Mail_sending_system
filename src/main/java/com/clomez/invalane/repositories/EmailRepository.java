package com.clomez.invalane.repositories;

import com.clomez.invalane.beans.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository  extends CrudRepository<Email, Long>{
}
