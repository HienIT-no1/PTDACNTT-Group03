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
@Table(name = "Documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 255)
    private String fileUrl; // Đường dẫn file hoặc link video

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType type;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    // --- Relationships ---

    // Many-to-One tới Account (Người tải lên)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Account uploader;

    // Many-to-Many với Courses (được map bởi 'documents' trong Course)
    @ManyToMany(mappedBy = "documents", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> courses;

    public enum DocumentType {
        PDF,
        VIDEO,
        WORD,
        OTHER
    }
}