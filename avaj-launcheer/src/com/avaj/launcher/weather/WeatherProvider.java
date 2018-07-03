package com.avaj.launcher.weather;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import com.avaj.launcher.aircraft.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {
            "SUN", "RAIN", "FOG", "SNOW"
    };

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int sum = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        sum /= 3;
        int gen = sum % 4;
        return weather[gen];
    }
}
