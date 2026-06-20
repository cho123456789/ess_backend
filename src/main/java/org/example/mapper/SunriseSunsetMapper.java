package org.example.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.dto.SunriseSunsetDto;

@Mapper
public interface SunriseSunsetMapper {
    SunriseSunsetDto selectSunriseSunset(
            @Param("date") String date,
            @Param("location") String location
    );
}
