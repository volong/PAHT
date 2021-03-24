package com.paht.controller;

import com.paht.model.Status;
import com.paht.service.impl.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StatusController {

    @Autowired
    private StatusServiceImpl statusService;

    @GetMapping("/statuses")
    public List<Status> findAll() {
     return statusService.findAll();
    }
}
