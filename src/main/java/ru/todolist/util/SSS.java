package ru.todolist.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class SSS {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().atZone(ZoneId.of("UTC+0")).withZoneSameInstant(ZoneId.of("UTC+5")).toLocalDateTime());
    }
}
