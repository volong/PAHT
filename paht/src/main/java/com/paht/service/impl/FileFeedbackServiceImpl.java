package com.paht.service.impl;

import com.paht.model.FileFeedback;
import com.paht.repository.FileFeedbackRepository;
import com.paht.service.FileFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileFeedbackServiceImpl implements FileFeedbackService {
    @Autowired
    private FileFeedbackRepository fileFeedbackRepository;

    @Override
     public List<FileFeedback> findByFb_id(Long fb_id) {
        return fileFeedbackRepository.findByFb_id(fb_id);
    }

    @Override
    public void save(FileFeedback fileFeedback) {
        fileFeedbackRepository.save(fileFeedback);
    }
}
