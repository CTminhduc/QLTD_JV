package com.java08.quanlituyendung.dto.UserAccountPayload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountCustomResponseDTO {
    private String id;
    private String email;
    private String avatar;
    private String fullName;
}
