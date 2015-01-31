
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jovan Gunawan
 */
public class TestingCalendar
{
    public static void main (String [] args)
    {        
        //String string_date = "12-12-2012 13:10:32";
        String string_date = "1/1/2014  3:51:15 AM";

        Date d;
        long milliseconds = 0;
        try {
            //SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            d = (Date)f.parse(string_date);
            milliseconds = d.getTime();
            System.out.println(milliseconds + " ");
        } catch (ParseException ex) {
            Logger.getLogger(TestingCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Date currentTime = new Date(milliseconds);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        // harus set timezone komputer ke UTC
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        System.out.println("GMT time: " + sdf.format(currentTime));
    }
}
