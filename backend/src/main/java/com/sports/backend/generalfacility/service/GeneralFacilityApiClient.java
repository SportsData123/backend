package com.sports.backend.generalfacility.service;

import com.sports.backend.common.BaseApiClient;
import com.sports.backend.generalfacility.dto.GeneralFacilityDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * `GeneralFacilityApiClient`는 외부 API를 호출하여 일반 시설 데이터를 가져오는 역할을 합니다.
 * API 호출 및 데이터를 `GeneralFacilityDto`로 매핑하는 로직을 제공합니다.
 */
@Service
@Slf4j
public class GeneralFacilityApiClient extends BaseApiClient<GeneralFacilityDto> {

    /**
     * RestTemplate을 주입받아 BaseApiClient를 초기화합니다.
     *
     * @param restTemplate RestTemplate 객체
     */
    public GeneralFacilityApiClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /**
     * API 호출을 위한 URI를 생성합니다.
     *
     * @param serviceKey API 인증 키
     * @param pageNo     호출할 페이지 번호
     * @param numOfRows  한 페이지에 가져올 데이터 수
     * @return 생성된 URI 객체
     */
    @Override
    protected URI makeUri(String serviceKey, int pageNo, int numOfRows) {
        return UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/B551014/SRVC_OD_API_FACIL_MNG/todz_api_facil_mng_i")
                .queryParam("serviceKey", serviceKey)  // 인증키
                .queryParam("pageNo", pageNo)          // 페이지 번호
                .queryParam("numOfRows", numOfRows)    // 한 페이지 결과 수
                .queryParam("resultType", "json")      // 결과 형식
                .build(true)
                .toUri();// 자동 인코딩
    }

    /**
     * API 응답 데이터를 `GeneralFacilityDto` 객체로 변환합니다.
     *
     * @param item API 응답 JSON 데이터의 개별 객체
     * @return 변환된 `GeneralFacilityDto` 객체
     */
    @Override
    protected GeneralFacilityDto mapToDto(JSONObject item) {
        return GeneralFacilityDto.builder()
                .facilName((String) item.get("facil_nm"))        // 시설 이름
                .resTelno((String) item.get("res_telno"))        // 시설 전화번호
                .mainEventName((String) item.get("main_event_nm")) // 주요 이벤트 이름
                .cityCode((String) item.get("city_cd"))          // 도시 코드
                .cityName((String) item.get("city_nm"))          // 도시 이름
                .districtCode((String) item.get("dist_cd"))      // 구 코드
                .districtName((String) item.get("local_nm"))     // 구 이름
                .brno((String) item.get("brno"))                 // 사업자 등록번호
                .facilSn((String) item.get("facil_sn"))          // 시설 고유번호
                .roadAddr((String) item.get("road_addr"))        // 도로명 주소
                .faciDaddr((String) item.get("faci_daddr"))      // 상세 주소
                .build();
    }
}
