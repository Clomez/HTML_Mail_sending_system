package com.clomez.invalane.repositories;

import com.clomez.invalane.beans.Images;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends CrudRepository<Images, Long> {
}
