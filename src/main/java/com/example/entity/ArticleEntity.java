package com.example.entity;

import com.example.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String content;

    @Column(name = "shared_count")
    private Integer sharedCount;

    @JoinColumn(name = "image_id")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity image;

    @JoinColumn(name = "region_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RegionEntity region;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @JoinColumn(name = "moderator_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity moderator;

    @JoinColumn(name = "publisher_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity publisher;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column
    private Boolean visible = Boolean.TRUE;

    @Column(name = "view_count")
    private Integer viewCount;
}
