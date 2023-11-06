package com.thefirstrow.dreammate.repository;

import com.thefirstrow.dreammate.model.entity.SequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceEntityRepository extends JpaRepository<SequenceEntity, Long> {
}
