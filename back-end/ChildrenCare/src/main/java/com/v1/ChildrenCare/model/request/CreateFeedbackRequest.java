package com.v1.ChildrenCare.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateFeedbackRequest {
    private Long id;
    private String customerName;
    private Integer rating;
    private String reviewText;
}
