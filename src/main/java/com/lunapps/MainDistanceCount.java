package com.lunapps;

public class MainDistanceCount {
    public static void main(String[] args) {
        final int R = 6371; // Radious of the earth

        Double lat1 = 47.57948;
        Double lon1 = 37.31976;

        Double lat2 = 47.5769447;
        Double lon2 = 37.3136707;

        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;

        System.out.println("The distance between two lat and long is::" + distance);
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
