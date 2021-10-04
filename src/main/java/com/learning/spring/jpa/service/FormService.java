package com.learning.spring.jpa.service;

import com.learning.spring.jpa.entities.Form;
import com.learning.spring.jpa.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public Form getAllForm(){
        return formRepository.findById(1l).get();
    }
}
