package com.java08.quanlituyendung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.java08.quanlituyendung.entity.InterviewEntity;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<InterviewEntity, Long> {
    List<InterviewEntity> findByJobPostingEntityId (Long i);
}

