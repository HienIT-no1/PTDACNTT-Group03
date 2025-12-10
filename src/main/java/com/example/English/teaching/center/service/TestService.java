package com.example.English.teaching.center.service;

import com.example.English.teaching.center.model.Test;
import com.example.English.teaching.center.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Test findById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết với slug: " ));
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
