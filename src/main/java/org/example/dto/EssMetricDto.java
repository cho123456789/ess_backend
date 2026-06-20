package org.example.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class EssMetricDto {
    private Long id;
    private String essId;
    private Integer rackId;
    private Double soc;
    private Double powerKw;
    private Double temperature;
    private LocalDateTime createdAt; // 💡 자바 8 이상 표준 날짜 컴포넌트
}