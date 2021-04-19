package com.lh.service.imp;

import com.lh.entity.medical.Medical;
import com.lh.repository.medical.MedicalRepository;
import com.lh.service.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalServiceImpl implements MedicalService {
    @Autowired
    private MedicalRepository medicalRepository;

    @Override
    public Medical findMedicalByName(String name) {
        return medicalRepository.findByName(name);
    }
}
