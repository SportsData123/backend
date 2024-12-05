package com.sports.backend.disabledfacility.service;

import com.sports.backend.common.BaseApiClient;
import com.sports.backend.disabledfacility.domain.DisabledFacility;
import com.sports.backend.disabledfacility.dto.DisabledFacilityDto;
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

/**
 * `DisabledFacilityApiClient`는 외부 API를 호출하여 장애인 시설 데이터를 가져오는 역할을 담당합니다.
 * API 응답을 파싱하여 DTO 리스트로 변환합니다.
 */
@Service
@Slf4j
public class DisabledFacilityApiClient extends BaseApiClient<DisabledFacilityDto> {

    public DisabledFacilityApiClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /**
     * API 호출에 필요한 URI를 생성합니다.
     *
     * @param serviceKey API 인증키
     * @param pageNo 페이지 번호
     * @param numOfRows 한 페이지에 포함될 데이터 수
     * @return 생성된 URI
     */
    @Override
    protected URI makeUri(String serviceKey, int pageNo, int numOfRows) {
        return UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/B551014/SRVC_OD_API_FACIL_MNG_DVOUCHER/TODZ_API_MNG_DVOUCHER_I")
                .queryParam("serviceKey", serviceKey)  // 인증키
                .queryParam("pageNo", pageNo)          // 페이지 번호
                .queryParam("numOfRows", numOfRows)    // 한 페이지 결과 수
                .queryParam("resultType", "json")      // 결과 형식
                .build(true)
                .toUri();// 자동 인코딩
    }

    /**
     * JSON 객체를 `DisabledFacilityDto`로 변환합니다.
     *
     * @param item JSON 객체
     * @return 변환된 `DisabledFacilityDto` 객체
     */
    @Override
    protected DisabledFacilityDto mapToDto(JSONObject item) {
        return DisabledFacilityDto.builder()
                .facilName((String) item.get("facil_nm"))
                .resTelno((String) item.get("res_telno"))
                .mainEventName((String) item.get("main_event_nm"))
                .cityCode((String) item.get("city_cd"))
                .cityName((String) item.get("city_nm"))
                .districtCode((String) item.get("dist_cd"))
                .districtName((String) item.get("local_nm"))
                .roadAddr((String) item.get("road_addr"))
                .faciDaddr((String) item.get("faci_daddr"))
                .build();
    }
}
