package DataMiningLogHistoriKIRIAwal;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jovan Gunawan
 */
public class DistanceHaversine
{
    private double r = 6.371;
    public DistanceHaversine()
    {
    }

    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2)
    {
//        double radianLat1 = Math.toRadians(latitude1);
//        double radianLon1 = Math.toRadians(longitude1);
//        double radianLat2 = Math.toRadians(latitude2);
//        double radianLon2 = Math.toRadians(longitude2);
//
//        double deltaLat = radianLat1-radianLat2;
//        double deltaLon = radianLon1-radianLon2;
//
//        double a = Math.pow(Math.sin(deltaLat/2),2) + Math.cos(radianLat1) * Math.cos(radianLat2) * Math.pow(Math.sin(deltaLon/2), 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//
//        return r * c;
        latitude1 = Math.toRadians(latitude1);
        longitude1 = Math.toRadians(longitude1);
        latitude2 = Math.toRadians(latitude2);
        longitude2 = Math.toRadians(longitude2);

        double dlon = longitude2 - longitude1;
        double dlat = latitude2 - latitude1;

        double a = Math.pow((Math.sin(dlat/2)),2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dlon/2),2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        //System.out.println("a: " + a + ";c: " + c + ";result: " + (r*c));
        return r * c;
    }

    public static void main (String [] args)
    {
        DistanceHaversine dh = new DistanceHaversine();
        //System.out.println(dh.calculateDistance(-6.92036, 107.60500, -6.92036, 107.67023)*1000 + " km");
        //ini bandung - surabaya
        System.out.println(dh.calculateDistance(-6.211544, 106.845172,  -7.289166, 112.734398)*1000 + " km");
    }
}
