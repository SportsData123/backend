package com.sports.backend.generalcourse.service;

import com.sports.backend.common.BaseApiClient;
import com.sports.backend.generalcourse.dto.GeneralCourseDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class GeneralCourseApiClient extends BaseApiClient<GeneralCourseDto> {

    public GeneralCourseApiClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected URI makeUri(String serviceKey, int pageNo, int numOfRows) {
        return UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/B551014/SRVC_OD_API_FACIL_COURSE/todz_api_facil_course_i")
                .queryParam("serviceKey", serviceKey)  // 인증키
                .queryParam("pageNo", pageNo)          // 페이지 번호
                .queryParam("numOfRows", numOfRows)    // 한 페이지 결과 수
                .queryParam("resultType", "json")      // 결과 형식
                .build(true)
                .toUri();// 자동 인코딩
    }

    @Override
    protected GeneralCourseDto mapToDto(JSONObject item) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String rawStartTime = (String) item.get("start_tm");
        String rawEndTime = (String) item.get("equip_tm");

        // 시간 파싱 전에 유효성 확인
        LocalTime startTime = null;
        LocalTime endTime = null;
        try {
            if (rawStartTime != null && rawStartTime.matches("\\d{2}:\\d{2}")) {
                startTime = LocalTime.parse(rawStartTime, timeFormatter);
            }
            if (rawEndTime != null && rawEndTime.matches("\\d{2}:\\d{2}")) {
                endTime = LocalTime.parse(rawEndTime, timeFormatter);
            }
        } catch (Exception e) {
            log.warn("시간 파싱 실패: start_time={}, end_time={}", rawStartTime, rawEndTime, e);
        }

        return GeneralCourseDto.builder()
                .busiRegNo((String) item.get("brno"))
                .sportNm((String) item.get("item_nm"))
                .courseNm((String) item.get("course_nm"))
                .startTime(startTime != null ? LocalTime.parse(startTime.toString()) : null)
                .endTime(endTime != null ? LocalTime.parse(endTime.toString()) : null)
                .weekday((String) item.get("lectr_weekday_val"))
                .courseSetaDesc((String) item.get("course_seta_desc_cn"))
                .facilSn((String)item.get("facil_sn"))
                .settlAmt((String)item.get("settl_amt"))
                .build();
    }
}