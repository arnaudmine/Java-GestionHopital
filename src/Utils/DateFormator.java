package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormator {

    public static String  fromCalendarToString (GregorianCalendar calendar){
        Date date = calendar.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormated = fmt.format(date.getTime());
        return dateFormated;
    }
}
