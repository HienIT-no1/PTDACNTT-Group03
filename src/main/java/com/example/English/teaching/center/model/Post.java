package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String slug;

    @Lob // Báo cho JPA biết đây là dữ liệu lớn (Long Text)
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    // Bạn có thể dùng Enum PostType ở dưới
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostType type; 

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    // Relationship: Admin đăng bài
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @PrePersist
    protected void onCreate() {
        if (publishedAt == null) {
            publishedAt = LocalDateTime.now();
        }
    }

    public enum PostType{
        BLOG,
        NEWS, 
        EVENT
    }
}