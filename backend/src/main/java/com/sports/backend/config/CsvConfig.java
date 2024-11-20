package com.sports.backend.config;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvConfig {
    public static <T> List<T> parseCsv(String filePath, Class<T> clazz) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String firstLine = reader.readLine();
            if (firstLine != null && firstLine.startsWith("\uFEFF")) {
                firstLine = firstLine.substring(1);
            }
            return new CsvToBeanBuilder<T>(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("CSV 파일 파싱 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
