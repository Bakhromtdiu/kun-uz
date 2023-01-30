package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    private String key;
    private String nameUz;
    private String nameRus;
    private String nameEng;
    private Boolean visible;
    private Integer creatorId;
    private LocalDateTime createdDate;
}
