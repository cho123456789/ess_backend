package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.dto.EssMetricDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface  EssMetricMapper {
    void insertMetric(EssMetricDto dto);
    List<EssMetricDto> selectRecentMetrics(@Param("essId") String essId, @Param("limit") int limit);
}
