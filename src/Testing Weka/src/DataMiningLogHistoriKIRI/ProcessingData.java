package DataMiningLogHistoriKIRI;

import java.util.ArrayList;

/**
 *
 * @author Jovan Gunawan
 */
public class ProcessingData
{
    ProcessingData()
    {
    }
    public void processSorting(ArrayList array, ArrayList data, String action)
    {
        for(int i =0; i < data.size(); i++)
        {
            String [] tempData = (String[])data.get(i);
            if(tempData[3].equals(action))
            {
                array.add(tempData);
            }
        }
    }
    public ArrayList preprocessingData(ArrayList<String[]> data, int[] maxMin)
    {
        // 2 int[] untuk mendapatkan nilai max dan min dari varible bulan dan tahun yang digunakan untuk inisialisasi max min pada kelas SDForExtractData.
        // array pertama untuk bulan, dan array kedua untuk tahun
        int []max = new int[2];
        int []min = new int[2];
        max[0] = 0;
        max[1] = 0;
        min[0] = Integer.MAX_VALUE;
        min[1] = Integer.MAX_VALUE;
        
        ArrayList<int[]> result = new ArrayList<int[]>();
        
        // tahap pertama: ubah waktu dari UTC ke GMT+7
        for(int i = 0; i < data.size(); i++)
        {
            //System.out.println(data.get(i)[3]);
            data.get(i)[2] = TimezoneConverter.convertToGMT7(data.get(i)[2]);
        }
        
        // tahap kedua sampai kesembilan
        DistanceHaversine haversine = new DistanceHaversine();
        for(int i = 0; i < data.size(); i++)
        {
            //cek apakah format sudah benar atau belum
            if(data.get(i)[4].split(",").length == 3)
            {
                // tahap kedua: pecah string atribut tanggal
                int[] temp = new int[5];
                String[] splited = data.get(i)[2].split(" ");
                temp[2] = Integer.parseInt(splited[0]);
                // tahap ketiga: pecah nilai string yang tanggal
                String[] splited2 = splited[1].split("/");
                temp[0] = Integer.parseInt(splited2[0]);
                temp[1] = Integer.parseInt(splited2[2]);
                // tahap keempat: pecah nilai string yang jam
                splited2 = splited[2].split(":");
                temp[3] = Integer.parseInt(splited2[0]);
                // tahap kelima: pecah string atribut additional data
                splited = data.get(i)[4].split("/");
                // tahap keenam: pecah lokasi keberangkatan dan lokasi tujuan untuk mendapatkan lat n lon dan (ini tahap ketujuh) menghitung jarak terhadap titik pusat
                splited2 = splited[0].split(",");
                double jarakKeberangkatan = haversine.calculateDistance(Double.parseDouble(splited2[0]), Double.parseDouble(splited2[1]), -6.916667,107.6) * 1000;
                splited2 = splited[1].split(",");
                double jarakTujuan = haversine.calculateDistance(Double.parseDouble(splited2[0]), Double.parseDouble(splited2[1]), -6.916667,107.6) * 1000;
                // tahap kedelapan, set semua data ke array
                temp[4] = klasifikasiKelas(jarakKeberangkatan, jarakTujuan);

                if(temp[4] != -2)
                {
                    result.add(temp);
                    //proses untuk mencatat nilai max dan min
                    if(max[0] < temp[0])
                    {
                        max[0] = temp[0];
                    }
                    if(min[0] > temp[0])
                    {
                        min[0] = temp[0];
                    }
                    if(max[1] < temp[1])
                    {
                        max[1] = temp[1];
                    }
                    if(min[1] > temp[1])
                    {
                        min[1] = temp[1];
                    }
                }
            }
            else
            {
                System.out.println("ERROR: additional data tidak sesuai; " + data.get(i)[4]);
            }
        }
        maxMin[0] = max[0];
        maxMin[1] = max[1];
        maxMin[2] = min[0];
        maxMin[3] = min[1];
        return result;
    }
    
    public int klasifikasiKelas(double jarakKeberangkatan, double jarakTujuan)
    {
        int regionKeberangkatan, regionTujuan;
        int klasifikasi = 0; // 0 --> tidak menuju Bandung, 1 --> menuju Bandung, 2 --> menuju region yang sama
        
        regionKeberangkatan = (int)jarakKeberangkatan;
        regionTujuan = (int)jarakTujuan;
        
        if(regionKeberangkatan >= 11 && regionTujuan >= 11)
        {
            return -2;
        }
        else
        {
            if(regionKeberangkatan >= 11)
            {
                regionKeberangkatan = 11;
            }
            else if(regionTujuan >= 11)
            {
                regionTujuan = 11;
            }
            if (regionKeberangkatan > regionTujuan)
            {
                klasifikasi = -1;
            }
            else if (regionKeberangkatan < regionTujuan)
            {
                klasifikasi = 1;
            }
            else
            {
                klasifikasi = 0;
            }
            return klasifikasi;
        }
    }
}
