package org.example;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Service.SunriseSunsetService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SunriseSunsetScheduler {

    private final SunriseSunsetService service;


    /**
     * 매일 새벽 1시에 실행 (Cron 표현식: 초 분 시 일 월 요일)
     * "0 0 1 * * *" = 매일 01시 00분 00초
     */
    @Scheduled(fixedDelay = 5000) // 👈 5000ms = 5초마다 계속 실행해라!
    public void collectDailySunriseSunset() {
        log.info("=== 일출/일몰 데이터 자동 수집 배치 시작 ===");

        // 1. 수집할 날짜 지정 (오늘 날짜 형식: yyyyMMdd)
        String todayStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 필요에 따라 '내일' 데이터를 미리 가져오고 싶다면 아래 주석을 해제하세요.
        // String tomorrowStr = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 2. 수집 대상 주요 도시 리스트
        List<String> targetLocations = Arrays.asList("서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종");

        // 3. 루프를 돌며 API 호출 및 DB 저장
        for (String location : targetLocations) {
            try {
                log.info("[배치 진행] 날짜: {}, 지역: {} 데이터 수집 중...", todayStr, location);
                service.fetchAndSaveSunriseSunset(todayStr, location);

                // 공공데이터 API 서버의 과부하 및 트래픽 제한(DDoS 오인)을 방지하기 위해 1초씩 쉬어줍니다.
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("[배치 오류] 인터럽트 발생: {}", e.getMessage());
            } catch (Exception e) {
                log.error("[배치 오류] 지역 [{}] 수집 실패: {}", location, e.getMessage());
            }
        }

        log.info("=== 일출/일몰 데이터 자동 수집 배치 종료 ===");
    }
}
