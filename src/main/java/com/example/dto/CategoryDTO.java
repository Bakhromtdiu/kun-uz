package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Integer id;
    private String key = String.valueOf(UUID.randomUUID());
    private String nameUz;
    private String nameRu;
    private String nameEng;
    private Boolean visible;
    private LocalDateTime createdDate;
    private Integer creatorId;
}
