package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private AttachEntity photo;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column
    private String email;

}
