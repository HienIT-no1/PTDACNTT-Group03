package com.example.English.teaching.center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.English.teaching.center.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    List<Course> findByCategory_NameIgnoreCase(String name);

    @Query("SELECT c FROM Course c WHERE c.isActive = true AND (" +
           "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.slug) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.courseGoal) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Course> searchFlexible(@Param("keyword") String keyword);
}
