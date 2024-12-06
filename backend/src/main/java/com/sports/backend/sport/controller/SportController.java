package com.sports.backend.sport.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.sport.dto.SportDto;
import com.sports.backend.sport.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
@RequiredArgsConstructor
public class SportController {
    private final SportService sportService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SportDto>>> getAllSports() {
        List<SportDto> sports = sportService.getAllSports();
        return ResponseEntity.ok(new ApiResponse<>(200, "스포츠 목록 조회 성공", sports));
    }
}
