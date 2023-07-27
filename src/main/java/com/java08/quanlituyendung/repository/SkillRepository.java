
package com.java08.quanlituyendung.repository;

import com.java08.quanlituyendung.entity.SkillEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    Optional<SkillEntity>findById(long id);
    @Query("SELECT s FROM SkillEntity s JOIN s.positions p WHERE p.id = ?1 AND s.id = ?2")
    Optional<SkillEntity> getSkillNameByPositionAndSkills(Long positionId, Long skillId);
}
