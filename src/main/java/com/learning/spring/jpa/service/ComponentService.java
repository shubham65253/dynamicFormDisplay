package com.learning.spring.jpa.service;

import com.learning.spring.jpa.entities.Component;
import com.learning.spring.jpa.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository repository;

    public List<Component> getAllComponent(){
        List<Component> ComponentList = repository.findAll();
        return ComponentList;
    }

    public Component saveComponent(Component component) {
        return repository.save(component);
    }
}
