package com.sly.demo.service;
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
  
public class CalendarTest {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
          
		String time = formatDateTime("2017-01-13 15:41");
		System.out.println("time:"+time);
		time = formatDateTime("2017-01-12 15:45");
		System.out.println("time:"+time);
		time = formatDateTime("2017-01-11 15:43");
		System.out.println("time:"+time); 
    }  
      
    /** 
     * 格式化时间 
     * @param time 
     * @return 
     */  
    private static String formatDateTime(String time) {  
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");   
        if(time==null ||"".equals(time)){  
            return "";  
        }  
        Date date = null;  
        try {  
            date = format.parse(time);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
          
        Calendar current = Calendar.getInstance();  
          
        Calendar today = Calendar.getInstance();    //今天  
          
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));  
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));  
        today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));  
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数  
        today.set( Calendar.HOUR_OF_DAY, 0);  
        today.set( Calendar.MINUTE, 0);  
        today.set(Calendar.SECOND, 0);  
          
        Calendar yesterday = Calendar.getInstance();    //昨天  
          
        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));  
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));  
        yesterday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);  
        yesterday.set( Calendar.HOUR_OF_DAY, 0);  
        yesterday.set( Calendar.MINUTE, 0);  
        yesterday.set(Calendar.SECOND, 0);  
          
        current.setTime(date);  
          
        if(current.after(today)){  
            return "今天 "+time.split(" ")[1];  
        }else if(current.before(today) && current.after(yesterday)){  
              
            return "昨天 "+time.split(" ")[1];  
        }else{  
            int index = time.indexOf("-")+1;  
            return time.substring(index, time.length());  
        }  
    }  
  
}  