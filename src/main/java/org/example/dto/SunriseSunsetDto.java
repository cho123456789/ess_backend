package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SunriseSunsetDto {
    private String targetDate;
    private String location;
    private String sunrise;
    private String sunset;
    private String latitude;
    private String longitude;

}
