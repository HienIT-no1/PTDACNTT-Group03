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

    @Lob 
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostType type; 

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

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

    public String getSummary() {
        if (this.content == null) return "";
        String cleanText = this.content.replaceAll("\\<.*?\\>", ""); 
        return cleanText.length() > 150 ? cleanText.substring(0, 150) + "..." : cleanText;
    }
}