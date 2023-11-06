package com.thefirstrow.dreammate.repository;

import com.thefirstrow.dreammate.model.entity.CameraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraEntityRepository extends JpaRepository<CameraEntity, Long> {
}
