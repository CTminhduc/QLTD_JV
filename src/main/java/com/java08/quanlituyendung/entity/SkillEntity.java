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

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Skill")
public class SkillEntity extends BaseEntity{
    @Column(name = "skillName")
    private String skillName;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
    @Column(name = "createdBy")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime createTime;
    @JsonIgnore
    @ManyToMany(mappedBy = "skillsEntities")
    private List<PositionEntity> positions = new ArrayList<>();
    @OneToMany(mappedBy="skill",cascade = CascadeType.ALL)
    private List<QuestionEntity> questions = new ArrayList<>();
    public SkillEntity(String name){
        this.skillName = name;
    }

}
