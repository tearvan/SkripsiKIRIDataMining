/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMiningLogHistoriKIRI;

/**
 *
 * @author Jovan Gunawan
 */
public class testing
{
    public static void main(String [] args)
    {
        int[] max = new int[]{2,2014,7,24};
        int[] min = new int[]{3,2014,1,0};
        SDForExtractData extract = new SDForExtractData(new String[]{"bulan", "tahun", "hari", "jam"},max, min);
        
        max[0] = 5;
        System.out.println(extract.getMaxBatasAtas()[0]);
        
        SDForExtractData extract2 = new SDForExtractData(new String[]{"bulan", "tahun", "hari", "jam"},new int[]{2,2014,7,24}, new int[]{3,2014,1,0});
        
    }
}
