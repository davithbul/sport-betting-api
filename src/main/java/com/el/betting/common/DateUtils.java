package com.el.betting.common;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class DateUtils {

    public final static TimeZone UTC = TimeZone.getTimeZone("UTC");
    public final static TimeZone EUROPE = TimeZone.getTimeZone("Europe/Berlin");
    public final static TimeZone AUSTRALIA = TimeZone.getTimeZone("ACT"); //australian central timezone
    public final static TimeZone ADELAIDE = TimeZone.getTimeZone("Australia/Adelaide");
    public final static TimeZone DEFAULT = TimeZone.getDefault();

    private DateUtils() {
    }

    public static boolean compareTillDay(Date date1, Date date2) {
        return trimSeconds(date1).equals(trimSeconds(date2));
    }

    public static Date trimSeconds(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static LocalDateTime trimTimeInformation(LocalDateTime localDateTime) {
        return localDateTime.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime convertToDateTime(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }

        int year = calendar.getYear() > 0 ? calendar.getYear() : 0;
        int hour = calendar.getHour() > 0 ? calendar.getHour() : 0;
        int minute = calendar.getMinute() > 0 ? calendar.getMinute() : 0;
        int second = calendar.getSecond() > 0 ? calendar.getSecond() : 0;
        int millisecond = calendar.getMillisecond() > 0 ? calendar.getMillisecond() : 0;

        return LocalDateTime.of(year, calendar.getMonth(), calendar.getDay(), hour, minute, second, millisecond);
    }

    public static LocalDateTime convertToDateTime(long timeInMilliseconds) {
        Instant instant = Instant.ofEpochMilli(timeInMilliseconds);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public static LocalDateTime convertToDateTime(long timeInMilliseconds, int nanoSeconds) {
        return LocalDateTime.ofEpochSecond(timeInMilliseconds, nanoSeconds, ZoneOffset.UTC);
    }

    public static LocalDateTime convertToDateTime(Date source) {
        return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        return Date.from(instant);
    }

    public static LocalDateTime convertUtcToDefaultZone(LocalDateTime localDateTime) {
        return convertToZone(localDateTime, TimeZone.getDefault());
    }

    public static LocalDateTime convertToZone(LocalDateTime localDateTime, TimeZone to) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(UTC.toZoneId());
        return zonedDateTime.withZoneSameInstant(to.toZoneId()).toLocalDateTime();
    }

    public static LocalDateTime convertToZone(LocalDateTime localDateTime, TimeZone from, TimeZone to) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(from.toZoneId());
        return zonedDateTime.withZoneSameInstant(to.toZoneId()).toLocalDateTime();
    }


    public static ChronoUnit convertToChronoUnit(TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit);
        switch (timeUnit) {
            case DAYS:
                return ChronoUnit.DAYS;
            case HOURS:
                return ChronoUnit.HOURS;
            case MINUTES:
                return ChronoUnit.MINUTES;
            case SECONDS:
                return ChronoUnit.SECONDS;
            case MICROSECONDS:
                return ChronoUnit.MICROS;
            case MILLISECONDS:
                return ChronoUnit.MILLIS;
            case NANOSECONDS:
                return ChronoUnit.NANOS;
            default:
                throw new IllegalArgumentException("there are no other TimeUnit ordinal values");
        }
    }

    public static String format(Temporal temporal, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        if (temporal instanceof LocalDate) {
            return ((LocalDate) temporal).format(formatter);
        } else if (temporal instanceof LocalDateTime) {
            return ((LocalDateTime) temporal).format(formatter);
        }

        throw new IllegalArgumentException("Can't handle date type: " + temporal.getClass());
    }
}
