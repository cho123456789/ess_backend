package org.example.Service;

import lombok.RequiredArgsConstructor;
import org.example.Entity.SunriseSunset;
import org.example.dto.SunRiseSetResponse;
import org.example.repository.SunriseSunsetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder; // 🔴 추가됨
import java.nio.charset.StandardCharsets; // 🔴 추가됨
import java.util.List;

@Service
@RequiredArgsConstructor
public class SunriseSunsetService {

    private final SunriseSunsetRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();


    private String serviceKey = "%2F99p4bK0LcTzFgaq97mbNe%2FoAWZex%2B7WDLf35%2FXJrIQElNWq%2BWR%2BVghbe8FxySwatEcu4EVAFq8Tb8Yz%2FUK6Pw%3D%3D";

    private final String API_URL = "http://apis.data.go.kr/B090041/openapi/service/RiseSetInfoService/getAreaRiseSetInfo";

    @Transactional
    public void fetchAndSaveSunriseSunset(String locdate, String location) {
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
            String urlWithParams = API_URL + "?serviceKey=" + serviceKey
                    + "&locdate=" + locdate
                    + "&location=" + encodedLocation;

            // 문자열을 순수한 URI 객체로 변환하여 RestTemplate에 넘깁니다.
            URI uri = new URI(urlWithParams);

            // API 호출 및 XML -> DTO 파싱
            SunRiseSetResponse response = restTemplate.getForObject(uri, SunRiseSetResponse.class);

            if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
                List<SunRiseSetResponse.SunItem> items = response.getBody().getItems().getItemList();

                if (items != null && !items.isEmpty()) {
                    SunRiseSetResponse.SunItem item = items.get(0);

                    // 중복 저장 방지 확인
                    if (repository.findByTargetDateAndLocation(locdate, location).isEmpty()) {
                        SunriseSunset entity = new SunriseSunset(
                                item.getLocdate().trim(),
                                item.getLocation().trim(),
                                item.getSunrise().trim(),
                                item.getSunset().trim(),
                                item.getLatitude().trim(),
                                item.getLongitude().trim()
                        );
                        repository.save(entity);
                        System.out.println("✅ 성공적으로 저장되었습니다: " + location + " - " + locdate);
                    }
                } else {
                    System.out.println("⚠️ 검색 결과 데이터가 존재하지 않습니다: " + location);
                }
            }
        } catch (Exception e) {
            // 콘솔에 에러 원인을 더 자세히 보기 위해 출력
            System.err.println("❌ [" + location + "] API 호출 실패 원인: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}