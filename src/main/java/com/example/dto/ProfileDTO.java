package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private Boolean visible;
    private ProfileRole role;
    private ProfileStatus status;
    private Integer creatorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private String imageId;
    private AttachDTO image;
    private String jwt;
}
