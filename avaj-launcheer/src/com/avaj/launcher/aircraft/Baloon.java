package com.avaj.launcher.aircraft;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import com.avaj.launcher.Simulator;
import com.avaj.launcher.tower.WeatherTower;
import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower opTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateCondition() {
        String weather = opTower.getWeather(this.coordinates);
        HashMap<String, String> mes = new HashMap<String, String>() {{
           put("SUN", "Oh what a lovely day to cruise the sky!");
           put("RAIN", "The view might be blurry, this rain is getting heavy");
           put("FOG", "Oh nooooo! I can't see anything");
           put("SNOW", "Damn! the snow is freezing our engines hard");
        }};

        if (weather.equals("SUN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 4);
        else if (weather.equals("RAIN"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 5);
        else if (weather.equals("FOG"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 3);
        else if (weather.equals("SNOW"))
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 15);

        Simulator.printer.println("Tower Says: Baloon "+
            this.name + " ID: ["+ this.id +"] " + mes.get(weather));
        if (this.coordinates.getHeight() == 0) {
            Simulator.printer.println("Tower Says: Baloon "+
                    this.name + " ID: ["+ this.id +"] " +" is landing");
            this.opTower.unregister( this );
            Simulator.printer.println("Tower Says: Baloon "+
                    this.name + " ID: ["+ this.id +"] " +" is now unregistered from Tower");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.opTower = weatherTower;
        this.opTower.register( this );
        Simulator.printer.println("Tower Says: Baloon "+
                this.name + " ID: ["+ this.id +"] " +" is registered to Tower");
    }
}
