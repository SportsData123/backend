package com.sports.backend.facility.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sports.backend.facility.dto.FacilityDto;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacilityApiClient {

    private final RestTemplate restTemplate;

    public List<FacilityDto> fetchFacilities(String serviceKey, int pageNo, int numOfRows) {
        try {
            // URI를 사용하여 URL 생성
            URI uri = makeUri(serviceKey, pageNo, numOfRows);
            log.info("API 호출 URI: {}", uri);

            // JSON 문자열로 응답 받기
            String jsonString = restTemplate.getForObject(uri, String.class);

            // 응답 로그 출력
            // log.info("API 응답 데이터: {}", jsonString);

            // JSON 파싱
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
            List<FacilityDto> result = new ArrayList<>();
            for (Object o : jsonItemList) {
                JSONObject item = (JSONObject) o;
                FacilityDto dto = mapToFacilityDto(item);
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

    // URI 생성
    private URI makeUri(String serviceKey, int pageNo, int numOfRows) {
        return UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551014/SRVC_API_SFMS_FACI/TODZ_API_SFMS_FACI")
                .queryParam("serviceKey", serviceKey)  // 인증키
                .queryParam("pageNo", pageNo)          // 페이지 번호
                .queryParam("numOfRows", numOfRows)    // 한 페이지 결과 수
                .queryParam("resultType", "json")      // 결과 형식
                .build(true)                           // 자동 인코딩
                .toUri();                              // URI로 변환
    }

    // JSON 객체를 FacilityDto로 매핑
    private FacilityDto mapToFacilityDto(JSONObject item) {
        if (item.get("faci_nm") == null) { // 시설 이름이 없는 데이터 건너뛰기
            log.warn("시설 이름이 없는 데이터가 감지되었습니다: {}", item);
            return null;
        }

        String facilityStatus = (String) item.get("faci_stat_nm");
        if ("폐지".equals(facilityStatus)) { // 존재여부가 '폐지'인 데이터 건너뛰기
            log.warn("존재여부가 '폐지'인 데이터가 감지되었습니다: {}", item);
            return null;
        }

        return FacilityDto.builder()
                .facilityName((String) item.get("faci_nm"))
                .facilityType((String) item.get("ftype_nm"))
                .facilityStatus((String) item.get("faci_stat_nm"))
                .roadAddress((String) item.get("faci_road_addr"))
                .detailAddress((String) item.get("faci_road_daddr"))
                .zipCode((String) item.get("faci_road_zip"))
                .longitude(parseDouble(item.get("faci_lot")))
                .latitude(parseDouble(item.get("faci_lat")))
                .inOutType((String) item.get("inout_gbn_nm"))
                .nationFlag((String) item.get("nation_yn"))
                .cityName((String) item.get("cp_nm"))
                .districtName((String) item.get("cpb_nm"))
                .build();
    }

    // 문자열 값을 Double로 변환
    private Double parseDouble(Object value) {
        try {
            return value != null ? Double.parseDouble(value.toString()) : null;
        } catch (NumberFormatException e) {
            log.warn("좌표 값을 Double로 변환할 수 없습니다: {}", value);
            return null;
        }
    }
}