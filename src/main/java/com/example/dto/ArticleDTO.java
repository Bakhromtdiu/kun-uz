package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.ProfileEntity;
import com.example.entity.RegionEntity;
import com.example.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private ArticleStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Integer viewCount;
    private Integer sharedCount;

    private Integer imageId;
    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publisherId;

    private AttachEntity image;
    private RegionEntity region;
    private CategoryEntity category;
    private ProfileEntity moderator;
    private ProfileEntity publisher;
}
