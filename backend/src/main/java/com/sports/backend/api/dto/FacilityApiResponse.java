package com.sports.backend.api.dto;

import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facilityBusiness.domain.FacilityBusiness;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FacilityApiResponse {
    private Response response;

    @Data
    public static class Response {
        private Body body;

        @Data
        public static class Body {
            private List<FacilityItem> items;

            @Data
            public static class FacilityItem {
                private String faciNm;
                private String ftypeNm;
                private String faciStatNm;
                private String faciRoadAddr;
                private String faciRoadDaddr;
                private String faciRoadZip;
                private double faciLot;
                private double faciLat;
                private String inoutGbnNm;
                private String nationYn;

                public Facility toEntity() {
                    FacilityBusiness facilityBusiness = FacilityBusiness.builder()
                            .facilityName(faciNm)
                            .build();

                    return Facility.builder()
                            .facilityName(facilityBusiness)
                            .facilityType(ftypeNm)
                            .facilityStatus(faciStatNm)
                            .roadAddress(faciRoadAddr)
                            .detailAddress(faciRoadDaddr)
                            .zipCode(faciRoadZip)
                            .longitude(faciLot)
                            .latitude(faciLat)
                            .inOutType(inoutGbnNm)
                            .nationFlag(nationYn)
                            .build();
                }
            }
        }
    }

    public List<Facility> toFacilityEntities() {
        return response.getBody().getItems().stream()
                .map(Response.Body.FacilityItem::toEntity)
                .collect(Collectors.toList());
    }
}