package com.lh.service.imp;

import com.lh.entity.medical.Medical;
import com.lh.entity.medical.node.CureNode;
import com.lh.entity.medical.node.MedicalNode;
import com.lh.entity.medical.node.SymptomNode;
import com.lh.repository.medical.MedicalRepository;
import com.lh.repository.medical.node.CureNodeRepository;
import com.lh.repository.medical.node.MedicalNodeRepository;
import com.lh.repository.medical.node.SymptomNodeRepository;
import com.lh.service.MedicalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicalServiceImpl implements MedicalService {
    @Autowired
    private MedicalRepository medicalRepository;

    //通过名字查找
    @Override
    public Medical findMedicalByName(String name) {
        return medicalRepository.findMedicalByName(name);
    }


}
