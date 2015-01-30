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
}
