package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Класс для хранение распарсенной строки
 */
public class Record {
    private String ip;
    private String sDate;
    private String sEvent;
    private String sNumEvent;
    private String sStaus;

   //ip username date event status
   private String username;
   private Date date;
   private Event event;
   private Integer numEvent;
   private Status status;

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public Integer getNumEvent() {
        return numEvent;
    }

    public Status getStatus() {
        return status;
    }

    public Record(String line) {
        String[] arr = line.split("\t");
        ip = arr[0];
        username = arr[1];

        sDate = arr[2];
        sEvent = arr[3];
        sStaus = arr[4];

        if (sEvent.trim().contains(" ")){
            //есть задача и номер через пробел
            event = Event.valueOf(sEvent.substring(0,sEvent.indexOf(" ")));
            sNumEvent = sEvent.substring(sEvent.indexOf(" ")+1,sEvent.length());
        }else{
            event = Event.valueOf(sEvent);
            sNumEvent = "0";
        }

        status = Status.valueOf(sStaus);
        numEvent = Integer.parseInt(sNumEvent);

        /*
        String[] arr1 = sDate.trim().split("\\.");
        String[] arr2 = sTime.trim().split(":");
        Calendar calendar = new GregorianCalendar(Integer.parseInt(sDate.trim().split("\\.")[2]),
                                                  Integer.parseInt(sDate.trim().split("\\.")[1])-1,
                                                  Integer.parseInt(sDate.trim().split("\\.")[0]),
                                                  Integer.parseInt(sTime.trim().split(":")[0]),
                                                  Integer.parseInt(sTime.trim().split(":")[1]),
                                                  Integer.parseInt(sTime.trim().split(":")[2])
                                                  );
        date = calendar.getTime();
        */
        SimpleDateFormat simpleDateFormat= new  SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            date = simpleDateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
