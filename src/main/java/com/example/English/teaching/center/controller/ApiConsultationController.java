package com.example.English.teaching.center.controller;

import com.example.English.teaching.center.model.Consultation;
import com.example.English.teaching.center.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultation")
public class ApiConsultationController {

    private final ConsultationService consultationService;

    public ApiConsultationController(ConsultationService consultationService){
        this.consultationService = consultationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerConsultation(@RequestBody Consultation consultation) {
        try {
            Consultation saved = consultationService.saveConsultation(consultation);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi đăng ký: " + e.getMessage());
        }
    }
}