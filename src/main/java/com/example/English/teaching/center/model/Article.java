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
@Table(name = "Articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleStatus status;

    @Column(length = 255)
    private String thumbnailUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    // --- Relationships ---

    // Many-to-One tới Account (Tác giả)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Account author;

    public enum ArticleCategory {
        NEW,
        RECRUITMENT,
        BLOG,
        LEARNING_PATH,
        LEARNING_METHOD
    }

    public enum ArticleStatus {
        draft,
        published
    }
}