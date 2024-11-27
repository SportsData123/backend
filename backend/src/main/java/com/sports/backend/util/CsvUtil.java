package com.sports.backend.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CsvUtil {
    public static List<String[]> parseCsv(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVReader csvReader = new CSVReader(reader)) {

            csvReader.skip(1); // 헤더 스킵
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                records.add(record);
            }

        } catch (IOException | CsvValidationException e) {
            log.error("CSV 파일 읽기 중 오류 발생: {}", e.getMessage(), e);
        }
        return records;
    }
}
