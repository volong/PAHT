package com.paht.repository;

import com.paht.model.FileFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileFeedbackRepository extends JpaRepository<FileFeedback, Long> {

    @Query(value = "SELECT * FROM files_feedback WHERE fb_id = :fb_id", nativeQuery = true)
    List<FileFeedback> findByFb_id(@Param("fb_id") Long fb_id);

}
