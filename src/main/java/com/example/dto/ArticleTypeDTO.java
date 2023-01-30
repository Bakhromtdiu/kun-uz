package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
    private Integer id;
    private String key;
    private String nameUz;
    private String nameRus;
    private String nameEng;
    private LocalDateTime createdDate;
    private Integer creatorId;
}
