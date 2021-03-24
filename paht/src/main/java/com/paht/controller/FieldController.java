package com.paht.controller;

import com.paht.model.Field;
import com.paht.service.impl.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FieldController {

    @Autowired
    private FieldServiceImpl fieldService;

    @GetMapping("/fields/{is_delete}")
    public List<Field> findAll(@PathVariable("is_delete") Long is_delete){
        return fieldService.findAll(is_delete);
    }

    @PostMapping("/field")
    void create(@RequestBody Field field){
        fieldService.create(field);
    }

    @PutMapping("/field")
    void update(@Param("field_name") String field_name, @Param("field_id") Long field_id) {
        fieldService.update(field_name, field_id);
    }

    @DeleteMapping("/field/{field_id}")
    void delete(@PathVariable("field_id") Long field_id) {
        fieldService.delete(field_id);
    }

    @PutMapping("/field/restore/{field_id}")
    void restore(@PathVariable("field_id") Long field_id) {
        fieldService.restore(field_id);
    }


}
