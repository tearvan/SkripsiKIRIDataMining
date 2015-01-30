package DataMiningLogHistoriKIRI;


import java.util.ArrayList;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jovan Gunawan
 */
public class Controller
{
    public static void main (String [] args)
    {
        /**
        CSVReader reader = new CSVReader();
        String[] file = new String[]{
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-01.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-02.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-03.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-04.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-05.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-06.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-07.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-08.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-09.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-10.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-11.csv",
        "D:\\kiri.travel\\temp\\statistic\\statistics-2014-12.csv"
        };
        //ArrayList data = reader.readCSV("D:\\kiri.travel\\temp\\statistic\\statistics-2014-01.csv");
        ArrayList data = reader.readCSV(file);

        ArrayList addApiKey = new ArrayList<String[]>();
        ArrayList findRoute = new ArrayList<String[]>();
        ArrayList login = new ArrayList<String[]>();
        ArrayList nearbyTransport = new ArrayList<String[]>();
        ArrayList pageLoad = new ArrayList<String[]>();
        ArrayList register = new ArrayList<String[]>();
        ArrayList searchPlace = new ArrayList<String[]>();
        ArrayList widgetError = new ArrayList<String[]>();
        ArrayList widgetLoad = new ArrayList<String[]>();
        ProcessingData process = new ProcessingData();

        process.processSorting(addApiKey, findRoute, login, nearbyTransport, pageLoad, register, searchPlace, widgetError, widgetLoad, data);

        System.out.println("Banyak data findRoute: " + findRoute.size());
        System.out.println("\nProcess count findRoute\n");
        * **/
        
        JFrame jf = new JFrame();
        View v = new View();
        
        jf.setVisible(true);
        jf.setSize(620, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jf.add(v);
    }
}
