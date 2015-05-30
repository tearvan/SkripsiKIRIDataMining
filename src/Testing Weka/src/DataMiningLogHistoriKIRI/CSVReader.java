package DataMiningLogHistoriKIRI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Jovan Gunawan
 */
public class CSVReader
{
    private ArrayList<String[]> data;
    private int banyakAtribut;
    public CSVReader() 
    {
        data = new ArrayList<String[]>();
        banyakAtribut = 0;
    }

    public void setEmpty()
    {
        getData().clear();
    }
    public ArrayList readCSV(String [] file)
    {
        for(int i = 0; i < file.length; i++)
        {
            readCSV(file[i]);
        }
        return getData();
    }
    public ArrayList readCSV(String file)
    {
        try
        {
            String temp;
            String [] splited;
            int i=0;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((temp = br.readLine()) != null)
            {
                splited = temp.split("\"");
                if(i==0)
                {
                    //baca atributnya terlebih dahulu
                    ArrayList al = new ArrayList<String>();
                    String tempAtribut ="";
                    for(int j = 0; j < splited.length; j++)
                    {
                        if(j%2 == 0)
                        {
                            String [] splitedKoma = splited[j].split(",");
                            for(int k = 0; k < splitedKoma.length; k++)
                            {
                                if(!(k == 0 && splitedKoma[k].length() ==0)||(k==splitedKoma.length-1 && splitedKoma[k].length() == 0))
                                {
                                    al.add(splitedKoma[k]);
                                }
                            }
                        }
                        else
                        {
                            al.add(splited[j]);
                        }
                    }
                    banyakAtribut = al.size();
                    String[] tempDataAtribut = new String[banyakAtribut];
                    for(int j = 0; j < banyakAtribut; j++)
                    {
                        tempDataAtribut[j] = (String)al.get(j);
                    }
                    getData().add(tempDataAtribut);
                    i++;
                }
                else
                {
                    //baca untuk datanya
                    int index = 0;
                    String [] tempData = new String[banyakAtribut];
                    for(int j = 0; j < splited.length; j++)
                    {
                        if(j%2 == 0)
                        {
                            String [] splitedKoma = splited[j].split(",");
                            for(int k = 0; k < splitedKoma.length; k++)
                            {
                                if(!(k == 0 && splitedKoma[k].length() ==0)||(k==splitedKoma.length-1 && splitedKoma[k].length() == 0))
                                {
                                    tempData[index] = splitedKoma[k];
                                    index++;
                                }
                            }
                        }
                        else
                        {
                            // harusnya ada cek ada kutip lagi? ("" merupakan " biasa)
                            tempData[index] = splited[j];
                            index++;
                        }
                    }
                    getData().add(tempData);
                    i++;
                }
            }
            for(int j = 0; j < i; j++)
            {
                String [] temp2 = (String[])getData().get(j);
            }
            br.close();
        }catch(IOException e)
        {
            System.out.println("Failed to read data");
        }
        return getData();
    }

    public ArrayList getData()
    {
        return data;
    }
    public void setData(ArrayList data)
    {
        this.data = data;
    }
    public int getBanyakAtribut()
    {
        return banyakAtribut;
    }
    public void setBanyakAtribut(int banyakAtribut)
    {
        this.banyakAtribut = banyakAtribut;
    }
}
