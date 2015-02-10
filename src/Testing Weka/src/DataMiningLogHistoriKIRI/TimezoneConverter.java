/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMiningLogHistoriKIRI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovan Gunawan
 */
public class TimezoneConverter 
{
    // Mengubah input waktu menjadi waktu GMT+7
    // Jika ingin mengubah dari UTC ke GMT+7 maka komputer harus set timezone ke UTC
    // input parameter string harus dalam format dd-MM-yyyy HH:mm:ss --> contoh 1/1/2014  3:51:15 AM
    // output akan mengubah waktu menjadi GMT+8 dalam String dengan format EEE MM/dd/yyyy HH:mm:ss --> contoh Wed 01/01/2014 03:51:15
    public static String convertToGMT7(String date)
    {
        Date d;
        long milliseconds = 0;
        
        try {
            SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            d = (Date)f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(TimezoneConverter.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        Date currentTime = new Date(milliseconds);
        // untuk hari --> 1 = senin,..., 7 = minggu
        SimpleDateFormat sdf = new SimpleDateFormat("u MM/dd/yyyy HH:mm:ss");
        
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return sdf.format(currentTime);
    }
}
