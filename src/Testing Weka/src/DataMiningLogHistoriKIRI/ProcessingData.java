package DataMiningLogHistoriKIRI;


import java.util.ArrayList;

/*
 * Class for processing data ONLY FOR DATA LOG HISTORY KIRI
 */

/**
 *
 * @author Jovan Gunawan
 */
public class ProcessingData
{
    ProcessingData()
    {
    }

    public void processSorting(ArrayList addApiKey, ArrayList findRoute, ArrayList login, ArrayList nearbyTransport, ArrayList pageLoad, ArrayList register, ArrayList searchPlace, ArrayList widgetError, ArrayList widgetLoad, ArrayList data)
    {
        System.out.println("Banyak Data: " + data.size());
        for(int i =0; i < data.size(); i++)
        {
            String [] tempData = (String[])data.get(i);
            if(tempData[3].equals("ADDAPIKEY"))
            {
                addApiKey.add(tempData);
            }
            else if(tempData[3].equals("FINDROUTE"))
            {
                findRoute.add(tempData);
            }
            else if(tempData[3].equals("LOGIN"))
            {
                login.add(tempData);
            }
            else if(tempData[3].equals("NEARBYTRANSPORT"))
            {
                nearbyTransport.add(tempData);
            }
            else if(tempData[3].equals("PAGELOAD"))
            {
                pageLoad.add(tempData);
            }
            else if(tempData[3].equals("REGISTER"))
            {
                register.add(tempData);
            }
            else if(tempData[3].equals("SEARCHPLACE"))
            {
                searchPlace.add(tempData);
            }
            else if(tempData[3].equals("WIDGETERROR"))
            {
                widgetError.add(tempData);
            }
            else if(tempData[3].equals("WIDGETLOAD"))
            {
                widgetLoad.add(tempData);
            }
        }
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
    public ArrayList preprocessingData(ArrayList<String[]> data)
    {
        ArrayList result = new ArrayList<String[]>();
        
        // tahap pertama: ubah waktu dari UTC ke GMT+7
        for(int i = 0; i < data.size(); i++)
        {
            data.get(i)[2] = TimezoneConverter.convertToGMT7(data.get(i)[2]);
        }
        
        // tahap kedua sampai kesembilan
        DistanceHaversine haversine = new DistanceHaversine();
        for(int i = 0; i < data.size(); i++)
        {
            // tahap kedua: pecah string atribut tanggal
            int[] temp = new int[7];
            String[] splited = data.get(1)[2].split(" ");
            temp[3] = Integer.parseInt(splited[0]);
            // tahap ketiga: pecah nilai string yang tanggal
            String[] splited2 = splited[1].split("/");
            temp[0] = Integer.parseInt(splited2[1]);
            temp[1] = Integer.parseInt(splited2[0]);
            temp[2] = Integer.parseInt(splited2[2]);
            // tahap keempat: pecah nilai string yang jam
            splited2 = splited[2].split(":");
            temp[4] = Integer.parseInt(splited2[0]);
            temp[5] = Integer.parseInt(splited2[1]);
            // tahap kelima: pecah string atribut additional data
            splited = data.get(i)[4].split("/");
            // tahap keenam: pecah lokasi keberangkatan dan lokasi tujuan untuk mendapatkan lat n lon dan (ini tahap ketujuh) menghitung jarak terhadap titik pusat
            splited2 = splited[0].split(",");
            double jarakKeberangkatan = haversine.calculateDistance(Double.parseDouble(splited2[0]), Double.parseDouble(splited2[1]), -6.92036, 107.67023) * 1000;
            splited2 = splited[1].split(",");
            double jarakTujuan = haversine.calculateDistance(Double.parseDouble(splited2[0]), Double.parseDouble(splited2[1]), -6.92036, 107.67023) * 1000;
            // tahap kedelapan, set semua data ke array
            temp[6] = klasifikasiKelas(jarakKeberangkatan, jarakTujuan);
            result.add(temp);
        }
        return result;
    }
    
    public int klasifikasiKelas(double jarakKeberangkatan, double jarakTujuan)
    {
        int regionKeberangkatan, regionTujuan;
        int klasifikasi = 0; // 0 --> tidak menuju Bandung, 1 --> menuju Bandung, 2 --> menuju region yang sama
        
        regionKeberangkatan = (int)jarakKeberangkatan;
        regionTujuan = (int)jarakTujuan;
        
        if(regionKeberangkatan > 11)
        {
            regionKeberangkatan = 11;
        }
        if(regionTujuan > 11)
        {
            regionTujuan = 11;
        }
        
        if (regionKeberangkatan > regionTujuan)
        {
            klasifikasi = 0;
        }
        else if (regionKeberangkatan < regionTujuan)
        {
            klasifikasi = 1;
        }
        else
        {
            klasifikasi = 2;
        }
        return klasifikasi;
    }
}
