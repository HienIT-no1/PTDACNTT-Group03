package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String slug;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive;

    // --- Chi tiết khóa học ---
    @Column(name = "target_audience", columnDefinition = "TEXT")
    private String targetAudience;

    @Column(name = "course_goal", columnDefinition = "TEXT")
    private String courseGoal;

    @Column(name = "total_sessions")
    private Integer totalSessions;

    @Column(name = "session_duration")
    private String sessionDuration;

    @Column(name = "coaching_sessions")
    private Integer coachingSessions;

    @Column(name = "class_size")
    private String classSize;

    @Column(name = "learning_format")
    private String learningFormat;

    // --- Cam kết đầu ra ---
    @Column(name = "commitment_vocab_grammar", columnDefinition = "TEXT")
    private String commitmentVocabGrammar;

    @Column(name = "commitment_listening", columnDefinition = "TEXT")
    private String commitmentListening;

    @Column(name = "commitment_reading", columnDefinition = "TEXT")
    private String commitmentReading;

    @Column(name = "commitment_speaking", columnDefinition = "TEXT")
    private String commitmentSpeaking;

    @Column(name = "commitment_writing", columnDefinition = "TEXT")
    private String commitmentWriting;

    // --- Timestamps ---
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Xử lí đầu ra cam kết 
    private String[] parseCommitment(String content){
        if(content == null || content.trim().isEmpty()){
            return new String[0];
        }
        return content.trim().split("\\R");
    }

    public String[] getVocabGrammarList(){
        return parseCommitment(this.commitmentVocabGrammar);
    }

    public String[] getListeningList(){
        return parseCommitment(this.commitmentListening);
    }

    public String[] getSpeakingList(){
        return parseCommitment(this.commitmentSpeaking);
    }

    public String[] getReadingList(){
        return parseCommitment(this.commitmentReading);
    }

    public String[] getWritingList(){
        return parseCommitment(this.commitmentWriting);
    }
}