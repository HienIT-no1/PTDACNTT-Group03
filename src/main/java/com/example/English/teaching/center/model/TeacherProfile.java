package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Teacher_Profiles")
public class TeacherProfile {

    @Id
    private Integer accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Account account;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(length = 255)
    private String specialization;

    private Integer yearsOfExperience;

    private boolean isFeatured;
}