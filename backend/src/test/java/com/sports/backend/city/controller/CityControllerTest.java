package com.sports.backend.city.controller;

import com.sports.backend.city.dto.CityResponseDto;
import com.sports.backend.city.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    /**
     * getCities API 테스트: 도시 목록을 조회했을 때 올바른 데이터가 반환되는지 확인합니다.
     */
    @Test
    void getCities_ReturnsCityList() throws Exception {
        // Given: Mock 데이터 설정
        when(cityService.getCityList()).thenReturn(Arrays.asList(
                new CityResponseDto(1, "서울특별시", "11"),
                new CityResponseDto(2, "부산광역시", "26")
        ));

        // When & Then: API 호출 및 검증
        mockMvc.perform(get("/api/cities").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].cityName").value("서울특별시"))
                .andExpect(jsonPath("$.data[1].cityCode").value("26"));
    }

    /**
     * importCities API 테스트: CSV 데이터를 로드했을 때 성공 메시지가 반환되는지 확인합니다.
     */
    @Test
    void importCities_ReturnsSuccessMessage() throws Exception {
        doNothing().when(cityService).importCityData(anyString());

        mockMvc.perform(post("/api/cities/import").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("도시 데이터가 성공적으로 저장되었습니다."));
    }
}
