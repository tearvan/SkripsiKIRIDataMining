package DataMiningLogHistoriKIRI;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.explorer.PreprocessPanel;

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
    public static void main (String [] args) throws IOException
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


        JFrame jf = new JFrame();
        View v = new View();
        
        jf.setVisible(true);
        jf.setSize(620, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jf.add(v);
        * */
        
        CSVReader csv = new CSVReader();
        ArrayList<String[]> data = csv.readCSV("D:\\Tugas\\Skripsi-1\\Skripsi GIT\\SkripsiKIRIDataMining\\src\\Testing Weka\\KIRIStatistics-2014-02.csv");
        
        ArrayList<String[]> findRoute = new ArrayList<String[]>();
        ProcessingData pd = new ProcessingData();
        pd.processSorting(findRoute, data, "FINDROUTE");
        
        ArrayList<int[]> dataAfterPreprocessing = pd.preprocessingData(findRoute);
        
        ArffIO io = new ArffIO();
        io.writeArrf("tempArff", dataAfterPreprocessing);
        
        Instances arff = io.readArff("D:\\Tugas\\Skripsi-1\\Skripsi GIT\\SkripsiKIRIDataMining\\src\\test data\\temp.arff");
        //arff.setClassIndex(arff.numAttributes() - 1);
        
        String[] option = new String[]{"-U"};
        J48 tree = new J48();
        
        try {
            tree.setOptions(option);
            
            tree.buildClassifier(arff);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(tree.toString());
    }
}
