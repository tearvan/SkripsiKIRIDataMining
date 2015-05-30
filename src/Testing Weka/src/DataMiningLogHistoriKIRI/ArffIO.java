package DataMiningLogHistoriKIRI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;

/**
 *
 * @author Jovan Gunawan
 */
public class ArffIO 
{
    public ArffIO()
    {
    }
    
    // method writer ini dibuat KHUSUS untuk skripsi data log KIRI
    // input berupa arraylist dengan objek int[]
    // Setiap array int, terdapat 7 nilai yaitu tanggal, bulan, tahun, hari, jam, menit, menujuBandung
    // disini nilai hari akan diubah menjadi string, 1 akan menjadi senin, ..., dan 7 akan menjadi minggu
    public void writeArrf(String name, ArrayList<int[]> data)
    {
        String result = "@relation " + name + "\n\n@attribute bulan REAL\n@attribute tahun REAL\n"
                + "@attribute hari REAL"
                + "\n@attribute jam REAL\n@attribute menujuBandung {-1,0,1}\n\n@data";
        
        for(int i = 0; i < data.size(); i++)
        {
            int[] temp = data.get(i);
            result += "\n" + temp[0] + "," + temp[1] + "," + temp[2] + "," + temp[3] + "," + temp[4]; 
        }
        
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("temp.arff")));
            out.println(result);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error ketika menulis file arff");
        }
    }
    
    public Instances readArff(String name) throws IOException
    {
        Instances data;
        data = new Instances(new BufferedReader(new FileReader("temp.arff")));
        data.setClassIndex(data.numAttributes() - 1);
        return data;
    }
}
