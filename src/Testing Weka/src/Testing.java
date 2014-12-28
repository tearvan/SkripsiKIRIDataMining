/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jovan Gunawan
 */

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode1;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class Testing 
{
    public Testing()
    {
    }
    
    public static void main(String [] args) throws Exception
    {
        //Testing test = new Testing();
        String[] option = new String[]{"-U"};
        Instances data;
        J48 tree = new J48();
        try {
            tree.setOptions(option);
            data = new Instances(new BufferedReader(new FileReader("D:\\Tugas\\Skripsi-1\\Skripsi GIT\\SkripsiKIRIDataMining\\src\\test data\\test data.arff")));
            data.setClassIndex(data.numAttributes() - 1);
            tree.buildClassifier(data);
        } catch (Exception ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // display classifier
        final javax.swing.JFrame jf = 
          new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(500,400);
        jf.getContentPane().setLayout(new BorderLayout());
        TreeVisualizer tv = new TreeVisualizer(null, tree.graph(), new PlaceNode2());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
          public void windowClosing(java.awt.event.WindowEvent e) {
            jf.dispose();
          }
        });

        jf.setVisible(true);
        tv.fitToScreen();
    }
}
