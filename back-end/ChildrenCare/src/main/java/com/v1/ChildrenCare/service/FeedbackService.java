package com.v1.ChildrenCare.service;

import com.v1.ChildrenCare.constaint.Result;
import com.v1.ChildrenCare.entity.Feedback;
import com.v1.ChildrenCare.model.request.CreateFeedbackRequest;
import com.v1.ChildrenCare.repository.FeedbackRepository;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackService {
    @Resource
    private FeedbackRepository feedbackRepository;

    public ResponseEntity<Result> getAllFeedback() {
        return ResponseEntity.ok(new Result("SUCCESS", "OK", feedbackRepository.findAll()));
    }

    public ResponseEntity<Result> createAndEdit(CreateFeedbackRequest request) {
        try {
            createAndEditFeedback(request);
            return ResponseEntity.ok(new Result("SUCCESS", "OK", null));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result(e.getMessage(), "NOT_FOUND", null));
        }
    }

    public ResponseEntity<Result> delete(Long id) {
        try {
            deleteFeedback(id);
            return ResponseEntity.ok(new Result("SUCCESS", "OK", null));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result(e.getMessage(), "NOT_FOUND", null));
        }
    }

    public ResponseEntity<Result> detail(Long id) {
        try {
            return ResponseEntity.ok(new Result("SUCCESS", "OK", detailFeedback(id)));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result(e.getMessage(), "NOT_FOUND", null));
        }
    }

    private void createAndEditFeedback(CreateFeedbackRequest request) {
        Feedback feedback = null;
        if (request.getId() != null) {
            feedback = feedbackRepository.findById(request.getId()).orElse(null);
            if (feedback == null) {
                throw new NullPointerException("Not found feedback");
            }
        } else {
            feedback = new Feedback();
        }
        feedback.setDate(LocalDateTime.now());
        feedback.setRating(request.getRating());
        feedback.setCustomerName(request.getCustomerName());
        feedback.setReviewText(request.getReviewText());
        feedbackRepository.save(feedback);
    }

    private void deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) {
            throw new NullPointerException("Not found feedback");
        }
        feedbackRepository.delete(feedback);
    }

    private Feedback detailFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) {
            throw new NullPointerException("Not found feedback");
        }
        return feedback;
    }
}
