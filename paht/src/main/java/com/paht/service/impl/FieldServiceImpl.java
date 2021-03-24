package com.paht.service.impl;

import com.paht.model.Field;
import com.paht.repository.FieldRepository;
import com.paht.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldRepository fieldRepository;

    @Override
    public List<Field> findAll(Long is_delete) {
        return fieldRepository.findAll(is_delete);
    }

    @Override
    public void create(Field field) {
        fieldRepository.save(field);
    }

    @Override
    public void update(String field_name, Long field_id) {
        fieldRepository.update(field_name, field_id);
    }

    @Override
    public void delete(Long field_id) {
        fieldRepository.delete(field_id);
    }

    @Override
    public void restore(Long field_id) {
        fieldRepository.restore(field_id);
    }


}
