package com.java08.quanlituyendung.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cv")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CVEntity extends BaseEntity {
    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        INPROGRESS,
        APPROVED
    }
    // vai tro la ung vien nop cv
    @ManyToMany
    @JoinTable(name = "CV_JobPosting", joinColumns = @JoinColumn(name = "cvId"), inverseJoinColumns = @JoinColumn(name = "jobPostingId"))
    private List<JobPostingEntity> jobPostingEntities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userAccountId")
    private UserAccountEntity userAccountEntity;


}
