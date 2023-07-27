package com.java08.quanlituyendung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Question")
public class QuestionEntity extends BaseEntity{
    @Column(name = "question")
    private String question;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
    @Column(name = "createdBy")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime createTime;
    @Column(name = "field")
    @Enumerated(EnumType.STRING)
    private FieldEnum field;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="skill_id", nullable=false)
    private SkillEntity skill;
    public QuestionEntity(String name,Boolean isDeleted){
        this.question = name;
        this.isDeleted = isDeleted;
    }

}
