package com.example.service;

import com.example.dto.ArticleDTO;
import com.example.entity.ArticleEntity;
import com.example.enums.ArticleStatus;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProfileService profileService;


    public ArticleDTO create(Integer adminId, ArticleDTO articleDTO) {
        articleDTO.setCreatedDate(LocalDateTime.now());
        articleDTO.setModeratorId(adminId);
        articleDTO.setStatus(ArticleStatus.NOT_PUBLISHED);

        ArticleEntity article = toEntity(articleDTO);
        articleRepository.save(article);

        articleDTO.setId(article.getId());
        return articleDTO;
    }

    private ArticleEntity toEntity(ArticleDTO articleDTO) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(articleDTO.getTitle());
        article.setDescription(articleDTO.getDescription());
        article.setContent(articleDTO.getContent());
        article.setSharedCount(articleDTO.getSharedCount());
        article.setImage(null);
        article.setRegion(regionService.get(articleDTO.getRegionId()));
        article.setCategory(categoryService.get(articleDTO.getCategoryId()));
        article.setModerator(profileService.get(articleDTO.getModeratorId()));
        article.setCreatedDate(articleDTO.getCreatedDate());
        article.setStatus(articleDTO.getStatus());
        return article;
    }

    public Boolean deleteById(String id) {
        ArticleEntity entity = get(id);

        articleRepository.deleteArticleById(id);
        return true;
    }

    private ArticleEntity get(String id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Article Not found");
        });
    }

    public ArticleDTO updateById(String id, ArticleDTO articleDTO) {
        get(id);

        articleDTO.setStatus(ArticleStatus.NOT_PUBLISHED);

        articleRepository.update(articleDTO.getTitle(), articleDTO.getDescription(), articleDTO.getContent(),
                                 articleDTO.getImageId(), articleDTO.getRegionId(), articleDTO.getCategoryId(),
                                 articleDTO.getStatus(), id);


        articleDTO.setId(id);
        return articleDTO;
    }

    public String changeStatus(String id, ArticleStatus status) {
        int update = articleRepository.changeStatus(status, id);

        if(update==0){
            throw new RuntimeException("ERROR");
        }
        return "Status updated";
    }

    public List<ArticleDTO> lastFive(ArticleStatus status) {
        List<ArticleEntity> getLastFive = articleRepository.getLastFive(status);

        List<ArticleDTO> dtoList = new ArrayList<>();

        for (ArticleEntity article : getLastFive) {
            ArticleDTO articleDTO = toDTO(article);
            dtoList.add(articleDTO);
        }
        return dtoList;
    }

    private ArticleDTO toDTO(ArticleEntity article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(article.getTitle());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setContent(article.getContent());
        articleDTO.setCreatedDate(article.getCreatedDate());
        articleDTO.setPublishedDate(article.getPublishedDate());
        return articleDTO;
    }

    public List<ArticleDTO> lastThree(ArticleStatus status) {
        List<ArticleEntity> getLastThree = articleRepository.getLastThree(status);

        List<ArticleDTO> dtoList = new ArrayList<>();

        for (ArticleEntity article : getLastThree) {
            ArticleDTO articleDTO = toDTO(article);
            dtoList.add(articleDTO);
        }
        return dtoList;
    }

    public List<ArticleDTO> lastEight() {
        List<ArticleEntity> entityList = articleRepository.getLastEight();


        return null;
    }
}
