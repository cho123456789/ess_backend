package org.example.Service;

import org.example.dto.SunriseSunsetDto;

public interface SunriseSunsetServiceApi {
    SunriseSunsetDto getSunriseSunsetData(String date, String location);
}
