package com.paht.repository;

import com.paht.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query(value = "SELECT * FROM fields WHERE is_delete = :is_delete ORDER BY field_id ASC", nativeQuery = true)
     List<Field> findAll(@Param("is_delete") Long is_delete);

    @Modifying
    @Query(value = "UPDATE fields SET field_name = :field_name WHERE field_id = :field_id", nativeQuery = true)
    void update(@Param("field_name") String field_name, @Param("field_id") Long field_id);

    @Modifying
    @Query(value = "UPDATE fields SET is_delete = 1 WHERE field_id = :field_id", nativeQuery = true)
    void delete(@Param("field_id") Long field_id);

    @Modifying
    @Query(value = "UPDATE fields SET is_delete = 0 WHERE field_id = :field_id",nativeQuery = true)
    void restore(@Param("field_id") Long field_id);
}

