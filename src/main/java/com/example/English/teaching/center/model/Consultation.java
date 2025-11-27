package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(length = 100)
    private String location; // Khu vực sống

    @Column(name = "learning_needs", columnDefinition = "TEXT")
    private String learningNeeds;

    // Dùng Enum để quản lý trạng thái xử lý (Chưa gọi, Đã gọi...)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ConsultationStatus status = ConsultationStatus.PENDING;

    @Column(columnDefinition = "TEXT")
    private String note; // Ghi chú của Admin

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum ConsultationStatus{
        PENDING,    // Chờ xử lý
        CONTACTED,  // Đã liên hệ
        DONE,       // Hoàn thành/Đã chốt
        CANCELLED   // Hủy bỏ
    }
}
