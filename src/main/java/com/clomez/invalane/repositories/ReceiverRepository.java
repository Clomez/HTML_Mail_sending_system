package com.clomez.invalane.repositories;

import com.clomez.invalane.beans.Receiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends CrudRepository<Receiver, Long> {
}
