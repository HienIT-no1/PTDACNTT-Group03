package com.example.English.teaching.center.controller;

import com.example.English.teaching.center.model.Question;
import com.example.English.teaching.center.model.Test;
import com.example.English.teaching.center.repository.QuestionRepository;
import com.example.English.teaching.center.service.TestService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final QuestionRepository questionRepository;

    @GetMapping("/user/test")
    public String viewTests(Model model) {
        model.addAttribute("listTests", testService.getAllTests());
        return "user/test"; 
    }

    @GetMapping("/user/test/start/{id}")
    public String startTest(@PathVariable("id") Long id, Model model){
        Test test = testService.findById(id);

        List<Question> questions = questionRepository.findByTestId(id);

        model.addAttribute("test", test);
        model.addAttribute("questions", questions);

        return "user/test-detail";
    }


    @PostMapping("/user/test-detail/submit")
    public String submitTest(@RequestParam("testId") Long testId, 
                             HttpServletRequest request, 
                             Model model) {
        
        Test test = testService.findById(testId);
        List<Question> questions = questionRepository.findByTestId(testId);

        int correctCount = 0;
        int totalScore = 0;

        for (Question q : questions) {
            String userAnswer = request.getParameter("answer_" + q.getId());
            if (userAnswer != null && userAnswer.equals(q.getCorrectOption())) {
                correctCount++;
                totalScore += q.getScore();
            }
        }

        int totalQuestions = questions.size();
        int percentage = (totalQuestions > 0) ? (correctCount * 100 / totalQuestions) : 0;

        model.addAttribute("test", test);
        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalScore", totalScore);
        model.addAttribute("percentage", percentage);

        return "user/test-result"; 
    }
}