package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.enums.ProfileRole;
import com.example.service.ProfileService;
import com.example.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(HttpServletRequest request,
                                           @RequestBody ProfileDTO profileDTO) {
        Integer adminID = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);

        ProfileDTO profile = profileService.create(profileDTO, adminID);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/update/admin/{profileId}")
    public ResponseEntity<?> updateAdmin(HttpServletRequest request,
                                         @RequestBody ProfileDTO profileDTO,
                                         @PathVariable Integer profileId) {
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);

        ProfileDTO profile = profileService.updateAdmin(profileId, profileDTO);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestBody ProfileDTO profileDTO) {
        Integer pId = JwtUtil.getIdFromHeader(request);
        ProfileDTO response = profileService.updateUser(pId, profileDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProfileList(@RequestParam Integer page,
                                            @RequestParam Integer size,
                                            HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        Page<ProfileDTO> response = profileService.getProfilePagination(page, size);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/visible/{profileId}")
    public ResponseEntity<?> deleteProfileById(HttpServletRequest request,
                                               @PathVariable Integer profileId) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);

        Boolean result = profileService.deleteProfileById(profileId);
        return ResponseEntity.ok(result);
    }
}
