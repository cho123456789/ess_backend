package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.sql.Connection;

@EnableScheduling
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // ⭐️ 스프링 부트가 켜지자마자 DB 연결을 확인하는 테스트 코드입니다.
    @Bean
    public CommandLineRunner testConnection(DataSource dataSource) {
        return args -> {
            System.out.println("\n========================================");
            System.out.println("🔄 데이터베이스 연결 테스트 시작...");

            try (Connection connection = dataSource.getConnection()) {
                String dbName = connection.getMetaData().getDatabaseProductName();
                String dbUrl = connection.getMetaData().getURL();

                System.out.println("✅ DB 연동 성공!!!!");
                System.out.println("📊 연결된 DB 종류: " + dbName);
                System.out.println("🔗 연결된 DB 주소: " + dbUrl);
            } catch (Exception e) {
                System.out.println("❌ DB 연동 실패...");
                System.out.println("⚠️ 에러 원인: " + e.getMessage());
            }

            System.out.println("========================================\n");
        };
    }
}