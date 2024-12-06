package com.sports.backend.sport.service;

import com.sports.backend.sport.domain.Sport;
import com.sports.backend.sport.dto.SportDto;
import com.sports.backend.sport.dao.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportService {
    private final SportRepository sportRepository;

    // 스포츠 목록 조회
    public List<SportDto> getAllSports() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream()
                .map(sport -> SportDto.builder()
                        .sportId(sport.getSportId())
                        .sportName(sport.getSportName())
                        .build())
                .collect(Collectors.toList());
    }
}
