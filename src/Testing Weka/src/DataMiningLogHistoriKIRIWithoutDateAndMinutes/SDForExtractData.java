package DataMiningLogHistoriKIRIWithoutDateAndMinutes;

import java.util.ArrayList;

/**
 *
 * @author Jovan Gunawan
 */
public class SDForExtractData 
{
    private String[] atribut;
    private boolean[] check;
    private int[] batasAtas;
    private int[] batasBawah;
    private int[] maxBatasAtas;
    private int[] minBatasBawah;
    private int kelas;
    private ArrayList<String> list;
    
    public SDForExtractData(String[] atribut, int[] max, int[] min)
    {
        int length = atribut.length;
        this.atribut = new String[length];
        this.maxBatasAtas = new int[length];
        this.minBatasBawah = new int[length];
        this.batasAtas = new int[length];
        this.batasBawah = new int[length];
        this.check = new boolean[length];
        list = new ArrayList<String>();
        
        for(int i = 0; i < length; i++)
        {
            this.atribut[i] = atribut[i];
            this.maxBatasAtas[i] = max[i];
            this.batasAtas[i] = max[i];
            this.minBatasBawah[i] = min[i];
            this.batasBawah[i] = min[i];
            check[i] = false;
        }
    }
    
    public SDForExtractData(SDForExtractData data)
    {
        String[] atribut = data.getAtribut();
        int[] max = data.getMaxBatasAtas();
        int[] min = data.getMinBatasBawah();
        int[] bmax = data.getBatasAtas();
        int[] bmin = data.getBatasBawah();
        boolean[] check = data.getCheck();
        int length = data.getAtribut().length;
        list = data.getList();
        
        this.atribut = new String[length];
        this.maxBatasAtas = new int[length];
        this.minBatasBawah = new int[length];
        this.batasAtas = new int[length];
        this.batasBawah = new int[length];
        this.check = new boolean[length];
        
        for(int i = 0; i < length; i++)
        {
            this.atribut[i] = atribut[i];
            this.maxBatasAtas[i] = max[i];
            this.batasAtas[i] = bmax[i];
            this.minBatasBawah[i] = min[i];
            this.batasBawah[i] = bmin[i];
            this.check[i] = check[i];
        }
    }
    
    public void setKelas(int kelas)
    {
        this.kelas = kelas;
    }
    
    public void setRules(String atribut, String rulesOperation, int value)
    {
        int index = 0;
        for(int i = 0; i < this.atribut.length; i++)
        {
            if(this.atribut[i].equals(atribut))
            {
                index = i;
                break;
            }
        }
        check[index] = true;
        
        if(rulesOperation.equals("<="))
        {
            batasAtas[index] = value;
        }
        else if(rulesOperation.equals("<"))
        {
            batasAtas[index] = value-1;
        }
        else if(rulesOperation.equals(">="))
        {
            batasBawah[index] = value;
        }
        else if(rulesOperation.equals(">"))
        {
            batasBawah[index] = value+1;
        }
        else if(rulesOperation.equals("="))
        {
            batasAtas[index] = value;
            batasBawah[index] = value;
        }
    }
    
    public void extract()
    {
        String[] hari = new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
        boolean in = false;
        String result = "";
        for(int i = 0; i < atribut.length; i++)
        {
            if(check[i])
            {
                if(in)
                {
                    result += " && ";
                }
                if(batasBawah[i] == batasAtas[i])
                {
                    result += atribut[i] + " = " + batasAtas[i];
                }
                else
                {
                    result += atribut[i] + ": " + batasBawah[i] + " - " + batasAtas[i]; 
                }
                in = true;
            }
        }
        
        if(kelas == 1)
        {
            result += " -> Menuju ke Bandung";
        }
        else if(kelas == 0)
        {
            result += " -> Menuju daerah yang sama";
        }
        else
        {
            result += " -> Menjauh dari Bandung";
        }
        list.add(result);
    }
    
    public void addStringResult(ArrayList<String> result)
    {
        list = result;
    }

    public String[] getAtribut() {
        return atribut;
    }

    public boolean[] getCheck() {
        return check;
    }

    public int[] getBatasAtas() {
        return batasAtas;
    }

    public int[] getBatasBawah() {
        return batasBawah;
    }

    public int[] getMaxBatasAtas() {
        return maxBatasAtas;
    }

    public int[] getMinBatasBawah() {
        return minBatasBawah;
    }

    public int getKelas() {
        return kelas;
    }

    public ArrayList<String> getList() {
        return list;
    }
    
}
