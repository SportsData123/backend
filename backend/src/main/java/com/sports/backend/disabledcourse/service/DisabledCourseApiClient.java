package com.sports.backend.disabledcourse.service;

import com.sports.backend.disabledcourse.dto.DisabledCourseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * `DisabledCourseApiClient`는 외부 API를 호출하여 장애인 강좌 데이터를 가져오는 역할을 합니다.
 * API 호출 및 데이터 파싱을 수행하며, 결과를 DTO 리스트로 반환합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DisabledCourseApiClient {

    private final RestTemplate restTemplate;

    /**
     * 장애인 강좌 데이터를 API로부터 가져옵니다.
     *
     * @param serviceKey API 인증키
     * @param pageNo     요청 페이지 번호
     * @param numOfRows  한 페이지에 가져올 데이터 개수
     * @return 장애인 강좌 데이터가 담긴 `DisabledCourseDto` 리스트
     */
    public List<DisabledCourseDto> fetchDisabledCourses(String serviceKey, int pageNo, int numOfRows) {
        try {
            URI uri = makeUri(serviceKey, pageNo, numOfRows);
            log.info("API 호출 URI: {}", uri);

            String jsonString = restTemplate.getForObject(uri, String.class);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

            // "response" 객체 가져오기
            JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
            if (jsonResponse == null) {
                log.error("JSON에 'response' 키가 없습니다.");
                return new ArrayList<>();
            }

            // "body" 객체 가져오기
            JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
            if (jsonBody == null) {
                log.error("JSON에 'body' 키가 없습니다.");
                return new ArrayList<>();
            }

            // "items" 객체 가져오기
            JSONObject jsonItems = (JSONObject) jsonBody.get("items");
            if (jsonItems == null) {
                log.error("JSON에 'items' 키가 없습니다.");
                return new ArrayList<>();
            }

            // "item" 배열 가져오기
            JSONArray jsonItemList = (JSONArray) jsonItems.get("item");
            if (jsonItemList == null) {
                log.error("JSON에 'item' 키가 없습니다.");
                return new ArrayList<>();
            }

            // DTO 리스트로 변환
            List<DisabledCourseDto> result = new ArrayList<>();
            for (Object o : jsonItemList) {
                JSONObject item = (JSONObject) o;
                DisabledCourseDto dto = mapToDisabledCourseDto(item);
                if (dto != null) { // null 객체 제외
                    result.add(dto);
                }
            }
            return result;

        } catch (ParseException e) {
            log.error("JSON 파싱 실패: {}", e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            log.error("API 호출 실패: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * API 호출을 위한 URI를 생성합니다.
     *
     * @param serviceKey API 인증키
     * @param pageNo     요청 페이지 번호
     * @param numOfRows  한 페이지 결과 개수
     * @return 생성된 URI 객체
     */
    private URI makeUri(String serviceKey, int pageNo, int numOfRows) {
        return UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551014/SRVC_DVOUCHER_FACI_COURSE/TODZ_DVOUCHER_FACI_COURSE")
                .queryParam("serviceKey", serviceKey)  // 인증키
                .queryParam("pageNo", pageNo)          // 페이지 번호
                .queryParam("numOfRows", numOfRows)    // 한 페이지 결과 수
                .queryParam("resultType", "json")      // 결과 형식
                .build(true)
                .toUri();// 자동 인코딩
    }

    /**
     * JSON 객체를 `DisabledCourseDto`로 매핑합니다.
     *
     * @param item JSON 객체
     * @return 변환된 `DisabledCourseDto` 객체
     */
    private DisabledCourseDto mapToDisabledCourseDto(JSONObject item) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


        String rawStartTime = (String) item.get("start_time");
        String rawEndTime = (String) item.get("end_time");

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

        return DisabledCourseDto.builder()
                .busiRegNo((String) item.get("busi_reg_no"))
                .sportNm((String) item.get("cntnt_fst"))
                .courseNm((String) item.get("course_nm"))
                .startTime(startTime != null ? LocalTime.parse(startTime.toString()) : null) // String으로 변환 저장
                .endTime(endTime != null ? LocalTime.parse(endTime.toString()) : null)       // String으로 변환 저장
                .weekday((String) item.get("weekday"))
                .courseSetaDesc((String) item.get("course_seta_desc"))
                .settlAmt((String) item.get("settl_amt"))
                .build();
    }
}
