package org.example.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EssSchedulerService {

    private final EssMetricService essMetricService;

    @Scheduled(fixedRate = 5000)
    public void collectMetrics() {
        String essId = "ESS_DAEGU_01";

        try {
            // 비즈니스 서비스 호출 (트랜잭션 안에서 실행됨)
            essMetricService.collectAndSaveMetrics(essId);
        } catch (Exception e) {
            log.error("[스케줄러 오류] {} 데이터 수집 중 에러 발생", essId, e);
        }
    }
}
