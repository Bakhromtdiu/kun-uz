package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/admin/create")
    public ResponseEntity<?> create(HttpServletRequest request,
                                    @RequestBody ArticleDTO articleDTO) {
        Integer adminId = JwtUtil.getIdFromHeader(request, ProfileRole.MODERATOR);

        ArticleDTO response = articleService.create(adminId, articleDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> updateById(HttpServletRequest request,
                                        @PathVariable String id,
                                        @RequestBody ArticleDTO articleDTO) {
        JwtUtil.getIdFromHeader(request, ProfileRole.MODERATOR);

        ArticleDTO response = articleService.updateById(id, articleDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteById(HttpServletRequest request,
                                        @PathVariable String id) {
        JwtUtil.getIdFromHeader(request, ProfileRole.MODERATOR);

        Boolean response = articleService.deleteById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/status")
    public ResponseEntity<?> changeStatus(HttpServletRequest request,
                                          @RequestParam String id,
                                          @RequestParam ArticleStatus status) {
        JwtUtil.getIdFromHeader(request, ProfileRole.PUBLISHER);

        String response = articleService.changeStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/last_five")
    public ResponseEntity<?> lastFive(@RequestParam ArticleStatus status) {
        List<ArticleDTO> response = articleService.lastFive(status);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/last_three")
    public ResponseEntity<?> lastThree(@RequestParam ArticleStatus status) {
        List<ArticleDTO> response = articleService.lastThree(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/last_eight")
    public ResponseEntity<?> lastEight(){
        List<ArticleDTO> response = articleService.lastEight();
        return ResponseEntity.ok(response);
    }

}
