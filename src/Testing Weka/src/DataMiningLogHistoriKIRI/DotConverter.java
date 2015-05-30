package DataMiningLogHistoriKIRI;

/**
 *
 * @author Jovan Gunawan
 */
public class DotConverter 
{
    // converter dot khusus untuk skripsi data mining log histori KIRI --> output berupa tree dalam string dari weka
    public static String convert(SDForConvertTree data, SDForExtractData extract, String miningAlgo, int deep, String nodeName)
    {
        String result = "";
        String [] temp;
        String nodeName1="", nodeName2="";
        //color 1  selalu 1.0 karena merah
        double color; 
        if(deep == 0 && data.getData().length == 1 && data.getData()[0].charAt(0) == ':')
        {
            result = data.getData()[0].split(" ")[1];
        }
        else
        {
            if(miningAlgo.equals("id3"))
            {
                boolean hasNext = true;
                int loop = 0;
                while(hasNext)
                {
                    hasNext = false;

                    temp = data.getData(0).split("  ");
                    boolean iniName1 = false;

                    System.out.println("Deep: " + deep + data.getData(0));
                    temp = temp[deep].split(" ");

                    color = 0.7 - deep * 0.02;
                    if(color < 0.3)
                    {
                        color = 0.3;
                    }

                    if(nodeName.equals(""))
                    {
                        if(loop == 0)
                        {
                            nodeName1 = data.getDataNumber(temp[0]);
                        }
                        result += nodeName1 + " -> ";
                        iniName1 = true;
                    }
                    else
                    {
                        result += nodeName + " -> ";
                    }

                    SDForExtractData tempExtract = new SDForExtractData(extract);

                    if(temp[2].charAt(temp[2].length()-1) == ':')
                    {
                        // masukin data buat extract
                        tempExtract.setRules(temp[0], temp[1], Integer.parseInt(temp[2].substring(0, temp[2].length()-1)));
                        try
                        {
                            tempExtract.setKelas(Integer.parseInt(temp[3]));
                        }catch(Exception a)
                        {
                            if(temp[3] == null)
                            {
                                tempExtract.setKelas(-2);
                            }
                        }
                        tempExtract.extract();
                        extract.addStringResult(tempExtract.getList());
                        // menghasilkan daun

                        nodeName2 = data.getDataNumber(temp[3]);
                        result += nodeName2 + " [label=\"" + temp[1] + " " + temp[2].substring(0, temp[2].length()-1) +  "\"]\n";
                        if(iniName1 && loop == 0)
                        {
                            result += nodeName1 + " [label=\"" + temp[0] + "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        }
                        result += nodeName2 + " [label=\"" + temp[3] + "\"]\n";
                        data.buangArrayPertama();
                    }
                    else
                    {
                        // masukin data bwt extract
                        tempExtract.setRules(temp[0], temp[1], Integer.parseInt(temp[2]));

                        // menghasilkan node
                        String [] temp2;
                        temp2 = data.getData(1).split("  ");
                        temp2 = temp2[deep+1].split(" ");
                        nodeName2 = data.getDataNumber(temp2[0]);
                        result += nodeName2 + " [label=\"" + temp[1] + " " + temp[2] +  "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        data.buangArrayPertama();

                        SDForExtractData newExtract = new SDForExtractData(tempExtract);
                        result += DotConverter.convert(data, newExtract, miningAlgo, deep+1, nodeName2);

                        if(iniName1 && loop == 0)
                        {
                            result += nodeName1 + " [label=\"" + temp[0] + "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        }
                        result += nodeName2 + " [label=\"" + temp2[0] + "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        extract.addStringResult(newExtract.getList());
                    }
                    if(data.hasNext())
                    {
                        if(data.getData(0).split("  ").length-1 == deep)
                        {
                            hasNext = true;
                        }
                    }
                    loop++;
                }
            }
            else
            {
                for(int i = 0; i < 2; i++)
                {
                    temp = data.getData(0).split("   ");
                    boolean iniName1 = false;

                    System.out.println("Deep: " + deep + data.getData(0));
                    temp = temp[deep].split(" ");

                    color = 0.7 - deep * 0.02;
                    if(color < 0.3)
                    {
                        color = 0.3;
                    }

                    if(nodeName.equals(""))
                    {
                        if(i == 0)
                        {
                            nodeName1 = data.getDataNumber(temp[0]);
                        }
                        result += nodeName1 + " -> ";
                        iniName1 = true;
                    }
                    else
                    {
                        result += nodeName + " -> ";
                    }

                    SDForExtractData tempExtract = new SDForExtractData(extract);

                    if(temp[2].charAt(temp[2].length()-1) == ':')
                    {   
                        // masukin data bwt extract
                        tempExtract.setRules(temp[0], temp[1], Integer.parseInt(temp[2].substring(0, temp[2].length()-1)));
                        tempExtract.setKelas(Integer.parseInt(temp[3]));
                        tempExtract.extract();
                        extract.addStringResult(tempExtract.getList());
                        // menghasilkan daun
                        nodeName2 = data.getDataNumber(temp[3]);
                        result += nodeName2 + " [label=\"" + temp[1] + " " + temp[2].substring(0, temp[2].length()-1) +  "\"]\n";
                        if(iniName1 && i == 0)
                        {
                            result += nodeName1 + " [label=\"" + temp[0] + "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        }
                        result += nodeName2 + " [label=\"" + temp[3] + "\"]\n";
                        data.buangArrayPertama();
                    }
                    else 
                    {
                        // masukin data bwt extract
                        tempExtract.setRules(temp[0], temp[1], Integer.parseInt(temp[2]));
                        // menghasilkan node
                        String [] temp2;
                        temp2 = data.getData(1).split("   ");
                        temp2 = temp2[deep+1].split(" ");
                        nodeName2 = data.getDataNumber(temp2[0]);
                        result += nodeName2 + " [label=\"" + temp[1] + " " + temp[2] +  "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        data.buangArrayPertama();

                        SDForExtractData newExtract = new SDForExtractData(tempExtract);
                        result += DotConverter.convert(data, newExtract, miningAlgo, deep+1, nodeName2);

                        if(iniName1 && i == 0)
                        {
                            result += nodeName1 + " [label=\"" + temp[0] + "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        }
                        result += nodeName2 + " [label=\"" + temp2[0] + "\",shape=box,style=filled,color=\"1.0 " + color + " 1.0\"]\n";
                        extract.addStringResult(newExtract.getList());
                    }
                }
            }
        }
        return result;
    }
}
