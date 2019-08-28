/**
 *
 */
package io.project.ppmtool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import io.project.ppmtool.exceptions.CustomException;

/**
 * @author Bernard A. Santos Jr.  9 Aug 2019
 */
@Service
public class DateTimeUtil {

    private String pattern = "yyyy-MM-dd HH:mm:ss";
    private String ymd = "yyyy-MM-dd";
    private String timePattern = "HH:mm:ss";
    private SimpleDateFormat ymdFormat = new SimpleDateFormat(ymd);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private SimpleDateFormat timeOnly = new SimpleDateFormat(timePattern);

    public String dateToStringDateOnly (Date date) {
        return ymdFormat.format(date);
    }
    public String dateToString (Date date) {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }
    public int toMonth (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }
    public int toYear (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    public Date toDate(String str) {
        Date date = null;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        //for optional api to return null
        if (str == null)
            return null;
        try {
            date = simpleDateFormat.parse(str);
        }catch (ParseException e) {
            throw new CustomException("error in parsing date",10);
        }
        return date;
    }
    public Date toTime(String str) {
        Date date = null;
        try {
            date = timeOnly.parse(str);
        }catch (ParseException e) {
            throw new CustomException("error in parsing date",10);
        }
        return date;
    } 
    //return int represent day of the week
    public int dayOfTheWeek(String str) {
        Date date = this.toDate(str);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    public int toHourUtc(String str) {
        Date date = this.toDate(str);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    public int toHour(String str) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        }catch (ParseException e) {
            throw new CustomException("error in parsing date",10);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    public Date addUnit(Date date,int unit) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.HOUR_OF_DAY, unit); 
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal.getTime();
    }
    
    public Date addMinute(Date date,int unit) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.MINUTE, unit); 
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal.getTime();
    }
    public Date minusUnit(Date date, int unit) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.HOUR_OF_DAY, -unit);
        return cal.getTime();
    }
    public Date minusDay(Date date, int unit) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.DATE, -unit);
        return cal.getTime();
    }
    public Date addDay(Date date, int unit) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.DATE, unit);
        return cal.getTime();
    }
    public Date toDateOnly (String str) {
        Date date = null;
        if (str == null)
            return null;
        try {
            date = ymdFormat.parse(str);
        }catch (ParseException e) {
            throw new CustomException("error in parsing date",10);
        }
        return date;
    }
    public Date endOfDay(Date date) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime(); 
    }
    
    //get start time of the day
    public Date startOfDay(Date date) {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 01);
        return cal.getTime(); 
    }
}    