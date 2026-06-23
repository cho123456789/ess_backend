package org.example.Service.impl;

import org.example.Service.SunriseSunsetServiceApi;
import org.example.dto.SunriseSunsetDto;
import org.example.mapper.SunriseSunsetMapper;
import org.springframework.stereotype.Service;

@Service
public class SunriseSunsetServiceApiImpl implements SunriseSunsetServiceApi {
    private final SunriseSunsetMapper sunriseSunsetMapper;

    public SunriseSunsetServiceApiImpl(SunriseSunsetMapper sunriseSunsetMapper) {
        this.sunriseSunsetMapper = sunriseSunsetMapper;
    }

    public SunriseSunsetDto getSunriseSunsetData(String date, String location) {

        SunriseSunsetDto result = sunriseSunsetMapper.selectSunriseSunset(date, location);

        if (result == null) {
            throw new IllegalArgumentException("해당 날짜와 지역의 데이터가 아직 수집되지 않았습니다.");
        }

        System.out.println("result = " + result.getSunset());

        return result;
    }
}