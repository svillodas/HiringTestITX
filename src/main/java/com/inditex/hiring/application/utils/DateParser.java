package com.inditex.hiring.application.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public static LocalDateTime parseStringToLocalDateTime(String dateTimeString) {

            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date time string: " + e.getMessage());
                return null;
            }
        }
        public static String parseLocalDateTimetoString(LocalDateTime datetime) {
            return datetime.format(formatter);
    }
}
