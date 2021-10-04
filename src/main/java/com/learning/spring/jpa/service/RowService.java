package com.learning.spring.jpa.service;

import com.learning.spring.jpa.entities.Row;
import com.learning.spring.jpa.repository.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowService {

    @Autowired
    private RowRepository rowRepository;

    public List<Row> getAllRows(){
        return rowRepository.findAll();
    }
}
