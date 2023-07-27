package com.java08.quanlituyendung.dto;

import com.java08.quanlituyendung.entity.FieldEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {
    Long pId;
    Long sId;
    FieldEnum fieldEnum;
    String question;
}
