package org.example.Service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Service.EssMetricService;
import org.example.dto.EssMetricDto;
import org.example.mapper.EssMetricMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EssMetricsServiceImpl implements EssMetricService {

    private final EssMetricMapper essMetricMapper; // 👈 매퍼 주입
    private final Random random = new Random();

    @Transactional
    @Override
    public void collectAndSaveMetrics(String essId) {
        EssMetricDto dto = new EssMetricDto();
        dto.setEssId(essId);
        dto.setRackId(1);
        dto.setSoc(Math.round((70.0 + random.nextDouble() * 10) * 100.0) / 100.0);
        dto.setPowerKw(Math.round((120.0 + random.nextDouble() * 30) * 100.0) / 100.0);
        dto.setTemperature(Math.round((24.0 + random.nextDouble() * 4) * 100.0) / 100.0);
        dto.setCreatedAt(LocalDateTime.now());

        // MyBatis 매퍼를 통해 DB insert
        essMetricMapper.insertMetric(dto);
        log.info("[MyBatis 스케줄러] {} 데이터 적재 성공 -> SoC: {}%", essId, dto.getSoc());

    }
}