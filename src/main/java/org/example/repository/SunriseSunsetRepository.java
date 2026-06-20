package org.example.repository;

import org.example.Entity.SunriseSunset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SunriseSunsetRepository extends JpaRepository<SunriseSunset, Long> {
    // 이미 저장된 데이터가 있는지 중복 체크용
    Optional<SunriseSunset> findByTargetDateAndLocation(String targetDate, String location);
}
