/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMiningLogHistoriKIRIWithoutDateAndMinutes;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 *
 * @author Jovan Gunawan
 */
public class DecisionTree 
{
    private Classifier tree;
    
    public double calculateConfident(Instances arff)
    {
        // mengecek confiden
        int nilaiBenar = 0, resultInt;
        float result = 0;
        for (int i = 0; i < arff.numInstances(); i++)
        {
            try {
                result = (float)tree.classifyInstance(arff.instance(i));
                resultInt = Math.round(result);
                if(resultInt == Integer.parseInt(arff.instance(i).stringValue(6)))
                {
                    nilaiBenar++;
                }
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        double confident = nilaiBenar * 1.0 / arff.numInstances() * 100;
        return confident;
    }
    
    public String id3(Instances arff)
    {
        tree = new Id3();
        try {
            NumericToNominal convert= new NumericToNominal();
            String[] options= new String[2];
            options[0]="-R";
            options[1]="1-4"; 

            convert.setOptions(options);
            convert.setInputFormat(arff);

            Instances newData=Filter.useFilter(arff, convert);
            
            tree.buildClassifier(newData);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tree.toString();
    }
    
    public String j48(Instances arff)
    {
        tree = new J48();
        try {
            tree.buildClassifier(arff);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tree.toString();
    }
}
