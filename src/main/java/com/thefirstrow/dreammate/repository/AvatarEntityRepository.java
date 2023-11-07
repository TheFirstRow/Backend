package com.thefirstrow.dreammate.repository;

import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarEntityRepository extends CrudRepository<AvatarEntity, Long> {

    Optional<AvatarEntity> findById(Long id);
}
