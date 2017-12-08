package test_ldap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String time = formatDateTime("2017-01-17 15:45:30");
//		System.out.println("time:"+time);
//		time = formatDateTime("2017-01-16 15:45:30");
//		System.out.println("time:"+time);
//		time = formatDateTime("2017-01-11 15:43:30");
//		System.out.println("time:"+time);
		
		System.err.println(Float.parseFloat("10"));
	}
	
	/**
	 * 格式化时间
	 * @param time
	 * @return
	 */
	private static String formatDateTime(String time) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
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
		
		Calendar today = Calendar.getInstance();	//今天
		
		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
		//  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set( Calendar.HOUR_OF_DAY, 23);
		today.set( Calendar.MINUTE, 59);
		today.set(Calendar.SECOND, 59);
		
		Calendar yesterday = Calendar.getInstance();	//昨天
		
		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);
		yesterday.set( Calendar.HOUR_OF_DAY, 23);
		yesterday.set( Calendar.MINUTE, 59);
		yesterday.set(Calendar.SECOND, 59);
		
		current.setTime(date);

		if(current.after(today)){
			return "明天 "+time.split(" ")[1];
		}else if(current.before(today) && current.after(yesterday)){
			return "今天 "+time.split(" ")[1];
		}else{
			int index = time.indexOf("-")+1;
			return "昨天 "+time.substring(index, time.length());
		}
	}

}