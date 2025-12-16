package com.example.English.teaching.center.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "section_title")
    private String sectionTitle;

    @Lob
    @Column(name = "section_content", columnDefinition = "LONGTEXT")
    private String sectionContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", length = 20)
    private MediaType mediaType; // NONE, IMAGE, VIDEO, YOUTUBE

    @Column(name = "media_url", columnDefinition = "TEXT")
    private String mediaUrl;

    @Column(name = "media_caption")
    private String mediaCaption;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type", length = 20)
    private SectionType sectionType; // TEXT, QUOTE, HIGHLIGHT

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @ToString.Exclude 
    @JsonIgnore
    private Post post;

    public enum MediaType {
        NONE, IMAGE, YOUTUBE
    }

    public enum SectionType {
        TEXT, QUOTE, HIGHLIGHT
    }
}