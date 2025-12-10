package com.example.English.teaching.center.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "material_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(unique = true, length = 100)
    private String slug;
}
