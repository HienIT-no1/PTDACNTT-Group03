package com.example.English.teaching.center.service;

import com.example.English.teaching.center.model.Consultation;
import com.example.English.teaching.center.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationService(ConsultationRepository consultationRepository){
        this.consultationRepository = consultationRepository;
    }

    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}