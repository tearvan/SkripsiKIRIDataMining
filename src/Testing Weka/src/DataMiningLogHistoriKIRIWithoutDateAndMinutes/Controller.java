package DataMiningLogHistoriKIRIWithoutDateAndMinutes;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import weka.core.Instances;

/**
 *
 * @author Jovan Gunawan
 */
public class Controller
{
    public Controller()
    {
    }
    
    public void startMining(String inputFilePath, String miningAlgo, JLabel label, JTextArea textArea) throws IOException
    {
        CSVReader csv = new CSVReader();
        ArrayList<String[]> data = csv.readCSV(inputFilePath);
        //ArrayList<String[]> data = csv.readCSV(file);
        
        ArrayList<String[]> findRoute = new ArrayList<String[]>();
        ProcessingData pd = new ProcessingData();
        pd.processSorting(findRoute, data, "FINDROUTE");
        
        //int maxMin digunakan untuk menyimpan nilai max dan min dari variable bulan dan tahun. Untuk ketentuan posisi array dapat dilihat di method preprocessing data
        int[] maxMin = new int[4];
        ArrayList<int[]> dataAfterPreprocessing = pd.preprocessingData(findRoute, maxMin);
        
        ArffIO io = new ArffIO();
        io.writeArrf("tempArff", dataAfterPreprocessing);
        
        Instances arff = io.readArff("temp.arff");
        //arff.setClassIndex(arff.numAttributes() - 1);
        DecisionTree dt = new DecisionTree();
        String [] tempTreeDataResult;
        System.out.println(miningAlgo);
        if(miningAlgo.equals("id3"))
        {
             textArea.setText(dt.id3(arff));
        }
        else
        {
             textArea.setText(dt.j48(arff));
        }
        tempTreeDataResult = textArea.getText().split("\n");
        textArea.setText(textArea.getText() + "\nNilai Confident: " + dt.calculatePrecision(arff) + "\n");
        String[] treeDataResult;
        System.out.println(tempTreeDataResult.length);
        if(tempTreeDataResult.length < 8)
        {
            if(miningAlgo.equals("id3"))
            {
                treeDataResult = new String[tempTreeDataResult.length-2];
            }
            else
            {
                treeDataResult = new String[tempTreeDataResult.length-6];
            }
            System.arraycopy(tempTreeDataResult, 2, treeDataResult, 0, treeDataResult.length);
        }
        else
        {
            if(miningAlgo.equals("id3"))
            {
                treeDataResult = new String[tempTreeDataResult.length-3];
            }
            else
            {
                treeDataResult = new String[tempTreeDataResult.length-7];
            }
            System.arraycopy(tempTreeDataResult, 3, treeDataResult, 0, treeDataResult.length);
        }
        System.out.println(treeDataResult[0]);
        SDForConvertTree dataTree = new SDForConvertTree(treeDataResult);
        //System.out.println("TEST: " + dataTree.getData(0).length());
        
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tree.txt")));
            SDForExtractData extract = new SDForExtractData(new String[]{"bulan", "tahun", "hari", "jam"},new int[]{maxMin[0],maxMin[1],7,24}, new int[]{maxMin[2],maxMin[3],1,0});
            out.println("digraph{" + DotConverter.convert(dataTree, extract, miningAlgo, 0, "") + "}");
            out.close();
            
            textArea.setText(textArea.getText());
            ArrayList<String> extractData = extract.getList();
            
            if(extractData.size() > 0)
            {
                textArea.setText(textArea.getText() + "\nExtract Data\n");
            }
            for(int i = 0; i < extractData.size(); i++)
            {
                textArea.setText(textArea.getText() + "\n" + extractData.get(i));
            }
            
        } catch (IOException ex) {
            System.out.println("Error ketika menulis file txt");
        }
        
        Cmd.makeJpgUsingDotCommand();
        
        JFrame jf2 = new JFrame();
        
        jf2.setVisible(true);
        jf2.setSize(620, 500);
        BufferedImage image = null;
        image = ImageIO.read(new File("tree.jpg"));
        ImageIcon image2 = new ImageIcon(image);
        JLabel labels = new JLabel(image2);
        JScrollPane pane = new JScrollPane(labels);
        jf2.setContentPane(pane);
    }
    
    
    public static void main (String [] args) 
    {
        Controller cont = new Controller();
        
        JFrame jf = new JFrame();
        View v = new View(cont);
        
        jf.setVisible(true);
        jf.setSize(620, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jf.add(v);
    }
}
