package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.EssMetricDto;
import org.example.mapper.EssMetricMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ess")
@RequiredArgsConstructor
public class EssController {

    private final EssMetricMapper essMetricMapper; // 👈 매퍼 주입

    @GetMapping("/metrics/recent")
    public List<EssMetricDto> getRecentMetrics(@RequestParam("essId") String essId) {
        // 최신 30개 데이터 쿼리 수행
        List<EssMetricDto> list = essMetricMapper.selectRecentMetrics(essId, 30);

        // 안드로이드 실시간 선형 그래프 정방향 렌더링을 위해 리스트 반전(과거 -> 현재)
        Collections.reverse(list);

        return list;
    }
}