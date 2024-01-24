package com.qa.framework.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateAndTime {

    public static long find(String join_date, String leave_date) {
        long days_difference;
        // Create an instance of the SimpleDateFormat class
        SimpleDateFormat obj = new SimpleDateFormat("dd MMM yyyy");
        // In the try block, we will try to find the difference
        try {
            // Use parse method to get date object of both dates
            Date date1 = obj.parse(join_date);
            Date date2 = obj.parse(leave_date);
            // Calucalte time difference in milliseconds
            long time_difference = date2.getTime() - date1.getTime();
            // Calucalte time difference in days
             days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;
            // Calucalte time difference in years
            long years_difference = (time_difference / (1000l * 60 * 60 * 24 * 365));
            // Calucalte time difference in seconds
            long seconds_difference = (time_difference / 1000) % 60;
            // Calucalte time difference in minutes
            long minutes_difference = (time_difference / (1000 * 60)) % 60;

            // Calucalte time difference in hours
            long hours_difference = (time_difference / (1000 * 60 * 60)) % 24;
            // Show difference in years, in days, hours, minutes, and seconds


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return days_difference;
    }

    public static String getTodayDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
