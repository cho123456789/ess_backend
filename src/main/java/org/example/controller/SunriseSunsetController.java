package org.example.controller;


import org.example.Service.SunriseSunsetServiceApi;
import org.example.dto.SunriseSunsetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sunrise-sunset")
public class SunriseSunsetController {
    private final SunriseSunsetServiceApi sunriseSunsetServiceApi;

    public SunriseSunsetController(SunriseSunsetServiceApi sunriseSunsetServiceApi) {
        this.sunriseSunsetServiceApi = sunriseSunsetServiceApi;
    }

    @GetMapping
    public ResponseEntity<SunriseSunsetDto> getSunriseSunset(
            @RequestParam("date") String date,
            @RequestParam("location") String location) {

        // 서비스 레이어에서 DB 조회 후 DTO로 변환하여 반환하는 로직
        SunriseSunsetDto result = sunriseSunsetServiceApi.getSunriseSunsetData(date, location);
        return ResponseEntity.ok(result);
    }
}