package com.example.inventory.Order.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class OrderIdGenerator {

    private static final AtomicLong sequence = new AtomicLong(0);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS");

    public static String generate() {
        long next = sequence.incrementAndGet();
        return "O-"+ LocalDateTime.now().format(FORMATTER)+String.format("%08d", next);
    }
}

