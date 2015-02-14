/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMiningLogHistoriKIRI;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.Id3;
import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 *
 * @author Jovan Gunawan
 */
public class DecisionTree 
{
    private Classifier tree;
    
    public String[] id3(Instances arff)
    {
        J48 tree = new J48();
        try {
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
                if(resultInt == Integer.parseInt(arff.instance(i).stringValue(6)))
                {
                    nilaiBenar++;
                }
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("nilai: " + nilaiBenar + " " + arff.numInstances());
        double confident = nilaiBenar * 1.0 / arff.numInstances() * 100;
        System.out.println("Confident = " + confident + "%");
        
        
        String [] result2 = new String[5];
        return result2;
    }
    
    public String[] j48()
    {
        String [] result = new String[5];
        return result;
    }
}
