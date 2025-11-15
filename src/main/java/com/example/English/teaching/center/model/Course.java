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
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseFormat format;

    @Column(length = 255)
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseAccessType accessType;

    @Column(length = 255)
    private String accessPassword; 

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    // --- Relationships ---

    // Many-to-One tới Account (Người tạo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Account createdBy;

    // One-to-Many tới Testimonials
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Testimonial> testimonials;

    // Many-to-Many tới Documents
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Course_Documents",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Document> documents;

    public enum CourseFormat {
        ONLINE,
        OFFLINE
    }

    public enum CourseAccessType {
        FREE,
        PRO
    }
}