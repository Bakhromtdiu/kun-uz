package com.example.repository;

import com.example.entity.ArticleEntity;
import com.example.enums.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
    @Transactional
    @Modifying
    @Query("update ArticleEntity set visible=false where id=?1")
    void deleteArticleById(String id);


    @Transactional
    @Modifying
    @Query("update ArticleEntity set title=?1, description=?2, content=?3, " +
            "image.id=?4, region.id=?5, category.id=?6," +
            "status=?7 where id = ?8")
    void update(String title, String description, String content,
                Integer imageId, Integer regionId, Integer categoryId,
                ArticleStatus status, String id);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set status=?1, publishedDate = LOCAL DATETIME where id=?2")
    int changeStatus(ArticleStatus status, String id);


    @Query("from ArticleEntity where status = ?1 order by createdDate desc ")
    List<ArticleEntity> getLastFive(ArticleStatus status);

    @Query("from ArticleEntity where status = ?1 order by createdDate desc ")
    List<ArticleEntity> getLastThree(ArticleStatus status);

    @Query(value = "SELECT * FROM article where ", nativeQuery = true)
    List<ArticleEntity> getLastEight();

}
