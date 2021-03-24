package com.paht.controller;

import com.paht.model.Interface.IStaticalDep;
import com.paht.service.impl.DepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class DepController {

    @Autowired
    private DepServiceImpl depService;

    @GetMapping("/dep")
    public List<IStaticalDep> findDep() {
        return depService.findDep();
    }
}
