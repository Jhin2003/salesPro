package utils;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final ZoneId PH_ZONE = ZoneId.of("Asia/Manila");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateTimeInPhilippines() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime phNow = now.atZone(PH_ZONE).toLocalDateTime();
        return phNow.format(FORMATTER);
    }

}
