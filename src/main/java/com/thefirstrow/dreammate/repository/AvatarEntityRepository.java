package com.thefirstrow.dreammate.repository;

import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarEntityRepository extends CrudRepository<AvatarEntity, Long> {
}
