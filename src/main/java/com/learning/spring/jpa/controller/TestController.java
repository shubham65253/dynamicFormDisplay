package com.learning.spring.jpa.controller;

import com.learning.spring.jpa.entities.Component;
import com.learning.spring.jpa.entities.Form;
import com.learning.spring.jpa.entities.Row;
import com.learning.spring.jpa.service.ComponentService;
import com.learning.spring.jpa.service.FormService;
import com.learning.spring.jpa.service.RowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private RowService rowService;

    @Autowired
    private FormService formService;

    @GetMapping("/getallcomponents")
    public List<Component> getAllComponent(){
        return componentService.getAllComponent();
    }
    @PostMapping("/setcomponent")
    public Component setComponent(@Valid @RequestBody Component component){
        return componentService.saveComponent(component);
    }
    @GetMapping("/getallrows")
    public List<Row> getAllRows(){
        List<Row> rowList = rowService.getAllRows();
        return rowList;
    }
    @GetMapping("/getform")
    public Form getForm(){
        return formService.getAllForm();
    }

}
