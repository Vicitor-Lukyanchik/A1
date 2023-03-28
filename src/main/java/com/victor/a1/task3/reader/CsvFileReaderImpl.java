package com.victor.a1.task3.reader;

import com.victor.a1.task3.exception.FileReaderException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvFileReaderImpl implements CsvFileReader {

    private static final String SLASH = "/";

    @Override
    public List<String> readResourceFileLines(String path) {
        try (InputStream inputStream = getClass().getResourceAsStream(SLASH + path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            String message = "File does not exist: " + path;
            throw new FileReaderException(message, e);
        }
    }
}
