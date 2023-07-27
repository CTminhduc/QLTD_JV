package com.java08.quanlituyendung.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Position")
public class PositionEntity extends BaseEntity {
    @Column(name = "positionName")
    private String positionName;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
    @Column(name = "createdBy")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime createTime;


    @ManyToMany
    @JoinTable(name = "Skills_Position", joinColumns = @JoinColumn(name = "positionId"), inverseJoinColumns = @JoinColumn(name = "skillId"))
    private List<SkillEntity> skillsEntities = new ArrayList<>();
    public PositionEntity(String name){
        this.positionName=name;
    }

}
