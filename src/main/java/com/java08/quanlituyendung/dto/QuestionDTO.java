package com.java08.quanlituyendung.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java08.quanlituyendung.entity.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDTO{
    private Long id;
    private String question;

}