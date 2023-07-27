package com.java08.quanlituyendung.repository;

import com.java08.quanlituyendung.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    Optional<QuestionEntity> findOneById(long id);
}
