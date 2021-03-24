package com.paht.service;

import com.paht.model.FileFeedback;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FileFeedbackService {

    List<FileFeedback> findByFb_id(@Param("fb_id") Long fb_id);

    void save(@RequestBody FileFeedback fileFeedback);
}
