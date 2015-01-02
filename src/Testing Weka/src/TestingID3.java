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

public class TestingID3 
{
    public TestingID3()
    {
    }
    
    public static void main(String [] args) throws Exception
    {
        //Testing test = new Testing();
        String[] option = new String[]{"-U"};
        Instances data;
        Id3 tree = new Id3();
        try {
            tree.setOptions(option);
            data = new Instances(new BufferedReader(new FileReader("D:\\Tugas\\Skripsi-1\\Skripsi GIT\\SkripsiKIRIDataMining\\src\\test data\\test data.arff")));
            data.setClassIndex(data.numAttributes() - 1);
            tree.buildClassifier(data);
        } catch (Exception ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
        // nilai yang harus dibuat untuk dijadikan tree!!
        System.out.println(tree.toString());
    }
}
