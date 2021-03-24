package com.paht.service;

import com.paht.model.Field;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FieldService {

    List<Field> findAll(@Param("is_delete") Long is_delete);

    void create(@RequestBody  Field field);

    void update(@Param("field_name") String field_name, @Param("field_id") Long field_id);

    void delete(@Param("field_id") Long field_id);

    void restore(@Param("field_id") Long field_id);

}
