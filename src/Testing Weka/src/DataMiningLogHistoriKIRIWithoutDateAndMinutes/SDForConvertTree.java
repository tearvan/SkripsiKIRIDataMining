/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMiningLogHistoriKIRIWithoutDateAndMinutes;

/**
 *
 * @author Jovan Gunawan
 * Class ini dibuat untuk struktur data yang digunakan untuk mengubah hasil output dari weka menjadi gambar dengan bahasa DOT
 * Class ini khusus untuk skripsi data mining log histori KIRI 
 * 
 * count akan digunakan untuk inisialisasi nomor data --> contoh tanggal1, tanggal 2, .... etc, angka tersebut yang akan ditaruh pada array tersebut
 * array count akan digunakan sebagai berikut --> [0] = tanggal, [1] = bulan, [2] = tahun, [3] = hari, [4] = jam, [5] = menit, [6] = nilai 0, [7] = nilai 1 , [8] = nilai 2
 */
public class SDForConvertTree 
{
    private String[] data;
    private int [] count;
    
    public SDForConvertTree(String [] data)
    {
        this.data = data;
        count = new int[9];
        for(int i = 0; i < 9; i++)
        {
            count[i] = 0;
        }
    }
    
    public void setData(String data, int index)
    {
        this.data[index] = data;
    }
    public String[] getData()
    {
        return data;
    }
    public String getData(int index)
    {
        return this.data[index];
    }
    public void setCount(int count, int index)
    {
        this.count[index] = count;
    }
    public int getCount(int index)
    {
        int temp = this.count[index];
        this.count[index]++;
        return temp;
    }
    
    public void buangArrayPertama()
    {
        String[] temp = new String [data.length-1];
        System.arraycopy(data, 1, temp, 0, data.length-1);
        data = temp;
    }
    public String getDataNumber(String atribut)
    {
        String result = atribut;
        if(atribut.equals("tanggal"))
        {
            result += count[0];
            count[0]++;
        }
        else if(atribut.equals("bulan"))
        {
            result += count[1];
            count[1]++;
        }
        else if(atribut.equals("tahun"))
        {
            result += count[2];
            count[2]++;
        }
        else if(atribut.equals("hari"))
        {
            result += count[3];
            count[3]++;
        }
        else if(atribut.equals("jam"))
        {
            result += count[4];
            count[4]++;
        }
        else if(atribut.equals("menit"))
        {
            result += count[5];
            count[5]++;
        }
        else if(atribut.equals("-1"))
        {
            result += count[6];
            count[6]++;
        }
        else if(atribut.equals("1"))
        {
            result += count[7];
            count[7]++;
        }
        else if(atribut.equals("0"))
        {
            result += count[8];
            count[8]++;
        }
        return result;
    }
}
