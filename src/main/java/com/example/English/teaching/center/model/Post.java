package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
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

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostType type;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PostStatus status;

    // --- SEO Fields ---
    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_keyword")
    private String metaKeyword;

    @Column(name = "meta_description", columnDefinition = "TEXT")
    private String metaDescription;
    // ------------------

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude 
    @Builder.Default
    private List<PostSection> sections = new ArrayList<>();

    public enum PostType {
        BLOG, NEWS, EVENT
    }

    public enum PostStatus {
        DRAFT, PUBLISHED, HIDDEN
    }

    public void addSection(PostSection section) {
        sections.add(section);
        section.setPost(this);
    }

    public void removeSection(PostSection section) {
        sections.remove(section);
        section.setPost(null);
    }

    @Transient
    public String getSectionsJson(){
        try{
            return new ObjectMapper().writeValueAsString(this.sections);
        }catch(JsonProcessingException e){
            return "[]";
        }
    }
}