package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Testimonials")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testimonialId;

    @Column(nullable = false, length = 100)
    private String studentName; // Tên do học viên tự nhập

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestimonialStatus status; // Mặc định là 'pending'

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    // --- Relationships ---

    // Many-to-One tới Course (Khóa học được đánh giá)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    public enum TestimonialStatus {
        PENDING,
        APPROVED
    }
}