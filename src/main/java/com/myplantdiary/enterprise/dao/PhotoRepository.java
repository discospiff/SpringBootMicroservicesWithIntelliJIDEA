package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
