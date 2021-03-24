package com.paht.controller;

import com.paht.model.FileFeedback;
import com.paht.service.impl.FileFeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FileFeedbackController {

    @Autowired
    private FileFeedbackServiceImpl fileFeedbackService;

    @PostMapping("/files_feedback")
    void save(@RequestBody List<FileFeedback> fileFeedbackList) {
        for (FileFeedback fileFeedback : fileFeedbackList) {
            fileFeedbackService.save(fileFeedback);
        }
    }

    @GetMapping("/files_feedback/{fb_id}")
    public List<FileFeedback> findByFb_id(@PathVariable("fb_id") Long fb_id) {
        return fileFeedbackService.findByFb_id(fb_id);
    }
}
