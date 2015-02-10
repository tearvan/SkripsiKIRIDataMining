package DataMiningLogHistoriKIRIWithoutDateAndMinutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
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
    public Controller()
    {
    }
    
    public void startMining(String inputFilePath, String miningAlgo, JTextArea textArea) throws IOException
    {
        CSVReader csv = new CSVReader();
        ArrayList<String[]> data = csv.readCSV(inputFilePath);
        //ArrayList<String[]> data = csv.readCSV(file);
        
        ArrayList<String[]> findRoute = new ArrayList<String[]>();
        ProcessingData pd = new ProcessingData();
        pd.processSorting(findRoute, data, "FINDROUTE");
        
        ArrayList<int[]> dataAfterPreprocessing = pd.preprocessingData(findRoute);
        
        ArffIO io = new ArffIO();
        io.writeArrf("tempArff", dataAfterPreprocessing);
        
        Instances arff = io.readArff("temp.arff");
        //arff.setClassIndex(arff.numAttributes() - 1);
        
        DecisionTree dt = new DecisionTree();
        String [] tempTreeDataResult;
        if(miningAlgo.equals("id3"))
        {
             textArea.setText(dt.id3(arff));
        }
        else
        {
             textArea.setText(dt.j48(arff));
        }
        tempTreeDataResult = textArea.getText().split("\n");
        String [] treeDataResult = new String[tempTreeDataResult.length-7];
        System.arraycopy(tempTreeDataResult, 3, treeDataResult, 0, treeDataResult.length);
        
        SDForConvertTree dataTree = new SDForConvertTree(treeDataResult);
        
        System.out.println(DotConverter.convert(dataTree, 0, ""));
    }
    
    
    public static void main (String [] args) 
    {
        
        /**
        //CSVReader reader = new CSVReader();
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

        * */

        Controller cont = new Controller();
        
        JFrame jf = new JFrame();
        View v = new View(cont);
        
        jf.setVisible(true);
        jf.setSize(620, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jf.add(v);
        
        /**
        CSVReader csv = new CSVReader();
        ArrayList<String[]> data = csv.readCSV("KIRIStatistics-2014-02.csv");
        //ArrayList<String[]> data = csv.readCSV(file);
        
        ArrayList<String[]> findRoute = new ArrayList<String[]>();
        ProcessingData pd = new ProcessingData();
        pd.processSorting(findRoute, data, "FINDROUTE");
        
        ArrayList<int[]> dataAfterPreprocessing = pd.preprocessingData(findRoute);
        
        ArffIO io = new ArffIO();
        io.writeArrf("tempArff", dataAfterPreprocessing);
        
        Instances arff = io.readArff("temp.arff");
        //arff.setClassIndex(arff.numAttributes() - 1);
        
        String[] option = new String[]{"-U"};
        J48 tree = new J48();
        
        try {
            //tree.setOptions(option);
            
            tree.buildClassifier(arff);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(tree.toString());
        
        int nilaiBenar = 0, resultInt;
        float result = 0;
        for (int i = 0; i < arff.numInstances(); i++)
        {
            try {
                result = (float)tree.classifyInstance(arff.instance(i));
                resultInt = Math.round(result);
                //System.out.println(dataAfterPreprocessing.get(i)[6] + " " + arff.instance(i).stringValue(6));
                if(resultInt == Integer.parseInt(arff.instance(i).stringValue(4)))
                {
                    nilaiBenar++;
                }
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }
        }
        System.out.println("nilai: " + nilaiBenar + " " + arff.numInstances());
        double confident = nilaiBenar * 1.0 / arff.numInstances() * 100;
        System.out.println("Confident = " + confident + "%");
        
        String [] tempTreeDataResult = tree.toString().split("\n");
        String [] treeDataResult = new String[tempTreeDataResult.length-7];
        System.arraycopy(tempTreeDataResult, 3, treeDataResult, 0, treeDataResult.length);
        
        SDForConvertTree dataTree = new SDForConvertTree(treeDataResult);
        
        System.out.println(DotConverter.convert(dataTree, 0, ""));
        * 
        * **/
    }
}
