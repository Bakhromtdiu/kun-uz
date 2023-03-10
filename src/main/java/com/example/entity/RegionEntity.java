package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String key = String.valueOf(UUID.randomUUID());

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_rus")
    private String nameRus;

    @Column(name = "name_eng")
    private String nameEng;

    @Column
    private Boolean visible = Boolean.TRUE;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private String name;
}
