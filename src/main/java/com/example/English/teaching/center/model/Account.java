package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity 
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole role;

    @Column(length = 255)
    private String avatarUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    // --- Relationships ---

    // 1-to-1 với TeacherProfile
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TeacherProfile profile;

    // 1-to-Many với Courses
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> createdCourses;

    // 1-to-Many với Articles
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Article> articles;

    // 1-to-Many với Documents
    @OneToMany(mappedBy = "uploader", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Document> documents;

    public enum AccountRole {
        TEACHER,
        ADMIN
    }
}