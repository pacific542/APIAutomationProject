package Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TimeStamp {


	
	    public static String getTimeStamp() {
	        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    }}
	


