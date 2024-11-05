package com.microservice.usuario.service.utils;

public class Ubicacion {
    private static final double EARTH_RADIUS = 6371; // Radio de la Tierra en km

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convertir de grados a radianes
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Retornar la distancia en km.00

        return Math.round(( EARTH_RADIUS * c)*100d) / 100d;
    }
}
