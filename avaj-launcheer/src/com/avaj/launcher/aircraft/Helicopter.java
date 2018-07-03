package com.avaj.launcher.aircraft;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import com.avaj.launcher.Simulator;
import com.avaj.launcher.tower.WeatherTower;
import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower opTower;

    Helicopter(String name, Coordinates coordinates) { super(name, coordinates); }

    public void updateCondition() {
        String weather = opTower.getWeather(this.coordinates);
        HashMap<String, String> mes = new HashMap<String, String>() {{
            put("SUN", "This light from the sun is too much, blocking my view");
            put("RAIN", "This will be a bumpy ride in the tides of the winds");
            put("FOG", "I hope I can blow this fog with my choppers");
            put("SNOW", "Oh why did it have to be this snowy, damn snow!");
        }};

        if (weather.equals("SUN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 10,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 2);
        else if (weather.equals("RAIN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 5);
        else if (weather.equals("FOG"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 1);
        else if (weather.equals("SNOW"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 10,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 12);

        Simulator.printer.println("Tower Says: Helicopter "+
            this.name +" ID: ["+ this.id +"] "+ mes.get(weather));
        if (this.coordinates.getHeight() == 0) {
            Simulator.printer.println("Tower Says: Helicopter "+
                    this.name +" ID: ["+ this.id +"] is landing");
            this.opTower.unregister( this );
            Simulator.printer.println("Tower Says: Helicopter "+
                    this.name +" ID: ["+ this.id +"] is unregistered from Tower");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.opTower = weatherTower;
        this.opTower.register( this );
        Simulator.printer.println("Tower Says: Helicopter "+
                this.name +" ID: ["+ this.id +"] is registered to Tower");
    }
}
