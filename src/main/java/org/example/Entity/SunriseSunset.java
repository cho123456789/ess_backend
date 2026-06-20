package org.example.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class SunriseSunset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String targetDate;  // 조회 날짜 (예: 20260609)
    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String location;    // 조회 지역 (예: 서울)
    private String sunrise;     // 일출 시각 (예: 0510)
    private String sunset;      // 일몰 시각 (예: 1950)
    private String latitude;    // 위도
    private String longitude;   // 경도

    public SunriseSunset(String targetDate, String location, String sunrise, String sunset, String latitude, String longitude) {

        this.targetDate = targetDate;
        this.location = location;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
