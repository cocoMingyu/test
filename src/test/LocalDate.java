package test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/2/26 20:49
 * @ Modified By:
 */
public class LocalDate {
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    public void test2(){
        /**
         * 时间戳
         */
        java.time.LocalDate of = java.time.LocalDate.of(2018, 1, 1);
        java.time.LocalDate now1 = java.time.LocalDate.now();
        Instant now = Instant.now();
        Instant end = Instant.now();
        long l = Duration.between(now, end).toNanos();
        int days = Period.between(now1, of).getDays();
        System.out.println(days);
    }

    public void test3(){
    }
    public static void main(String[] args) {
        LocalDate date = new LocalDate();
        date.test2();
    }
}
