package com.microservice.parada.service.utils;

public class Gps {
    private static final double EARTH_RADIUS_KM = 6371.0; // Radio de la Tierra en km

    public static double[] calcularCuadradoLimite(double lat, double lon, double distanceKm) {
        double latDistance = distanceKm / 111.0; // Aproximación de distancia en grados de latitud
        double lonDistance = distanceKm / (111.0 * Math.cos(Math.toRadians(lat))); // Ajuste para longitud

        double minLat = lat - latDistance;
        double maxLat = lat + latDistance;
        double minLon = lon - lonDistance;
        double maxLon = lon + lonDistance;

        return new double[]{minLat, maxLat, minLon, maxLon};
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convertir de grados a radianes
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Retornar la distancia en km.00

        return Math.round(( EARTH_RADIUS_KM * c)*100d) / 100d;
    }
}
