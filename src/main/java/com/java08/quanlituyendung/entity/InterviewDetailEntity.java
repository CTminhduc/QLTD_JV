package com.java08.quanlituyendung.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "InterviewDetail")
public class InterviewDetailEntity extends BaseEntity {

    // 1 can di date, ben candidate ko can tham chieu lai
    // lay tu danh sach cv trong job posting
    @ManyToOne
    @JoinColumn(name = "candidateId")
    private UserAccountEntity candidate;

    // room
    @OneToOne
    @JoinColumn(name = "interviewId")
    private InterviewEntity interview;

    private String date;
    private String time;

    @Column(columnDefinition = "TEXT")
    private String description;

    private float averageScore;
    private boolean status;
    private String comment;

    @Column(name = "questions", columnDefinition = "TEXT")
    private String questions;

}
