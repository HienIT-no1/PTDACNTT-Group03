package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_category_id")
    private MaterialCategory materialCategory;

    @Column(nullable = false)
    private String title;

    @Column(name = "badge_text", length = 100)
    private String badgeText;

    @Column(name = "topic_group", length = 100)
    private String topicGroup;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 20)
    private String type; 

    @Column(name = "file_url", nullable = false, length = 500)
    private String fileUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Builder.Default
    private Integer views = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}